package com.troy.toby

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
class DaoFactory {
    @Bean
    fun userDao(): UserDao {
        return UserDaoJdbc(JdbcTemplate(dataSource()))
    }

    @Bean
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .url("jdbc:mysql://localhost:3306/toby")
            .username("toby")
            .password("toby")
            .build()

    }
}