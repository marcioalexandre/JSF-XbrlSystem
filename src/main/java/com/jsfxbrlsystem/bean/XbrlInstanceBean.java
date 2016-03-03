/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.log4j.Logger;
/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 */
@Entity
@Table( name = "XbrlInstanceBean" )
public class XbrlInstanceBean  {
    //log
    private static final Logger logger = Logger.getLogger(XbrlInstanceBean.class);
    //attributes
    @Id
    @GeneratedValue
    private int idInstance;
    @Column
    private Document doc;
    @Column
    private RootBean root; // 0..1 (eg.: xbrl)
    @OneToMany (cascade = {CascadeType.ALL},mappedBy = "SimpleLinkBean",
            targetEntity = com.jsfxbrlsystem.bean.SimpleLinkBean.class)
    private ArrayList<SimpleLinkBean> simplelinkList; // 0..N. (ex.: SchemaRef, roleref ...)
    @OneToMany (cascade = {CascadeType.ALL},mappedBy = "ContextBean",
            targetEntity = com.jsfxbrlsystem.bean.ContextBean.class)
    private ArrayList<ContextBean> contextList; //0..N
    @OneToMany (cascade = {CascadeType.ALL},mappedBy = "ElementBean",
            targetEntity = com.jsfxbrlsystem.bean.ElementBean.class)
    private ArrayList<ElementBean> elementList; //0..N
    @OneToMany (cascade = {CascadeType.ALL},mappedBy = "ExtendedLinkbaseDocumentBean",
            targetEntity = com.jsfxbrlsystem.bean.ExtendedLinkbaseDocumentBean.class)
    private ArrayList<ExtendedLinkbaseDocumentBean> extendedlinkbaseList; //footnotelink; //0..1
    //XBRL Regular Expressions
    private String reXmlLine = "^(?!<[link:.*|xbrl.*])((<\\w*[-]{1}\\w*:\\w*\\s.*>.*)|(<\\w*:\\w*\\s.*>.*))"; 
    private String reElementName  = "(?<=:)\\w+\\s";
    private String reContextRef = "((?<=context[R|r]ef\\p{Punct}{1}).+[\"\'])"; 
    private String reUnitRef = "((?<=unit[R|r]ef\\p{Punct}{1}).+[\"\'])";
    private String reDecimals = "((?<=decimals\\p{Punct}{1}).+[\"\'])";
    private String reId = "(?<=id=[\"\']).*(?=[\"\'])";	
    

    //methods
    public XbrlInstanceBean(){
        //it is used by Hibernate
    }
    
    public XbrlInstanceBean(Document doc, RootBean root, 
            ArrayList<SimpleLinkBean> slink, ArrayList<ContextBean> con, ArrayList<ElementBean> ele,
                    ArrayList<ExtendedLinkbaseDocumentBean> elink){
        //it is used by JUnit
        this.doc = doc;
        this.root = root;
        this.simplelinkList = slink;
        this.contextList = con;
        this.elementList = ele;
        this.extendedlinkbaseList = elink;
    }
    
    public int getIdInstance() {
        return idInstance;
    }

    public void setIdInstance(int idInstance) {
        this.idInstance = idInstance;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }
    
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
     * @reason: this method has a very low performance and wrong result
     * @description: This method loads all data from XML to Java variables through XPath Technology.
     */
    public void getElementFromDocXPath(Document doc) throws XPathExpressionException, Exception{
        logger.info("Starting getElementFromDoc...");
        XPath xPath = XPathFactory.newInstance().newXPath();
        String eleXPath = "(//xbrl/*[@contextRef != '']/(@*|text()))";
        NodeList nodeList = (NodeList) xPath.evaluate(eleXPath, doc, XPathConstants.NODESET);
        ArrayList<ElementBean> eleBeanList = new ArrayList<ElementBean>();
        ElementBean eleBean;
        logger.info("Starting processing...");
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
        logger.info("...processing is done!");
        logger.info(doc.toString());
        logger.info("Performance Time (milliseconds): "+(end-start));
    }
           
