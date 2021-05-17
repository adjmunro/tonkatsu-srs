package nz.tonkatsu.core.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nz.tonkatsu.core.model.entity.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM words ORDER BY id DESC")
    fun getAll(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM words")
    suspend fun deleteAll()

}