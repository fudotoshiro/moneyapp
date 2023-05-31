package com.kafka.moneyapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class historyModel(@PrimaryKey(autoGenerate = true) val id: Int,
     val label: String, val amount: Double, val date: String, @ColumnInfo("categoryName") val categoryName: String){
}
