/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.bean;
import java.io.BufferedReader;
import java.io.StringReader;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */
@Entity
@Table( name = "XbrlDocBean" )
public class XbrlDocBean {
    @Id
    @GeneratedValue
    private int idDoc;
    @Column
    private String name;
    @Column
    private Document doc;
    @Column
    private String docAsString;

    public XbrlDocBean(){
        //hibernate
    }
    
    public XbrlDocBean(int id, String name, Document doc, String docAsString){
        //JUnit
        this.idDoc = id;
        this.name = name;
        this.doc = doc;
        this.docAsString = docAsString;
    }
    
    public int getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }
    
    public Document getDoc() {
        return doc;
    }

    public String getName() {
        return name;
    }

    public void setDocAsPart(Part file) {
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
    
    public void setDocAsString(String file) {
        this.docAsString = file;
    }    
    
    public BufferedReader getDocAsBufferedReader(){
        StringReader sr = new StringReader(this.docAsString); // wrap your String
        BufferedReader br = new BufferedReader(sr); // wrap your StringReader
        return br;
    }
    
}
