package com.kafka.moneyapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface transactionsDao {
    @Query("SELECT * from transactions")
    fun getAll():List<historyModel>

    @Insert
    fun insertAll(vararg historyModel: historyModel)

    @Delete
    fun delete(historyModel: historyModel)

    @Update
    fun update(vararg historyModel: historyModel)
}