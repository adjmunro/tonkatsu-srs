package nz.tonkatsu.core.io

import android.content.Context
import android.util.Log
import nz.tonkatsu.core.R
import nz.tonkatsu.core.model.entity.Word

/**
 * Importer core6k tsv
 *
 * @constructor Create empty Importer core6k tsv
 */
class ImporterCore6kTsv {

    companion object {
        private const val TAG: String = "ImporterCore6kTsv"

        fun import(context: Context) {
            context.resources.openRawResource(R.raw.core6k)
                .bufferedReader(charset = Charsets.UTF_8).lineSequence().forEach { line ->
                    line.trim().split('\t').let {
                        val w = Word(
                            english = it[9],
                            jlpt = it[6].removePrefix("JLPT").toIntOrNull(),
                            kana = it[8],
                            kanji = it[7],
                            sound = it[10].removeSurrounding("[sound:", ".mp3]"),
                            pos = it[11]
                        )
                        Log.d(TAG, w.toString())
                    }
                }
        }
    }

}