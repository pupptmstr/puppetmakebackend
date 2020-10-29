package com.pupptmstr.puppetmakebackend.dbrepo

import com.pupptmstr.puppetmakebackend.Utils
import com.pupptmstr.puppetmakebackend.models.News
import com.pupptmstr.puppetmakebackend.models.ResponseModel
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class NewsRepo {
    val DB_URL = "jdbc:postgresql://localhost:5432/puppetmakedb"
    val USER = "postgres"
    val PASS = "postgres"
    val utils = Utils()

    fun getAll(): ResponseModel<News> {
        try {
            Class.forName("org.postgresql.Driver")
            val connection = DriverManager.getConnection(DB_URL, USER, PASS)
            val statement = connection.createStatement()
            val result = mutableListOf<News>()
            try {
                val resSet: ResultSet = statement.executeQuery("SELECT * FROM news;")
                while (resSet.next()) {
                    result.add(
                        News(
                            resSet.getLong("id"),
                            resSet.getString("header"),
                            resSet.getString("content"),
                            utils.getLocalDateFromString(resSet.getString("create_at"))!!,
                            utils.getLocalDateFromString(resSet.getString("delete_at")),
                            resSet.getString("main_image_link")
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ResponseModel(result)
        } catch (e: SQLException) {
            e.printStackTrace()
            throw SQLException()
        }
    }

    fun getById(id: Long): News {
        try {
            Class.forName("org.postgresql.Driver")
            val connection = DriverManager.getConnection(DB_URL, USER, PASS)
            val statement = connection.createStatement()
            val result = mutableListOf<News>()
            try {
                val resSet: ResultSet = statement.executeQuery("SELECT * FROM news WHERE id=${id};")
                if (resSet.next()) {
                    result.add(
                        News(
                            resSet.getLong("id"),
                            resSet.getString("header"),
                            resSet.getString("content"),
                            utils.getLocalDateFromString(resSet.getString("create_at"))!!,
                            utils.getLocalDateFromString(resSet.getString("delete_at")),
                            resSet.getString("main_image_link")
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result[0]
        } catch (e: SQLException) {
            e.printStackTrace()
            throw SQLException()
        }
    }
}