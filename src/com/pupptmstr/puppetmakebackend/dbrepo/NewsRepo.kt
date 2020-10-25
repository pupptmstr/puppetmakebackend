package com.pupptmstr.puppetmakebackend.dbrepo

import com.pupptmstr.puppetmakebackend.models.News
import com.pupptmstr.puppetmakebackend.models.Project
import java.lang.Exception
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.time.LocalDate

class NewsRepo {
    val DB_URL = "jdbc:postgresql://localhost:5432/puppetmakedb"
    val USER = "postgres"
    val PASS = "postgres"

    fun getAll(): Array<News>{
        try {
            Class.forName("org.postgresql.Driver")
            val connection = DriverManager.getConnection(DB_URL, USER, PASS)
            val statement = connection.createStatement()
            val result = mutableListOf<Project>()
            try {
                val resSet: ResultSet = statement.executeQuery("SELECT * FROM news;")
                while (resSet.next()) {
                    break
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            throw SQLException()
        }
        return arrayOf(News(0, "", "", LocalDate.now(), LocalDate.now(), ""))
    }

    fun getById(id: Long): News {
        return News(0, "", "", LocalDate.now(), LocalDate.now(), "")
    }
}