/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */
@Entity
public class ElementBean {
    @Id
    @GeneratedValue
    private int idElement;
    private int number;
    private String name;
    private String contextRef;
    private String unitRef;
    private String decimals;
    private String id;
    private String value;

    public int getIdElement() {
        return idElement;
    }

    public void setIdElement(int idElement) {
        this.idElement = idElement;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        String[] ele = name.split(":");
        if (ele.length > 1)
            this.name = ele[1];
        else
            this.name = ele[0];
    }

    public String getContextRef() {
        return contextRef;
    }

    public void setContextRef(String contextRef) {
        this.contextRef = contextRef;
    }

    public String getUnitRef() {
        return unitRef;
    }

    public void setUnitRef(String unitRef) {
        this.unitRef = unitRef;
    }

    public String getDecimals() {
        return decimals;
    }

    public void setDecimals(String decimals) {
        this.decimals = decimals;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public void print(){
        System.out.println("===============");
        System.out.println("ContextRef: "+this.contextRef);
        System.out.println("Decimals: "+this.decimals);
        System.out.println("Id: "+this.id);
        System.out.println("Name: "+this.name);
        System.out.println("UnitRef: "+this.unitRef);
        System.out.println("Value: "+this.value);
    }
}
