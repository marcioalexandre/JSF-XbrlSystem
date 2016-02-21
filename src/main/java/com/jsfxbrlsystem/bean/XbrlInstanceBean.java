/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Attr;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */

public class XbrlInstanceBean {
    private Document doc;
    private RootBean root; // 0..1 (eg.: xbrl)
    private ArrayList<SimpleLinkBean> simplelinkList; // 0..N. (ex.: SchemaRef, roleref ...)
    private ArrayList<ContextBean> contextList; //0..N
    private ArrayList<ElementBean> elementList; //0..N
    private ArrayList<ExtendedLinkbaseDocumentBean> extendedlinkbaseList; //footnotelink; //0..1

    public RootBean getRoot() {
        return root;
    }

    public void setRoot(RootBean root) {
        this.root = root;
    }

    public ArrayList<SimpleLinkBean> getSimplelinkList() {
        return simplelinkList;
    }

    public void setSimplelinkList(ArrayList<SimpleLinkBean> simplelinkList) {
        this.simplelinkList = simplelinkList;
    }

    public ArrayList<ContextBean> getContextList() {
        return contextList;
    }

    public void setContextList(ArrayList<ContextBean> contextList) {
        this.contextList = contextList;
    }

    public ArrayList<ElementBean> getElementList() {
        return elementList;
    }

    public void setElementList(ArrayList<ElementBean> elementList) {
        this.elementList = elementList;
    }

    public ArrayList<ExtendedLinkbaseDocumentBean> getExtendedlinkbaseList() {
        return extendedlinkbaseList;
    }

    public void setExtendedlinkbaseList(ArrayList<ExtendedLinkbaseDocumentBean> extendedlinkbaseList) {
        this.extendedlinkbaseList = extendedlinkbaseList;
    }
       
    /**
     * @deprecated 
     * @reason: this method has a very low performance
     * @description: This method loads all data from XML to Java variables through XPath Technology.
     */
    public void getElementFromDocXPath(Document doc) throws XPathExpressionException, Exception{
        System.out.println("Starting getElementFromDoc...");
         XPath xPath = XPathFactory.newInstance().newXPath();
            String eleXPath = "(//xbrl/*[@contextRef != '']/(@*|text()))";
            NodeList nodeList = (NodeList) xPath.evaluate(eleXPath, doc, XPathConstants.NODESET);
            ArrayList<ElementBean> eleBeanList = new ArrayList<ElementBean>();
            ElementBean eleBean;
            System.out.println("Starting processing...");
            long start = System.currentTimeMillis();
            long end = 0;
            for(int i=0; i < nodeList.getLength(); i++){
                eleBean = new ElementBean();  
                eleBean.setNumber(i+1);
                eleBean.setName(nodeList.item(i).getNodeName());
                                
                String crXPath = "(//xbrl/"+eleBean.getName()+"/@contextRef)";
                Node crNode = (Node) xPath.evaluate(crXPath, doc, XPathConstants.NODE);
                if (crNode != null)
                    eleBean.setContextRef(crNode.getNodeValue());
      
                
                String dXPath = "(//xbrl/"+eleBean.getName()+"/@decimals)";
                Node dNode = (Node) xPath.evaluate(dXPath, doc, XPathConstants.NODE);
                if (dNode != null)
                    eleBean.setDecimals(dNode.getNodeValue());
                
                String idXPath = "(//xbrl/"+eleBean.getName()+"/@id)";
                Node idNode = (Node) xPath.evaluate(idXPath, doc, XPathConstants.NODE);
                if (idNode != null)
                    eleBean.setId(idNode.getNodeValue());
                
                String urXPath = "(//xbrl/"+eleBean.getName()+"/@unitRef)";
                Node urNode = (Node) xPath.evaluate(urXPath, doc, XPathConstants.NODE);
                if (urNode != null)
                    eleBean.setUnitRef(urNode.getNodeValue());
                              
                String vXPath = "(//xbrl/"+eleBean.getName()+"/text())";
                Node vNode = (Node) xPath.evaluate(vXPath, doc, XPathConstants.NODE);
                if (vNode != null)
                    eleBean.setValue(vNode.getNodeValue());
                
                //eleBean.print();    
                
                eleBeanList.add(eleBean);
               
            }
            this.elementList = eleBeanList;
            end = System.currentTimeMillis();
            System.out.println("...processing is done!");
            System.out.println(doc.toString());
            System.out.print("Performance Time (milliseconds): "+(end-start));
    }
    
