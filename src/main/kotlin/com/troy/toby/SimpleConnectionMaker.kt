package com.troy.toby

import java.sql.Connection
import java.sql.DriverManager

class SimpleConnectionMaker : ConnectionMaker {
    override fun makeConnection(): Connection {
        Class.forName("com.mysql.cj.jdbc.Driver")
        val conn = DriverManager.getConnection("jdbc:mysql://localhost/toby", "toby", "toby")
        return conn
    }
}