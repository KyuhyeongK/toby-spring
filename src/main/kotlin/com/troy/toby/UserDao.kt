package com.troy.toby

import javax.sql.DataSource

class UserDao(
    private val dataSource: DataSource,
    private val jdbcContext: JdbcContext,
) {
    fun add(user: User) {
        dataSource.connection.use { conn ->
            conn.prepareStatement(
                """
            insert into user_m (user_id, name, password)
            values (?, ?, ?)
        """.trimIndent()
            ).use { psmt ->
                psmt.setString(1, user.id)
                psmt.setString(2, user.name)
                psmt.setString(3, user.password)
                psmt.executeUpdate()
            }
        }

    }

    fun get(id: String): User {
        dataSource.connection.use { conn ->
            conn.prepareStatement(
                """
            select user_id, name, password
            from user_m
            where user_id = ?
        """.trimIndent()
            ).use { psmt ->
                psmt.setString(1, id)
                val rs = psmt.executeQuery()

                var user: User? = null
                if (rs.next()) {
                    user = User(rs.getString("user_id"), rs.getString("name"), rs.getString("password"))
                }

                if (user == null) {
                    throw IllegalArgumentException("not found")
                }

                return user
            }
        }
    }

    fun deleteAll() {
        jdbcContext.executeSql("delete from user_m")
    }

    fun getCount(): Int {
        dataSource.connection.use { conn ->
            conn.prepareStatement(
                """
            select count(*) from user_m
        """.trimIndent()
            ).use { psmt ->
                val rs = psmt.executeQuery()
                rs.next()
                val count = rs.getInt(1)
                return count
            }
        }
    }
}