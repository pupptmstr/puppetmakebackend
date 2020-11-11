package com.pupptmstr.puppetmakebackend.dbrepo

import com.pupptmstr.puppetmakebackend.Utils
import com.pupptmstr.puppetmakebackend.models.dbmodels.Project
import com.pupptmstr.puppetmakebackend.models.responses.ResponseModel
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class ProjectsRepo() {
    val DB_URL = "jdbc:postgresql://localhost:5432/puppetmakedb"
    val USER = "postgres"
    val PASS = "postgres"
    val utils = Utils()

    val data = mutableSetOf<Project>()

    private fun getAllFromDB(): ResponseModel<Project> {
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
            return ResponseModel(result)
        } catch (e: SQLException) {
            e.printStackTrace()
            throw SQLException()
        }
    }


    fun update() {
        val responseModel = getAllFromDB()
        data.addAll(responseModel.ArrayData)
    }

    fun getAll(): ResponseModel<Project> {
        return ResponseModel(data.toList())
    }

    fun getAllDone(): ResponseModel<Project> {
        return getAllByStatus(true)
    }

    fun getAllToDo(): ResponseModel<Project> {
        return getAllByStatus(false)
    }

    private fun getAllByStatus(status: Boolean): ResponseModel<Project> {
        val res = mutableListOf<Project>()
        data.forEach {
            if (it.status == status) {
                res.add(it)
            }
        }
        return ResponseModel(res)
    }

    fun getById(id: Long): ResponseModel<Project> {
        val result = mutableListOf<Project>()
        for (proj: Project in data) {
            if (proj.id == id) {
                result.add(proj)
            }
        }
        return ResponseModel(result)
    }

    fun search(query: String) : ResponseModel<Project> {
        val result = mutableListOf<Project>()
        data.forEach {
            if (it.projectName.toLowerCase().contains(query.toLowerCase())) {
                result.add(it)
            }
        }
        return ResponseModel(result)
    }
}