package com.troy.toby

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userDao: UserDao,
) {
    fun upgradeLevels() {
        userDao.getAll().forEach { user ->
            if (user.level == Level.BASIC && user.loginCount >= 50) {
                user.level = Level.SILVER
                userDao.update(user)
            } else if (user.level == Level.SILVER && user.recommendedCount >= 30) {
                user.level = Level.GOLD
                userDao.update(user)
            }

        }
    }

}