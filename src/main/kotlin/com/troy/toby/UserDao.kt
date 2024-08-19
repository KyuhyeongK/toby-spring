package com.troy.toby

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate

class UserDao(
    private val jdbcTemplate: JdbcTemplate,
) {
    fun add(user: User) {
        jdbcTemplate.update(
            """
            insert into user_m (user_id, name, password)
            values (?, ?, ?)
        """.trimIndent(), user.id, user.name, user.password
        )
    }

    fun get(id: String): User {
        try {
            return jdbcTemplate.queryForObject(
                """
            select user_id, name, password 
            from user_m 
            where user_id = ?
            """.trimIndent(), { rs, _ ->
                    User(rs.getString("user_id"), rs.getString("name"), rs.getString("password"))
                }, id
            )!!
        } catch (e: EmptyResultDataAccessException) {
            throw IllegalArgumentException("not found")
        }

    }

    fun deleteAll() {
        jdbcTemplate.update("delete from user_m")
    }

    fun getCount(): Int {
        return jdbcTemplate.queryForObject("select count(*) from user_m", Int::class.java) ?: 0
    }
}