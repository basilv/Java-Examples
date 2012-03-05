package com.basilv.examples.rmi;


import java.io.Serializable;
import java.rmi.*;

public interface RemoteTaskExecutor extends Remote {

    public Object executeTask(Task task, Serializable argument) throws RemoteException;
}
