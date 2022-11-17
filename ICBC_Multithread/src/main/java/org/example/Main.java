package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {

//                try {
//                    executor.awaitTermination(3, TimeUnit.SECONDS);
//                    System.out.println("******************************************************************Finished all threads");
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }

            }

        });
        int i = 1;
        while (true) {
            Runnable worker = new WorkerThread(" "+ i);
            executor.execute(worker);
            i++;

        }
    }
}





//        while(true)
//        for (int i = 1; i < 20; i++) {
//            Runnable worker = new WorkerThread(" "+ i);
//            executor.execute(worker);
//        }
//        executor.shutdown();
//
//        while (!executor.isTerminated()) {
//
//        }
//
//        System.out.println("Finished all threads");
