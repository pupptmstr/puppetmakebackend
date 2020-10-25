package com.pupptmstr.puppetmakebackend.dbrepo

import com.pupptmstr.puppetmakebackend.Utils
import com.pupptmstr.puppetmakebackend.models.News
import com.pupptmstr.puppetmakebackend.models.Project
import java.lang.Exception
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class ProjectsRepo() {
    val DB_URL = "jdbc:postgresql://localhost:5432/puppetmakedb"
    val USER = "postgres"
    val PASS = "postgres"
    val utils = Utils()

    fun getAll(): Array<Project> {
        try {
            Class.forName("org.postgresql.Driver")
            val connection = DriverManager.getConnection(DB_URL, USER, PASS)
            val statement = connection.createStatement()
            val result = mutableListOf<Project>()
            try {
                val resSet: ResultSet = statement.executeQuery("SELECT * FROM projects;")
                while (resSet.next()) {
                    result.add(
                        Project(
                            resSet.getLong("id"),
                            resSet.getString("description"),
                            utils.getListFromSqlArray(resSet.getArray("genres")),
                            resSet.getString("project_name"),
                            resSet.getBoolean("status"),
                            resSet.getString("tech_specs"),
                            resSet.getString("logo_img_link")
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
            return result.toTypedArray()
        } catch (e: SQLException) {
            e.printStackTrace()
            throw SQLException()
        }
    }

    fun getById(id: Long): Project {
        try {
            Class.forName("org.postgresql.Driver")
            val connection = DriverManager.getConnection(DB_URL, USER, PASS)
            val statement = connection.createStatement()
            val result = mutableListOf<Project>()
            try {
                val resSet: ResultSet = statement.executeQuery("SELECT * FROM projects WHERE id=${id};")
                if (resSet.next()) {
                    result.add(
                        Project(
                            resSet.getLong("id"),
                            resSet.getString("description"),
                            utils.getListFromSqlArray(resSet.getArray("genres")),
                            resSet.getString("project_name"),
                            resSet.getBoolean("status"),
                            resSet.getString("tech_specs"),
                            resSet.getString("logo_img_link")
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
            return result[0]
        } catch (e: SQLException) {
            e.printStackTrace()
            throw SQLException()
        }
    }
}