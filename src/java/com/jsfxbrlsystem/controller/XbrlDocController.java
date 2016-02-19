/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.controller;

import com.jsfxbrlsystem.bean.XbrlDocBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */
@ManagedBean(name="d")
@SessionScoped
public class XbrlDocController {
    private XbrlDocBean document;
    public XbrlDocController(){
        document = new XbrlDocBean();
    }

    public XbrlDocBean getDocument() {
        return document;
    }

    public void setDocument(XbrlDocBean document) {
        this.document = document;
    }


    
}
