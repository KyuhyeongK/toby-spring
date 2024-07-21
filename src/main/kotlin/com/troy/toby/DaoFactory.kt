package com.troy.toby

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DaoFactory {
    @Bean
    fun userDao(): UserDao {
        return UserDao(dataSource())
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