package com.pupptmstr.puppetmakebackend.dbrepo

import com.pupptmstr.puppetmakebackend.models.Project
import java.lang.Exception
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class ProjectsRepo() {
    val DB_URL = "jdbc:postgresql://localhost:5432/puppetmakedb"
    val USER = "postgres"
    val PASS = "postgres"

    fun getAll(): Array<Project>{
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
    return arrayOf(Project(0, "", listOf(""), "", false, "", ""))
    }

    fun getById(id: Long): Project {
        return Project(0, "", listOf(""), "", false, "", "")
    }
}