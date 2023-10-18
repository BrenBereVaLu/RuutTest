package com.example.ruuttest.data.databases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insert(vararg user: User)

    @Query("SELECT * FROM user_table LIMIT 1")
    fun getAnyUser(): User?

    @Query("UPDATE user_table SET user_password =:pass WHERE user_email LIKE:email")
    fun update(pass: String, email: String)

    @Query("UPDATE user_table SET user_email=:email WHERE user_email LIKE:email")
    fun updateEmail(email: String)

    @Query("SELECT * FROM user_table WHERE user_email LIKE :email LIMIT 1")
    fun findByEmail(email: String): User

    @Query("DELETE FROM user_table ")
    fun deleteAll()

    @Delete
    fun delete(user: User)
}