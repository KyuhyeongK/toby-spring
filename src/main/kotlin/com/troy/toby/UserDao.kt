package com.troy.toby

interface UserDao {

    fun add(user: User)

    fun get(id: String): User

    fun getAll(): List<User>

    fun deleteAll()

    fun getCount(): Int

    fun update(user: User)
}