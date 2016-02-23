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
public class AttributeBean {
    @Id
    @GeneratedValue
    private int idAttribute;
    private String label; //Attribute name/label
    private String value; //Attribute value (it can be a link ou another value type

    public int getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(int idAttribute) {
        this.idAttribute = idAttribute;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}
