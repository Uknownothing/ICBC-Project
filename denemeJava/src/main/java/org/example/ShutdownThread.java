package org.example;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ShutdownHook class that checks if all threads in thread pool is finished
 */
public class ShutdownThread extends Thread {

    private final App                app;
    private final ThreadPoolExecutor executor;
    private final int                waitTimeToCheckThreadFinish;

    public ShutdownThread(App app, ThreadPoolExecutor executor, int waitTimeToCheckThreadFinish) {
        this.app = app;
        this.executor = executor;
        this.waitTimeToCheckThreadFinish = waitTimeToCheckThreadFinish;
    }

    @Override
    public void run() {
        System.out.println("Service is shutting down");
        // App main loop will not continue
        app.setContinueToCreate(false);
        // Thread pool does not except new threads
        executor.shutdown();
        waitUntilAllThreadsFinish(waitTimeToCheckThreadFinish);
        System.out.println("Service shutdowned");
    }

    /**
     * Loops until all threads in thread pool finishes
     * @param waitTimeToCheckThreadFinish Sleep time in miliseconds for loop
     */
    private void waitUntilAllThreadsFinish(int waitTimeToCheckThreadFinish) {
        while (!executor.isTerminated()) {
            System.out.println("Waiting threads to finish");
            try {
                Thread.sleep(waitTimeToCheckThreadFinish);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
