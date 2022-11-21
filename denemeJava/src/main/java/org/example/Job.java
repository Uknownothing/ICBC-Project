package org.example;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * A class that simulates a job,
 * prints job start time,
 * wait for a random of time,
 * prints job end time
 */
public class Job implements Runnable {

    private final Random random = new Random();
    private final String name;
    private final int    maxWaitTime;

    public Job(String name, int maxWaitTime) {
        this.name        = name;
        this.maxWaitTime = maxWaitTime;
    }

    public void run() {
        System.out.println(name + " started at " + LocalDateTime.now().toString());
        try {
            Thread.sleep(random.nextInt(maxWaitTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " stopped at " + LocalDateTime.now().toString());
    }

}
