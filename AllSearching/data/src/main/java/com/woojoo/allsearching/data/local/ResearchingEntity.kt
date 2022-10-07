package com.woojoo.allsearching.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.woojoo.allsearching.data.local.ResearchingEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ResearchingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val dateTime: String,
    val viewType: Int,
    val title: String,
    val thumbnail: String
) {
    companion object {
        const val TABLE_NAME = "researching"
    }
}