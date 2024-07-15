package com.troy.toby

import java.sql.Connection

interface ConnectionMaker {
    fun makeConnection(): Connection
}