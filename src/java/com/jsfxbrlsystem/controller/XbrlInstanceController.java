/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.controller;

import com.jsfxbrlsystem.bean.XbrlInstanceBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */
@ManagedBean(name="i")
@SessionScoped
public class XbrlInstanceController {
    private XbrlInstanceBean instance;
    public XbrlInstanceController(){
        instance = new XbrlInstanceBean();
    }
    public XbrlInstanceBean getInstance() {
        return instance;
    }

    public void setInstance(XbrlInstanceBean instance) {
        this.instance = instance;
    }
    
}
