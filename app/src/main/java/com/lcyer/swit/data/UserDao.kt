package com.lcyer.swit.data

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Query("SELECT * FROM User")
    fun getUsers(): List<User>

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}