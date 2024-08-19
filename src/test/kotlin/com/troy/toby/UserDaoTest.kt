package com.troy.toby

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [DaoFactory::class])
class UserDaoTest(
    @Autowired val userDao: UserDao,
) : StringSpec({

    extensions(SpringExtension)

    lateinit var user1: User
    lateinit var user2: User
    lateinit var user3: User

    beforeTest {
        user1 = User("user1", "user1", "user1")
        user2 = User("user2", "user2", "user2")
        user3 = User("user3", "user3", "user3")
    }

    "add 한 유저를 get 하는지 테스트한다" {
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
        userDao.deleteAll()
        userDao.getCount() shouldBe 0

        userDao.add(user1)
        userDao.getCount() shouldBe 1

        userDao.add(user2)
        userDao.getCount() shouldBe 2

        userDao.add(user3)
        userDao.getCount() shouldBe 3
    }

    "get 메서드 notFound 를 테스트한다" {
        userDao.deleteAll()
        shouldThrow<IllegalArgumentException> { userDao.get("unknown") }
    }

    "전체 멤버를 조회하는 getAll을 테스트한다" {
        userDao.deleteAll()

        val users0 = userDao.getAll()
        users0.size shouldBe 0

        userDao.add(user1)
        val users1: List<User> = userDao.getAll()
        users1.size shouldBe 1
        checkSameUser(user1, users1.first())

        userDao.add(user2)
        val users2: List<User> = userDao.getAll()
        users2.size shouldBe 2
        checkSameUser(user1, users2.first())
        checkSameUser(user2, users2[1])

        userDao.add(user3)
        val users3: List<User> = userDao.getAll()
        users3.size shouldBe 3
        checkSameUser(user1, users3.first())
        checkSameUser(user2, users3[1])
        checkSameUser(user3, users3.last())
    }

})

private fun checkSameUser(user1: User, user2: User) {
    user1.id shouldBe user2.id
    user1.name shouldBe user2.name
    user1.password shouldBe user2.password

}
