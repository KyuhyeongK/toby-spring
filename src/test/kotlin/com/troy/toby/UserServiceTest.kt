package com.troy.toby

import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [DaoFactory::class])
class UserServiceTest(
    @Autowired val userService: UserService,
    @Autowired val userDao: UserDao,
) : StringSpec({

    extensions(SpringExtension)

    lateinit var users: List<User>

    beforeTest {
        users = listOf(
            User("user1", "user1", "user1", Level.BASIC, 49, 0),
            User("user2", "user2", "user2", Level.BASIC, 50, 0),
            User("user3", "user3", "user3", Level.SILVER, 60, 29),
            User("user4", "user4", "user4", Level.SILVER, 30, 30),
            User("user5", "user5", "user5", Level.GOLD, 100, 100),
        )
    }

    "유저 레벨 일괄 변경 테스트" {
        userDao.deleteAll()

        users.forEach { userDao.add(it) }

        userService.upgradeLevels()

        userDao.get(users[0].id).level shouldBe Level.BASIC
        userDao.get(users[1].id).level shouldBe Level.SILVER
        userDao.get(users[2].id).level shouldBe Level.SILVER
        userDao.get(users[3].id).level shouldBe Level.GOLD
        userDao.get(users[4].id).level shouldBe Level.GOLD

    }
})