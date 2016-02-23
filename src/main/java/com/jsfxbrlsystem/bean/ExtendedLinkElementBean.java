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
public class ExtendedLinkElementBean {
    @Id
    @GeneratedValue
    private int idExtLinkEle;
    private String name; //(eg.: <link:footnote ...>, <link: label ...>, <link: geodata...>, <link: calculation...>
    private String label;
    private String role;
    private String type="resource";
    private String lang;
    private ArrayList<AttributeBean> attributeList; //0..1 (others optional attributes)

    public int getIdExtLinkEle() {
        return idExtLinkEle;
    }

    public void setIdExtLinkEle(int idExtLinkEle) {
        this.idExtLinkEle = idExtLinkEle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public ArrayList<AttributeBean> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(ArrayList<AttributeBean> attributeList) {
        this.attributeList = attributeList;
    }

       
}
