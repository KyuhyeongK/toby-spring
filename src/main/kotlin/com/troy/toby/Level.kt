package com.troy.toby

enum class Level(val code: String) {
    BASIC("01"),
    SILVER("02"),
    GOLD("03"),
    ;

    companion object {
        fun valueOfCode(code: String): Level {
            return when (code) {
                "01" -> BASIC
                "02" -> SILVER
                "03" -> GOLD
                else -> throw IllegalArgumentException("Invalid level code: $code")
            }
        }
    }
}