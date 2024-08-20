package com.troy.toby

data class User(
    var id: String,
    var name: String,
    var password: String,
    var level: Level = Level.BASIC,
    var loginCount: Int = 0,
    var recommendedCount: Int = 0,
)