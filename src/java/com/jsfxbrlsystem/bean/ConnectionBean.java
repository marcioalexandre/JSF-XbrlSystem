/*
 * Copyright 2016 http://xbrlframework.com/
 * Licensed under the Apache License, Version 2.0 (the "License");
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package com.jsfxbrlsystem.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Marcio Alexandre P. da Silva <email: marcio.alexandre83@gmail.com>
 * 
 * This is a simple connection class with MySql
 */
public class ConnectionBean {
    private static Connection connection;
    public Connection call() throws SQLException {
	try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String server = "127.0.0.1:3306"; // computer:port
            String dbname = "xbrldb";
            String user = "marcio@xbrlframewor_kXYW73oirWOQAZOILHKJ45q";
            String password = "1234";
            String url = "jdbc:mysql://"+server+"/"+dbname+"?user="+user+"&password="+password;
            connection = DriverManager.getConnection(url);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }catch (InstantiationException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
        return connection;
    }

}