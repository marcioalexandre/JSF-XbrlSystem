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
public class XbrlRegularExpressionBean {
    private String xmlLine; 
    private String elementName;
    private String contextRef; 
    private String unitRef;
    private String decimals;
    private String id;	

    public XbrlRegularExpressionBean(){
        this.xmlLine 	= "^(?!<[link:.*|xbrl.*|\\p{Punct}.*|segment.*|context.*])((<.*[-]{1}.*:.*>.*)|(<.*:.*>.*))$";
        this.elementName = "(?<=:)\\w+\\s";
        this.contextRef  = "((?<=context[R|r]ef\\p{Punct}{1}).+[\"\'])";
        this.unitRef	= "((?<=unit[R|r]ef\\p{Punct}{1}).+[\"\'])";
        this.decimals	= "((?<=decimals\\p{Punct}{1}).+[\"\'])";
        this.id		= "(?<=id=[\"\']).*(?=[\"\'])";
    }    

    public String getXmlLine() {
        return xmlLine;
    }
    
    public String getElementName() {
        return elementName;
    }

    public String getContextRef() {
        return contextRef;
    }

    public String getUnitRef() {
        return unitRef;
    }

    public String getDecimals() {
        return decimals;
    }

    public String getId() {
        return id;
    }

    
    
}
