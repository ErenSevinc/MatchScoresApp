package com.example.matchscoresapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.matchscoresapp.domain.model.Match

@Dao
interface MatchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: Match)

    @Delete
    fun delete(articles: Match)

    @Query("SELECT * FROM matches")
    fun getFavMatch(): List<Match>
}