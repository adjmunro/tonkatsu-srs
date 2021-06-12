package nz.tonkatsu.core.games

import nz.tonkatsu.core.model.entity.Word

class Crossword(
    val wordSelection: MutableList<Word>,
    val maxWords: Int = 50,
    val mapWidth: Int = 10,
    val mapHeight: Int = 50
) {

    companion object {
        private const val TAG: String = "Crossword"
    }

    data class Coordinate2D<T>(
        val x: T,
        val y: T
    )

    var words: MutableList<Word> = mutableListOf()
    var gameMap: MutableMap<Coordinate2D<Int>, Char> = mutableMapOf()

    fun generate() { //search entire game map every time, or build a map of letters to coords, and random
        var i: Int = 0
        while (words.size < maxWords && i < 2 * maxWords) {
            wordSelection.randomOrNull()?.let { word ->

                for (c in gameMap.filter { it.value in word.kana }.keys.shuffled()) {
                    word.kana.withIndex().filter { it.value == gameMap[c] }.shuffled().takeWhile {
                        true
                    }
                    true
                }

            } ?: break

            i++
        }
    }

}