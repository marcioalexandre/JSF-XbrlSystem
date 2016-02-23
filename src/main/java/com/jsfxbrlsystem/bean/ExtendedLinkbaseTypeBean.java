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
public class ExtendedLinkbaseTypeBean {
    @Id
    @GeneratedValue
    private int idExtLinkType;
    private String name; ////FootNote, Label, Presentation, Calculation, Reference, definir or geoXBRL anyone else.
    private ArrayList<SimpleLinkBean> simplelinkList; // 0..N (eg.: roleRef,...)
    private ArrayList<AttributeBean> attributeList; // 0..N (eg.: role, type...or others optional attributes)
    private ArrayList<LocBean> locList; //locator
    private ArrayList<ExtendedLinkElementBean> extendedlinkelementList; //Link specific element (label, footnote, geodata,...)
    private ArrayList<ExtendedLinkArcBean> extendedlinkarcList; //Arc element between locator and link specific element

    public int getIdExtLinkType() {
        return idExtLinkType;
    }

    public void setIdExtLinkType(int idExtLinkType) {
        this.idExtLinkType = idExtLinkType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SimpleLinkBean> getSimplelinkList() {
        return simplelinkList;
    }

    public void setSimplelinkList(ArrayList<SimpleLinkBean> simplelinkList) {
        this.simplelinkList = simplelinkList;
    }

    public ArrayList<AttributeBean> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(ArrayList<AttributeBean> attributeList) {
        this.attributeList = attributeList;
    }

    public ArrayList<LocBean> getLocList() {
        return locList;
    }

    public void setLocList(ArrayList<LocBean> locList) {
        this.locList = locList;
    }

    public ArrayList<ExtendedLinkElementBean> getExtendedlinkelementList() {
        return extendedlinkelementList;
    }

    public void setExtendedlinkelementList(ArrayList<ExtendedLinkElementBean> extendedlinkelementList) {
        this.extendedlinkelementList = extendedlinkelementList;
    }

    public ArrayList<ExtendedLinkArcBean> getExtendedlinkarcList() {
        return extendedlinkarcList;
    }

    public void setExtendedlinkarcList(ArrayList<ExtendedLinkArcBean> extendedlinkarcList) {
        this.extendedlinkarcList = extendedlinkarcList;
    }

}