    /**
     * @param br
     * @param doc
     * @description: This method loads all elements and company name (from context/entity/identifier) of XBRL intance
     */
    public void getElementsFromDoc_BR(BufferedReader br, Document doc) throws IOException, XPathExpressionException {
        logger.info("getElementsFromDoc_BR method");
        long start = System.currentTimeMillis();
        long end = 0;
        XPath xPath = XPathFactory.newInstance().newXPath();
        ArrayList<ElementBean> endEleList = new ArrayList<ElementBean>();
        ArrayList<ElementBean> tempEleList = this.getSomeXbrlAttr(br);
        //get data through XPath 1.0 with above taken data
        try{
            for (ElementBean ele: tempEleList){
                xPath = XPathFactory.newInstance().newXPath();
                String xp = "(//xbrl/"+ele.getName();
                if (ele.getContextRef() != null){
                    xp += "[@contextRef = '"+ele.getContextRef()+"']";
                }
                if (ele.getId() != null){
                    xp += "[@id = '"+ele.getId()+"']";
                }
                if (ele.getDecimals() != null){
                    xp += "[@decimals = '"+ele.getDecimals()+"']";
                }
                if (ele.getUnitRef() != null){
                    xp += "[@unitRef = '"+ele.getUnitRef()+"']";
                }
                xp += "/text())";
                Node node = (Node) xPath.evaluate(xp, doc, XPathConstants.NODE);
                if (node != null)
                    ele.setValue(node.getNodeValue());
                endEleList.add(ele);
            }
        }catch(Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        //getting contexts (this have to be a while/for, because there are many of them
        ArrayList<ContextBean> contextList = new ArrayList<ContextBean>();
        ContextBean context = new ContextBean();
        EntityBean entity = new EntityBean();
        IdentifierBean identifier = new IdentifierBean();
        String schemexp = "//xbrl/context/entity/identifier/@scheme";
        Node snode = (Node) xPath.evaluate(schemexp, doc, XPathConstants.NODE);
        if (snode != null)
            identifier.setSchema(snode.getNodeValue());
        String idxp = "//xbrl/context/entity/identifier/text()";
        Node inode = (Node) xPath.evaluate(idxp, doc, XPathConstants.NODE);
        if (inode != null)
            identifier.setValue(inode.getNodeValue());
        entity.setIdentifier(identifier);
        context.setEntitity(entity);
        contextList.add(context);

        this.contextList = contextList;
        this.elementList = endEleList;
        
        end = System.currentTimeMillis();
        logger.info("Performance Time (milliseconds): "+(end-start));

    }

    
    /**
     * @description: This method get element name, contextRef, unitRef, id, decimals attr from SEC files
     */
    public ArrayList<ElementBean> getSomeXbrlAttr(BufferedReader br) {
        logger.info("get first xbrl elements - getSomeXbrlAttr");
        ArrayList<ElementBean> eleList = new ArrayList<ElementBean>();
        //getting some data through Regular Expressions
        try{
            int j=1;
            String currentLine;
            if (br != null){
                while ((currentLine = br.readLine()) != null){
                    currentLine = currentLine.trim();
                    ElementBean ele = new ElementBean();
                    Pattern p = Pattern.compile(this.reXmlLine);
                    Matcher m = p.matcher(currentLine);
                    if (m.find()){
                        ele.setNumber(j);
                        String line = m.group();
                        Pattern pname = Pattern.compile(this.reElementName);
                        Matcher mname = pname.matcher(line);
                        if (mname.find()){
                                ele.setName(mname.group().trim());
                        }else{	}
                        // setting element id
                        Pattern pid = Pattern.compile(this.reId);
                        Matcher mid = pid.matcher(line);
                        if (mid.find()){
                                String[] rid = mid.group().split("\\s");
                                rid[0] = rid[0].replace("\"","");
                                rid[0] = rid[0].replace("\'","");
                                rid[0] = rid[0].replace(">&lt;div",""); 
                                ele.setId(rid[0].trim());
                        }else{	}
                        //setting Element Context_Ref
                        Pattern pcr = Pattern.compile(this.reContextRef);
                        Matcher mcr = pcr.matcher(line);
                        if (mcr.find()){
                                String[] rcr = mcr.group().split("\\s");
                                rcr[0] = rcr[0].replace("\"","");
                                rcr[0] = rcr[0].replace("\'","");
                                ele.setContextRef(rcr[0].trim());
                        }else{  }
                        //setting Element Unit_Ref
                        Pattern pur	= Pattern.compile(this.reUnitRef);
                        Matcher mur	= pur.matcher(line);
                        if (mur.find()){
                                String[] rur	=	mur.group().split("\\s"); 
                                rur[0]	= rur[0].replace("\"","");
                                rur[0]	= rur[0].replace("\'","");
                                ele.setUnitRef(rur[0].trim());
                        }
                        //setting Element Decimals
                        Pattern pd	= Pattern.compile(this.reDecimals);
                        Matcher md	= pd.matcher(line);
                        if (md.find()){
                                String[] rd = md.group().split("\\s");
                                rd[0] = rd[0].replace("\"","");
                                rd[0] = rd[0].replace("\'","");
                                ele.setDecimals(rd[0].trim());
                        }
                        eleList.add(ele);
                        j++;
                    }else{
                        logger.error("Problem [line "+j+": "+currentLine+", RE: "+this.reXmlLine+"]");
                    }
                }
                
            }else{
                logger.error("Error: getSomeXbrlAttr");
                throw new Exception("There were some problems in this processing");
            }
        }catch(Exception e){
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return eleList;
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