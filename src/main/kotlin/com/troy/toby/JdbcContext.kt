package com.troy.toby

import java.sql.Connection
import java.sql.PreparedStatement
import javax.sql.DataSource

class JdbcContext(
    private val dataSource: DataSource,
) {
    fun executeSql(query: String) {
        workWithStatementStrategy {
            it.prepareStatement(query)
        }
    }

    private fun workWithStatementStrategy(makePreparedStatement: (Connection) -> PreparedStatement) {
        dataSource.connection.use { conn ->
            makePreparedStatement(conn)
                .executeUpdate()
        }
    }
}