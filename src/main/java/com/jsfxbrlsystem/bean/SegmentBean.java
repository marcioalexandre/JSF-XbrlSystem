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
public class SegmentBean {
    @Id
    @GeneratedValue
    private int idSegment;
    private ExplicitMemberBean explicitMember;

    public int getIdSegment() {
        return idSegment;
    }

    public void setIdSegment(int idSegment) {
        this.idSegment = idSegment;
    }

    public ExplicitMemberBean getExplicitMember() {
        return explicitMember;
    }

    public void setExplicitMember(ExplicitMemberBean explicitMember) {
        this.explicitMember = explicitMember;
    }
    
    
}
