package nz.tonkatsu.core.io

/*
val f = """
一	いち	one
二	に	two
三	さん	three
四	よん	four
五	ご	five
六	ろく	six
七	なな	seven
八	はち	eight
九	きゅう	nine
十	じゅう	ten
"""

data class Word(
    val kanji: String,
    val kana: String,
    val english: String
)

fun main() {
    val words: List<Word> = f.trim().split('\n').map { line ->
        line.split('\t').let {
            Word(
                kanji = it[0],
                kana = it[1],
                english = it[2]
            )
        }
    }
    println(words.map {it.kanji})
}
 */
/**
 * Importer core6k tsv
 *
 * @constructor Create empty Importer core6k tsv
 */
class ImporterCore6kTsv