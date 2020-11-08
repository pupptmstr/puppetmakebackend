package com.pupptmstr.puppetmakebackend.dbrepo

import com.pupptmstr.puppetmakebackend.Utils
import com.pupptmstr.puppetmakebackend.models.News
import com.pupptmstr.puppetmakebackend.models.Project
import com.pupptmstr.puppetmakebackend.models.ResponseModel
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

    val data = mutableSetOf<Teammate>()

    private fun getAllFromDB(): ResponseModel<Teammate>{
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
                        currentRole = resSet.getString("curr_role")
                        photos = utils.getListFromSqlArray(resSet.getArray("photos"))
                    }catch (e: PSQLException) {
                        e.printStackTrace()
                    } catch (e: NullPointerException) {
                        e.printStackTrace()
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

    fun getAll(): ResponseModel<Teammate> {
        return ResponseModel(data.toList())
    }

    fun getById(id: Long): ResponseModel<Teammate> {
        val result = mutableListOf<Teammate>()
        for (teammate: Teammate in data) {
            if (teammate.id == id) {
                result.add(teammate)
            }
        }
        return ResponseModel(result)
    }
}