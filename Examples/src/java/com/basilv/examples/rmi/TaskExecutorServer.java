package com.basilv.examples.rmi;


import java.io.Serializable;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class TaskExecutorServer extends UnicastRemoteObject implements RemoteTaskExecutor {

    public static final String REGISTRY_NAME = TaskExecutorServer.class.getName();
    
    public TaskExecutorServer() throws RemoteException {
        super();
    }

    public Object executeTask(Task task, Serializable argument) {
        return task.execute(argument);
    }

    public static void main(String[] args) throws Exception {
        
        int registryPortNumber = 1099;
        LocateRegistry.createRegistry(registryPortNumber);
        
        Naming.rebind(REGISTRY_NAME, new TaskExecutorServer());
        
        System.out.println("Server running...");
        
        Thread.sleep(15000);
        
        System.out.println("Shutting down server...");
        Naming.unbind(REGISTRY_NAME);
        System.exit(1);
    }
    
    
}
