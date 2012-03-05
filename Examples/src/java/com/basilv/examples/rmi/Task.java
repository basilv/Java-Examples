package com.basilv.examples.rmi;


import java.io.Serializable;

public interface Task extends Serializable {
    public Object execute(Object argument);
}
