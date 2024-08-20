package com.troy.toby

import org.springframework.stereotype.Service

private const val MIN_LOGIN_COUNT_FOR_SILVER = 50
private const val MIN_RECOMMENDED_COUNT = 30

@Service
class UserService(
    private val userDao: UserDao,
) {
    fun upgradeLevels() {
        userDao.getAll().forEach { user ->
            if (canUpgrade(user)) {
                upgradeLevel(user)
            }

        }
    }

    private fun canUpgrade(user: User): Boolean {
        return when (user.level) {
            Level.BASIC -> user.loginCount >= MIN_LOGIN_COUNT_FOR_SILVER
            Level.SILVER -> user.recommendedCount >= MIN_RECOMMENDED_COUNT
            Level.GOLD -> false
        }
    }

    private fun upgradeLevel(user: User) {
        user.upgradeLevel()
        userDao.update(user)
    }

}