    public void getElementsFromDoc(Document doc) throws XPathExpressionException, Exception{
        // http://stackoverflow.com/questions/2460592/xpath-how-to-get-all-the-attribute-names-and-values-of-an-element
        // http://stackoverflow.com/questions/11863038/how-to-get-the-attribute-value-of-an-xml-node-using-java
        System.out.println("Starting getElementsFromDoc...");

        XPath xPath = XPathFactory.newInstance().newXPath();
        ArrayList<ElementBean> eleList = new ArrayList<ElementBean>();
        // String xp = "(//xbrl/*[@contextRef != '']/(@*|text()))"; //xpath 2.0
        String xp = "(//xbrl/*[@contextRef != ''])";
        NodeList nl = (NodeList) xPath.evaluate(xp, doc, XPathConstants.NODESET);
        ElementBean ele;
        long start = System.currentTimeMillis();
        long end = 0;
        int size = nl.getLength();
        for (int i=0; i<nl.getLength(); i++){
            //laço para carregar todos os atributos do elemento
            ele = new ElementBean();
            ele.setNumber(i+1);
            ele.setName(nl.item(i).getNodeName());
            NamedNodeMap nl2 = nl.item(i).getAttributes();
            //System.out.println("Attrs number: "+nl2.getLength());
            for (int j=0; j<nl2.getLength();j++){
                Attr attr = (Attr) nl2.item(j);
                String name = attr.getName().trim();
                String value = attr.getValue().trim();
                //System.out.println("Attr name: "+name+", value: "+value);
                if (name.equals("contextRef"))
                    ele.setContextRef(value);
                else if (name.equals("id"))
                    ele.setId(value);
                else if (name.equals("decimals"))
                    ele.setDecimals(value);
                else if (name.equals("unitRef"))
                    ele.getUnitRef();
            }
            
        
            String elexp = "//xbrl/*";
            if (ele.getContextRef() != null){
                elexp += "[@contextRef = '"+ele.getContextRef()+"']";
            }
            if (ele.getId() != null){
                elexp += "[@id = '"+ele.getId()+"']";
            }
            if (ele.getDecimals() != null){
                elexp += "[@decimals = '"+ele.getDecimals()+"']";
            }
            if (ele.getUnitRef() != null){
                elexp += "[@unitRef = '"+ele.getUnitRef()+"']";
            }
            //System.out.println("xpath to Get name:"+elexp);
            //System.out.println(elexp);
            Node n = (Node) xPath.evaluate(elexp, doc, XPathConstants.NODE);
            ele.setName(n.getNodeName());

            elexp = elexp.replace("*", ele.getName());
            String valuexp = elexp+"/text()";
            //System.out.println(valuexp);
            Node n2 = (Node) xPath.evaluate(valuexp, doc, XPathConstants.NODE);
            if (n2 != null){
                ele.setValue(n2.getNodeValue());
            }
            eleList.add(ele);
        }
        this.elementList = eleList;
        //System.out.println("Element number: "+size);
        //System.out.println("Element number in list: "+eleList.size());
        end = System.currentTimeMillis();
        System.out.print("Performance Time (milliseconds): "+(end-start));
    }
        
    /**
     * @description: This method loads all data from XML to Java variables through Zorba library.
     */
    public void getElementFromDocJson(Document doc){
        //http://docs.zorba.io.s3-website-us-east-1.amazonaws.com/3.0.0/java/Test_Zorba_8java-example.html
        //http://rabidgadfly.com/2013/02/angular-and-xml-no-problem/ 
    }
    
    /**
     * @description: This method have to be build according your needs, it's just a template
     */
    public void saveRDBMS(){
        try{
            ConnectionBean connBean = new ConnectionBean();
            Connection conn = connBean.call();
            String query = "";
            for (ElementBean ele: this.getElementList()){
                //the following query is just a example
                query += "insert into \"AccountingElement\" (id,name,value,contextRef) "
                        + "values ("+ele.getId()+","+ele.getName()+","+ele.getValue()+","+ele.getContextRef()+");"; 
            }
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            conn.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
     /**
     * @description: This method have to be build according your needs, it's just a template
     */
    public void saveXbrl(){     
        System.out.println("saveXbrl method processing...");
    }
    
    /**
     * @description: This method have to be build according your needs, it's just a template
     */
    public void saveOther(){
        System.out.println("saveOther method processing...");
    }
    
    /**
     * @description: This method have to be build according your needs, it's just a template
     */
    public void saveJson(){
        System.out.println("saveJson method processing...");
    }
           
}
/**
 * Documentation Java Xquery: https://docs.oracle.com/javase/7/docs/api/javax/xml/xpath/package-summary.html
 * 
 * This XQuery documentations can help you in others queries:
 *  * http://exist-db.org/exist/apps/demo/examples/basic/basics.html
 * http://stackoverflow.com/questions/14042942/xpath-where-clause
 * http://homepages.inf.ed.ac.uk/wadler/papers/xquery-tutorial/xquery-tutorial.pdf
 * http://www.w3schools.com/xsl/xquery_intro.asp
 * 
 * Documentation for oracle Xquery solution: https://docs.oracle.com/database/121/ADXDK/adx_j_xqj.htm#ADXDK109
 */