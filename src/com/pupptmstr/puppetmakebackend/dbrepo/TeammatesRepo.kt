package com.pupptmstr.puppetmakebackend.dbrepo

import com.pupptmstr.puppetmakebackend.Utils
import com.pupptmstr.puppetmakebackend.models.Project
import com.pupptmstr.puppetmakebackend.models.Teammate
import org.postgresql.util.PSQLException
import java.lang.Exception
import java.lang.NullPointerException
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.time.LocalDate

class TeammatesRepo {
    val DB_URL = "jdbc:postgresql://localhost:5432/puppetmakedb"
    val USER = "postgres"
    val PASS = "postgres"
    val utils = Utils()

    fun getAll(): Array<Teammate>{
        try {
            Class.forName("org.postgresql.Driver")
            val connection = DriverManager.getConnection(DB_URL, USER, PASS)
            val statement = connection.createStatement()
            val result = mutableListOf<Teammate>()
            try {
                val resSet: ResultSet = statement.executeQuery("SELECT * FROM teammates;")
                while (resSet.next()) {
                    var currentRole: String? = null
                    var photos: List<String>? = null
                    try {
                        currentRole = resSet.getString("current_role")
                        photos = utils.getListFromSqlArray(resSet.getArray("photos"))
                    }catch (e: PSQLException) {
                        //who cares?
                    } catch (e: NullPointerException) {
                        //who cares?
                    }
                    result.add(
                        Teammate(
                            resSet.getLong("id"),
                            resSet.getString("first_name"),
                            resSet.getString("surname"),
                            resSet.getString("nickname"),
                            utils.getLocalDateFromString(resSet.getString("hired_at"))!!,
                            resSet.getString("global_role"),
                            currentRole,
                            resSet.getString("main_photo_link"),
                            resSet.getString("description"),
                            utils.getListFromSqlArray(resSet.getArray("social_links"))!!,
                            photos
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

    fun getById(id: Long): Teammate {
        try {
            Class.forName("org.postgresql.Driver")
            val connection = DriverManager.getConnection(DB_URL, USER, PASS)
            val statement = connection.createStatement()
            val result = mutableListOf<Teammate>()
            try {
                val resSet: ResultSet = statement.executeQuery("SELECT * FROM teammates WHERE id=${id};")
                if (resSet.next()) {
                    var currentRole: String? = null
                    var photos: List<String>? = null
                    try {
                        currentRole = resSet.getString("current_role")
                        photos = utils.getListFromSqlArray(resSet.getArray("photos"))
                    }catch (e: PSQLException) {
                        //who cares?
                    } catch (e: NullPointerException) {
                        //who cares?
                    }
                    result.add(
                        Teammate(
                            resSet.getLong("id"),
                            resSet.getString("first_name"),
                            resSet.getString("surname"),
                            resSet.getString("nickname"),
                            utils.getLocalDateFromString(resSet.getString("hired_at"))!!,
                            resSet.getString("global_role"),
                            currentRole,
                            resSet.getString("main_photo_link"),
                            resSet.getString("description"),
                            utils.getListFromSqlArray(resSet.getArray("social_links"))!!,
                            photos
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