package com.troy.toby

import javax.sql.DataSource

class UserDao(
    private val dataSource: DataSource,
) {
    fun add(user: User) {
        val conn = dataSource.connection
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
        val conn = dataSource.connection
        val psmt = conn.prepareStatement("""
            select user_id, name, password
            from user_m
            where user_id = ?
        """.trimIndent())
        psmt.setString(1, id)
        val rs = psmt.executeQuery()
        rs.next()
        val user = User(rs.getString("user_id"), rs.getString("name"), rs.getString("password"))
        rs.close()
        psmt.close()
        conn.close()
        return user
    }

    fun deleteAll() {
        val conn = dataSource.connection
        val psmt = conn.prepareStatement(
            """
            delete from user_m
        """.trimIndent()
        )
        psmt.executeUpdate()

        psmt.close()
        conn.close()
    }

    fun getCount(): Int {
        val conn = dataSource.connection
        val psmt = conn.prepareStatement(
            """
            select count(*) from user_m
        """.trimIndent()
        )
        val rs = psmt.executeQuery()
        rs.next()
        val count = rs.getInt(1)

        rs.close()
        psmt.close()
        conn.close()
        return count
    }
}