package com.troy.toby

import java.sql.Connection
import java.sql.DriverManager

class SimpleConnectionMaker {
    fun makeNewConnection(): Connection {
        Class.forName("com.mysql.cj.jdbc.Driver")
        val conn = DriverManager.getConnection("jdbc:mysql://localhost/toby", "toby", "toby")
        return conn
    }
}