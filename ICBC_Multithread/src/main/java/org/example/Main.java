package org.example;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {

                System.out.println("Program Terminated, rest of the threads will process");

                executor.shutdown();
                try {
                    // timeout 20000 ile thread islemleri 1-20 arasinda random bir degere ataniyor.
                    if (!executor.awaitTermination(20000, TimeUnit.SECONDS)) {
                        List<Runnable> droppedTasks = executor.shutdownNow();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("All threads is finished. Good bye!");
            }
        });

//        RejectedExecutionHandlerImpl n1 = new RejectedExecutionHandlerImpl();
        int i = 1;
        while (true) {

            Thread.sleep(1000);
            Runnable worker = new WorkerThread(" "+ i);
            executor.execute(worker);
            i++;
        }
    }
}

