package com.pupptmstr.puppetmakebackend.dbrepo

import com.pupptmstr.puppetmakebackend.models.Project
import com.pupptmstr.puppetmakebackend.models.Teammate
import java.lang.Exception
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.time.LocalDate

class TeammatesRepo {
    val DB_URL = "jdbc:postgresql://127.0.0.1:5432/gorizont"
    val USER = "postgres"
    val PASS = "postgres"

    fun getAll(): Array<Teammate>{
        try {
            Class.forName("org.postgresql.Driver")
            val connection = DriverManager.getConnection(DB_URL, USER, PASS)
            val statement = connection.createStatement()
            val result = mutableListOf<Project>()
            try {
                val resSet: ResultSet = statement.executeQuery("SELECT * FROM projects;")
                while (resSet.next()) {
                    TODO("")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            throw SQLException()
        }
        return arrayOf(Teammate(0, "", "", "", LocalDate.now(), "", "", "", "", listOf(""), listOf("")))
    }

    fun getById(id: Long): Teammate {
        return Teammate(0, "", "", "", LocalDate.now(), "", "", "", "", listOf(""), listOf(""))
    }
}