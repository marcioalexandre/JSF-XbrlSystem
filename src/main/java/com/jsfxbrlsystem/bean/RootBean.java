/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.bean;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */
@Entity
public class RootBean {
    @Id
    @GeneratedValue
    private int idRoot;
    private ArrayList<AttributeBean> attributeList; // 0..N (eg.: xmlns, xmlns:xsi, xmlns:xlink, xsi:schemaLocation...)

    public int getIdRoot() {
        return idRoot;
    }

    public void setIdRoot(int idRoot) {
        this.idRoot = idRoot;
    }

    public ArrayList<AttributeBean> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(ArrayList<AttributeBean> attributeList) {
        this.attributeList = attributeList;
    }
    
}
