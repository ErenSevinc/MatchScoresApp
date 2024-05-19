package com.example.matchscoresapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.matchscoresapp.domain.model.Match

@Database(
    entities = [Match::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): MatchDao
}