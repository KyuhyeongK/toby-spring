package com.troy.toby

class User(
    var id: String,
    var name: String,
    var password: String,
    var level: Level = Level.BASIC,
    var loginCount: Int = 0,
    var recommendedCount: Int = 0,
) {
    fun upgradeLevel() {
        level.nextLevel?.let { nextLevel ->
            level = nextLevel
        } ?: throw IllegalStateException("${level} 레벨은 업그레이드 불가")
    }
}
