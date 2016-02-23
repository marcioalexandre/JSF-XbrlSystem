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
public class PeriodBean {
    @Id
    @GeneratedValue
    private int idPeriod;
    private AttributeBean attrList;

    public int getIdPeriod() {
        return idPeriod;
    }

    public void setIdPeriod(int idPeriod) {
        this.idPeriod = idPeriod;
    }

    public AttributeBean getAttrList() {
        return attrList;
    }

    public void setAttrList(AttributeBean attrList) {
        this.attrList = attrList;
    }
    
}
