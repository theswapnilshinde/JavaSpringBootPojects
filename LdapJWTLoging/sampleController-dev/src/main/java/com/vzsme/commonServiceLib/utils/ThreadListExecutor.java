package com.vzsme.commonServiceLib.utils;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ThreadListExecutor<T> {
    private int maxThreads;
    private List list;

    public ThreadListExecutor(int maxThreads, List list) {
        this.maxThreads = maxThreads;
        this.list = list;
    }

    public void processList(Consumer<T> callback) {
        try {
            int count = 0;
            CountDownLatch latch = new CountDownLatch(maxThreads);

            for (int ndx = 0; ndx < this.list.size(); ndx++) {
                count++;
                final CountDownLatch latch2 = latch;
                final Object obj = this.list.get(ndx);
                new Thread(() -> {
                    try {
                        callback.accept((T)obj);
                    } finally {
                        latch2.countDown();
                    }
                }).start();

                //
                // If at max threads OR at the end of the list then
                // wait for the threads to complete
                //
                if (count == maxThreads || ndx == this.list.size() - 1) {

                    // If we are at the end of the list AND
                    // there are not max threads then reduce the wait counter
                    int cnt = maxThreads - count;
                    while (cnt-- > 0) {
                        latch.countDown();
                    }

                    // Wait until the counter is zero OR we timeout
                    try {
                        latch.await(5, TimeUnit.MINUTES);
                    } catch (InterruptedException ex) {
                        System.out.println(String.format("Wait timeout. Number of threads still waiting: %d", latch.getCount()));
                    }

                    // Continue if there are more in the list
                    if (ndx < this.list.size() - 1) {
                        count = 0;
                        latch = new CountDownLatch(maxThreads);
                    }
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
