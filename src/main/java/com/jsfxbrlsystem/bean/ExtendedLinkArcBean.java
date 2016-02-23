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
public class ExtendedLinkArcBean {
    @Id
    @GeneratedValue
    private int idExtLinkArc;
    private String type="arc";
    private String arcrole;
    private String from;
    private String to;
    private String title;
    private String order;
    private ArrayList<AttributeBean> attributeList; //0..1 (others optional attributes)

    public int getIdExtLinkArc() {
        return idExtLinkArc;
    }

    public void setIdExtLinkArc(int idExtLinkArc) {
        this.idExtLinkArc = idExtLinkArc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArcrole() {
        return arcrole;
    }

    public void setArcrole(String arcrole) {
        this.arcrole = arcrole;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public ArrayList<AttributeBean> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(ArrayList<AttributeBean> attributeList) {
        this.attributeList = attributeList;
    }
     
}
