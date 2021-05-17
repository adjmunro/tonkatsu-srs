package nz.tonkatsu.core.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nz.tonkatsu.core.model.dao.WordDao
import nz.tonkatsu.core.model.entity.Word

/**
 * Srs database
 *
 * @see [https://developer.android.com/codelabs/android-room-with-a-view-kotlin#7]
 * @see [https://developer.android.com/training/model-storage/room/migrating-db-versions]
 *
 * Prepopulate DB with another DB
 * @see [https://developer.android.com/training/model-storage/room/prepopulate]
 * TODO I recommend, writing code to turn the TSV into the initial database of words, sentences,
 *  and preprocessed pivot tables between them (because it won't change, unless more than is added).
 *  Then, import that database from assets for new users, and add the SRS tables etc.
 *
 * @constructor Create empty Srs database
 */
@Database(
    entities = [
        Word::class
    ],
    exportSchema = true,
    version = 1
)
abstract class SrsDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        // Singleton prevents multiple instances of the
        // database from opening at the same time.
        @Volatile
        private var INSTANCE: SrsDatabase? = null

        fun getDatabase(context: Context): SrsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SrsDatabase::class.java,
                    "srs_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}