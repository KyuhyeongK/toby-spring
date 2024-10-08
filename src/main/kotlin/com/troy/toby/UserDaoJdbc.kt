package com.troy.toby

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper

class UserDaoJdbc(
    private val jdbcTemplate: JdbcTemplate,
) : UserDao {

    private val userMapper = RowMapper { rs, _ ->
        User(
            rs.getString("user_id"),
            rs.getString("name"),
            rs.getString("password"),
            Level.valueOfCode(rs.getString("level")),
            rs.getInt("login_cnt"),
            rs.getInt("recommended_cnt")
        )
    }

    override fun add(user: User) {
        jdbcTemplate.update(
            """
            insert into user_m (user_id, name, password, level, login_cnt, recommended_cnt)
            values (?, ?, ?, ?, ?, ?)
        """.trimIndent(), user.id, user.name, user.password, user.level.code, user.loginCount, user.recommendedCount
        )
    }

    override fun get(id: String): User {
        try {
            return jdbcTemplate.queryForObject(
                """
            select user_id, name, password, level, login_cnt, recommended_cnt
            from user_m 
            where user_id = ?
            """.trimIndent(), userMapper, id
            )!!
        } catch (e: EmptyResultDataAccessException) {
            throw IllegalArgumentException("not found")
        }

    }

    override fun deleteAll() {
        jdbcTemplate.update("delete from user_m")
    }

    override fun getCount(): Int {
        return jdbcTemplate.queryForObject("select count(*) from user_m", Int::class.java) ?: 0
    }

    override fun getAll(): List<User> {
        return jdbcTemplate.query(
            "select * from user_m order by user_id", userMapper
        )
    }

    override fun update(user: User) {
        jdbcTemplate.update(
            """
                update user_m 
                set name = ?
                , password = ?
                , level = ?
                , login_cnt = ?
                , recommended_cnt = ?
                where user_id = ?
            """.trimIndent(), user.name, user.password, user.level.code, user.loginCount, user.recommendedCount, user.id
        )
    }
}