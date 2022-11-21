package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class App {

    private boolean continueToCreate;
    private ThreadPoolExecutor executor;

    public void setContinueToCreate(boolean value) {
        this.continueToCreate = value;
    }

    /**
     * Sleep for a given time in miliseconds
     *
     * @param waitTime Sleep time in miliseconds
     */
    private void waitForNewJob(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Continuously create new job and wait for a certain time
     * @param maxNumberOfThreads Maximum number of threads
     * @param maxJobTime Maximum time for a job
     * @param waitTime Wait time in miliseconds
     */
    private void createNewJobs(int maxNumberOfThreads, int maxJobTime, int waitTime) {
        int i = 1;
        while (continueToCreate) {
            if (executor.getActiveCount() >= maxNumberOfThreads) {
                System.out.println("Thread pool is full");
            } else {
                executor.execute(new Job(Integer.toString(i++), maxJobTime));
            }
            waitForNewJob(waitTime);
        }
    }

    /**
     * Initilization of service
     * @param maxNumberOfThreads Maximum number of threads
     * @param timeToCheckThreadFinish Time in miliseconds for checking if thread has been finished
     */
    private void initilizeService(int maxNumberOfThreads,
                                  int timeToCheckThreadFinish) {
        continueToCreate = true;
        executor         = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxNumberOfThreads);
        executor.setMaximumPoolSize(maxNumberOfThreads);

        Runtime.getRuntime().addShutdownHook(new ShutdownThread(this, executor, timeToCheckThreadFinish));
    }

    /**
     * Process
     */
    public void process() throws IOException {

        Properties p = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/data.properties");
        p.load(fis);


        String max_threads = p.getProperty("MAX_THREADS");
        String max_job_time = p.getProperty("MAX_JOB_TIME");
        String wait_time_for_new_job = p.getProperty("WAIT_TIME_FOR_NEW_JOB");
        String wait_time_to_check_thread_finish  = p.getProperty("WAIT_TIME_TO_CHECK_THREAD_FINISH");


        initilizeService(Integer.parseInt(max_threads), Integer.parseInt(wait_time_to_check_thread_finish));
        createNewJobs(Integer.parseInt(max_threads),Integer.parseInt(max_job_time),Integer.parseInt(wait_time_for_new_job));

        
    }


    public static void main(String[] args) throws IOException {

        App app = new App();
        app.process();


    }

}
