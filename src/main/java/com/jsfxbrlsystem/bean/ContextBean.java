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
public class ContextBean {
    @Id
    @GeneratedValue
    private int idContext;
    private EntityBean entitity;
    private PeriodBean period;

    public EntityBean getEntitity() {
        return entitity;
    }

    public void setEntitity(EntityBean entitity) {
        this.entitity = entitity;
    }

    public PeriodBean getPeriod() {
        return period;
    }

    public void setPeriod(PeriodBean period) {
        this.period = period;
    }
    
    
}
