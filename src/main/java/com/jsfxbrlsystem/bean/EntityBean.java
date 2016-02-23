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
public class EntityBean {
    @Id
    @GeneratedValue
    private int idEntity;
    private IdentifierBean identifier;
    private SegmentBean segment;

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }

    public IdentifierBean getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierBean identifier) {
        this.identifier = identifier;
    }

    public SegmentBean getSegment() {
        return segment;
    }

    public void setSegment(SegmentBean segment) {
        this.segment = segment;
    }
    
    
}
