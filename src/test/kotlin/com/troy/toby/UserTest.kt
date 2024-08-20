package com.troy.toby

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UserTest : StringSpec({

    lateinit var user: User

    beforeTest {
        user = User("id", "name", "password")
    }

    "레벨 업그레이드 테스트" {
        Level.entries
            .filter { it.nextLevel != null }
            .forEach {
                user.level = it
                user.upgradeLevel()
                user.level shouldBe it.nextLevel
            }
    }

    "레벨 업그레이드 불가 테스트" {
        Level.entries
            .find { it.nextLevel == null }
            ?.let {
                user.level = it
                shouldThrow<IllegalStateException> {
                    user.upgradeLevel()
                }
            }
    }


})