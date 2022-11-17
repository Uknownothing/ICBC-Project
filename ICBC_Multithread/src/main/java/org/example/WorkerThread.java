package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class WorkerThread implements Runnable {

    private String threadNumber;

    public WorkerThread(String s){
        this.threadNumber =s;

    }

    @Override
    public void run() {


        System.out.println("Thread"+ threadNumber +" is started at "+ java.time.LocalTime.now());
//      System.out.println(Thread.currentThread().getName()+" is running " + java.time.LocalTime.now());

        processCommand();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----------Thread"+ threadNumber +" is finsihed at "+ java.time.LocalTime.now());


    }

    private void processCommand() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return this.threadNumber;
    }

}