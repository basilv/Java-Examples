package com.basilv.examples.rmi;


import java.rmi.Naming;

public class TaskExecutorClient {

    public static void main(String[] args) throws Exception {

        String host = "localhost";
        int portNumber = 1099;
        String lookupName = "//" + host + ":" + portNumber + "/" + TaskExecutorServer.REGISTRY_NAME;
        RemoteTaskExecutor executor = (RemoteTaskExecutor) Naming.lookup(lookupName);

        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("Requesting task execution for " + currentTimeMillis);
        Object result = executor.executeTask(new TestTask(), currentTimeMillis);
        System.out.println("Task executed for " + result);
        
    }
    
    public static class TestTask implements Task {

        public Object execute(Object argument) {
            System.out.println("Executing task for " + argument +
                " thread id = " + Thread.currentThread().getId());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
            System.out.println("Executed task for " + argument);
            return argument;
        }
        
    }
}
