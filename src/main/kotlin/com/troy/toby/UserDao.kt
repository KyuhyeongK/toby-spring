package com.troy.toby

import java.sql.Connection

abstract class UserDao {
    fun add(user: User) {
        val conn = getConnection()
        val psmt = conn.prepareStatement("""
            insert into user_m (user_id, name, password)
            values (?, ?, ?)
        """.trimIndent())
        psmt.setString(1, user.id)
        psmt.setString(2, user.name)
        psmt.setString(3, user.password)
        psmt.executeUpdate()

        psmt.close()
        conn.close()
    }

    fun get(id: String): User {
        val conn = getConnection()
        val psmt = conn.prepareStatement("""
            select user_id, name, password
            from user_m
        """.trimIndent())
        val rs = psmt.executeQuery()
        rs.next()
        val user = User(rs.getString("user_id"), rs.getString("name"), rs.getString("password"))
        rs.close()
        psmt.close()
        conn.close()
        return user
    }

    abstract fun getConnection(): Connection

}