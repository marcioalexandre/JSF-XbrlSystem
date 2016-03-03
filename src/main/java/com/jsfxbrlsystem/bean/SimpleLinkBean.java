/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.bean;

import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */
@Entity
@Table( name = "SimpleLinkBean" )
public class SimpleLinkBean {
    @Id
    @GeneratedValue
    private int idSimpleLink;
    //all simple link sort: roleref, schemaRef, linkbaseRef...
    @OneToMany (cascade = {CascadeType.ALL},mappedBy = "AttributeBean",
            targetEntity = com.jsfxbrlsystem.bean.AttributeBean.class)
    private ArrayList<AttributeBean> attributeList;
    
    public SimpleLinkBean(){
        //hibernate
    }
    
    public SimpleLinkBean(int id, ArrayList<AttributeBean> attrList){
        //Junit
        this.idSimpleLink = id;
        this.attributeList = attrList;
    }

    public int getIdSimpleLink() {
        return idSimpleLink;
    }

    public void setIdSimpleLink(int idSimpleLink) {
        this.idSimpleLink = idSimpleLink;
    }

    public ArrayList<AttributeBean> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(ArrayList<AttributeBean> attributeList) {
        this.attributeList = attributeList;
    }
    
}
