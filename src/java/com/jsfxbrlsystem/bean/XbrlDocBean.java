/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.bean;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */

public class XbrlDocBean {
    private String name;
    private Document doc;
    
    public Document getDoc() {
        return doc;
    }

    public String getName() {
        return name;
    }

    public void setDoc(Part file) {
        try{
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            //Document dDoc = builder.parse("D:/workspace/GeoXBRL/geoxbrlConfig.xml");
            this.name = file.getSubmittedFileName();
            Document dDoc = builder.parse(file.getInputStream());
            this.doc = dDoc;
            System.out.println("File loaded");
        }catch(Exception e){
            System.err.println("XbrlDocument class");
            e.printStackTrace();
        }
    }
    
}