package com.troy.toby

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DaoFactory {
    @Bean
    fun userDao(): UserDao {
        return UserDao(connectionMaker())
    }

    @Bean
    fun connectionMaker(): ConnectionMaker {
        return SimpleConnectionMaker()
    }
}