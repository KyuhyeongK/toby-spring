package com.troy.toby

class DaoFactory {
    fun userDao(): UserDao {
        val connectionMaker = SimpleConnectionMaker()
        return UserDao(connectionMaker)
    }
}