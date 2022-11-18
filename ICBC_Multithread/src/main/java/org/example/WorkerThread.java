package org.example;

import java.util.Random;


public class WorkerThread implements Runnable {

    private String threadNumber;
    Random random = new Random();

    public WorkerThread(String s){
        this.threadNumber =s;
    }

    @Override
    public void run() {

        System.out.println("Thread"+ threadNumber +" is started at "+ java.time.LocalTime.now());
//      System.out.println(Thread.currentThread().getName()+" is running " + java.time.LocalTime.now());

        processCommand();
//      olusturulan thread'i, 1-20 arasinda rastgele bir sureye atiyor
        try {
            Thread.sleep(random.nextInt(20000));
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
