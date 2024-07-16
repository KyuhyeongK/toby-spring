package com.troy.toby

class DaoFactory {
    fun userDao(): UserDao {
        return UserDao(connectionMaker())
    }

    private fun connectionMaker(): ConnectionMaker {
        return SimpleConnectionMaker()
    }
}