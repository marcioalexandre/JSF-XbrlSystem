/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.controller;

import com.jsfxbrlsystem.bean.FormBean;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */
@Named("formC")
@SessionScoped
public class FormController implements Serializable {
    private FormBean form;
    public FormController(){
        form = new FormBean();
    }

    public FormBean getForm() {
        return form;
    }

    public void setForm(FormBean form) {
        this.form = form;
    }
    
    public String ok(){
        if (form.getName().equals("") || form.getName().equals(null))
            return "index"; //faces-config
        else
            return "ok";//faces-config
        
    }
    
}
