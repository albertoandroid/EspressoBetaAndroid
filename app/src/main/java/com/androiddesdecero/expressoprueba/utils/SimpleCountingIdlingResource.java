package com.androiddesdecero.expressoprueba.utils;

import java.util.concurrent.atomic.AtomicInteger;

import androidx.test.espresso.IdlingResource;

import static androidx.core.util.Preconditions.checkNotNull;

/*
Lo que hemos comentador, será cero si no hay tareas pendientes
tendra un valor diferente de cero si hay tareas que deber
finalizar.
 */
public class SimpleCountingIdlingResource implements IdlingResource {
    private final String resourceName;

    private final AtomicInteger counter = new AtomicInteger(0);

    // written from main thread, read from any thread.
    private volatile ResourceCallback resourceCallback;

    /**
     * Creates a SimpleCountingIdlingResource
     *
     * @param resourceName the resource name this resource should report to Espresso.
     */
    public SimpleCountingIdlingResource(String resourceName) {
        this.resourceName = checkNotNull(resourceName);
    }

    @Override
    public String getName() {
        return resourceName;
    }

    /*
    Es decir si ha acabado o todavia esta haciendo algo en background
     */
    @Override
    public boolean isIdleNow() {
        return counter.get() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

    /**
     * Increments the count of in-flight transactions to the resource being monitored.
     */
    public void increment() {
        counter.getAndIncrement();
    }

    /**
     * Decrements the count of in-flight transactions to the resource being monitored.
     *
     * If this operation results in the counter falling below 0 - an exception is raised.
     *
     * @throws IllegalStateException if the counter is below 0.
     */
    public void decrement() {
        int counterVal = counter.decrementAndGet();
        if (counterVal == 0) {
            // we've gone from non-zero to zero. That means we're idle now! Tell espresso.
            if (null != resourceCallback) {
                resourceCallback.onTransitionToIdle();
            }
        }

        if (counterVal < 0) {
            throw new IllegalArgumentException("Counter has been corrupted!");
        }
    }
}

