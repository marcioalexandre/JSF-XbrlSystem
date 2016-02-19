/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.bean;

/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */
public class ContextBean {
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
