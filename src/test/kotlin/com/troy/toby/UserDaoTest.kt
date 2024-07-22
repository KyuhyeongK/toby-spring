package com.troy.toby

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class UserDaoTest : StringSpec({

    "add 한 유저를 get 하는지 테스트한다" {
        val context = AnnotationConfigApplicationContext(DaoFactory::class.java)
        val userDao = context.getBean("userDao", UserDao::class.java)

        // 테스트 시작 전 deleteAll 처리
        userDao.deleteAll()
        userDao.getCount() shouldBe 0

        val addUser = User("id", "troy", "troy")
        userDao.add(addUser)
        val getUser = userDao.get("id")

        getUser.id shouldBe addUser.id
        getUser.name shouldBe addUser.name
        getUser.password shouldBe addUser.password
    }

    "deleteAll과 getCount를 테스트한다" {
        val context = AnnotationConfigApplicationContext(DaoFactory::class.java)
        val userDao = context.getBean("userDao", UserDao::class.java)

        userDao.deleteAll()
        userDao.getCount() shouldBe 0

        val user1 = User("user1", "user1", "user1")
        val user2 = User("user2", "user2", "user2")
        val user3 = User("user3", "user3", "user3")

        userDao.add(user1)
        userDao.getCount() shouldBe 1

        userDao.add(user2)
        userDao.getCount() shouldBe 2

        userDao.add(user3)
        userDao.getCount() shouldBe 3
    }

})