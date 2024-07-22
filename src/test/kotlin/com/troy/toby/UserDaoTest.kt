package com.troy.toby

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class UserDaoTest : StringSpec({

    "add 한 유저를 get 하는지 테스트한다" {
        val context = AnnotationConfigApplicationContext(DaoFactory::class.java)
        val userDao = context.getBean("userDao", UserDao::class.java)

        val addUser = User("id", "troy", "troy")
        userDao.add(addUser)
        val getUser = userDao.get("id")

        getUser.id shouldBe addUser.id
        getUser.name shouldBe addUser.name
        getUser.password shouldBe addUser.password
    }

})