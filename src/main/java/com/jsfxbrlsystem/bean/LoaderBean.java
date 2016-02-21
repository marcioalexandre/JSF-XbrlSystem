/*
 * Copyright 2016 Marcio Alexandre
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.bean;

import java.io.IOException;
import javax.servlet.http.Part;
import java.io.Serializable;
import java.util.Scanner;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */
@ManagedBean(name="loader")
@SessionScoped
public class LoaderBean implements Serializable {
    private Part file;
    private String fileAsString;
    
    private static final int MAX_SIZE = 5 * 1024 * 1024;

    public String getFileAsString() {
        return fileAsString;
    }

    public void setFileAsString(String fileAsString) {
        this.fileAsString = fileAsString;
    }
    
    public String importing() {
        String result = "index";
        try {
            String content = new Scanner(file.getInputStream())
                .useDelimiter("\\A").next();
            this.fileAsString = content;
            result = "ok";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public void validator(FacesContext context, UIComponent component, Object value) {
        Part file = (Part) value;
        if (file.getSize() > MAX_SIZE) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "File too big ("+file.getSize()+")", "It must not be most than 5mb.");
            throw new ValidatorException(msg);
        }else if (!"text/xml".equals(file.getContentType()) && !"application/octet-stream".equals(file.getContentType())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "File type is invalide ("+file.getContentType()+")", "File must be XBRL/XML type.");
                throw new ValidatorException(msg);
        }
    }


    // getter e setter   

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }    
}