/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.controller;

import com.jsfxbrlsystem.bean.ElementBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */
@ManagedBean(name="e")
@SessionScoped
public class ElementController {
    private ElementBean ele;
    public ElementController(){
        ele = new ElementBean();
    }

    public ElementBean getEle() {
        return ele;
    }

    public void setEle(ElementBean ele) {
        this.ele = ele;
    }
    
}
