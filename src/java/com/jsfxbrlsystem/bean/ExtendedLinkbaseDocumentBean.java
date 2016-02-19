/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.bean;

import java.util.ArrayList;

/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */
public class ExtendedLinkbaseDocumentBean {
    //FootNote, Label, Presentation, Calculation, Reference, definir or geoXBRL anyone else.
    private RootBean root; // 0..1 [if it's another XML document (eg.: <linkbase...>)
    private ArrayList<SimpleLinkBean> simplelink;
    private ExtendedLinkbaseTypeBean extendedlinkbasetype; // 0..1 (eg.: footnoteLink, labelLink, calculationLink geoxbrlLink...)

    public RootBean getRoot() {
        return root;
    }

    public void setRoot(RootBean root) {
        this.root = root;
    }

    public ArrayList<SimpleLinkBean> getSimplelink() {
        return simplelink;
    }

    public void setSimplelink(ArrayList<SimpleLinkBean> simplelink) {
        this.simplelink = simplelink;
    }

    public ExtendedLinkbaseTypeBean getExtendedlinkbasetype() {
        return extendedlinkbasetype;
    }

    public void setExtendedlinkbasetype(ExtendedLinkbaseTypeBean extendedlinkbasetype) {
        this.extendedlinkbasetype = extendedlinkbasetype;
    }


  
}
