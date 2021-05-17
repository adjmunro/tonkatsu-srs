package nz.tonkatsu.core.model.repo

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import nz.tonkatsu.core.model.dao.WordDao
import nz.tonkatsu.core.model.entity.Word

/**
 * Word repo
 *
 * @see [https://developer.android.com/codelabs/android-room-with-a-view-kotlin#8]
 *
 * Pass in the DAO instead of the whole database, because we only need to access the DAO
 * @property wordDao word dao
 * @constructor Create empty Word repo
 */
class WordRepo(
    private val wordDao: WordDao
) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the model has changed.
    val all: Flow<List<Word>> = wordDao.getAll()

    // By default Room runs suspend queries off the main thread,
    // therefore, we don't need to implement anything else to ensure
    // we're not doing long running database work off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

}