package com.troy.toby

enum class Level(val code: String, val nextLevel: Level?) {
    GOLD("03", null),
    SILVER("02", GOLD),
    BASIC("01", SILVER),
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