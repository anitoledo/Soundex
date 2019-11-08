package com.example.tddc

private const val TAIL_MAX_LENGTH = 3

class Soundex {
    fun encode(word: String): String {
        return "${getFirstLetter(word).toUpperCase()}${completeDigit(replaceConsonants(word))}"
    }

    private fun getFirstLetter(word: String): Char {
        return word.first()
    }

    private fun tail(word: String): String {
        return word.substring(1)
    }

    private fun replaceConsonants(word: String): String {
        var temp = ""
        val tail = tail(word)
        var lastWordValue = encodeLetter(word[0], "")
        tail.forEachIndexed { i, letter ->
            if(!isCompleted(temp)) {
                lastWordValue = encodeLetter(word[i], lastWordValue)
                val currentWordValue = encodeLetter(letter, lastWordValue)
                temp += if (repeatedValue(lastWordValue, currentWordValue)) "" else currentWordValue
            }
        }
        return temp
    }

    private fun encodeLetter(letter: Char, lastWordValue: String): String {
        return when(letter.toLowerCase()){
            'b', 'f', 'p', 'v' -> "1"
            'c', 'g', 'j', 'k', 'q', 's', 'x', 'z' -> "2"
            'd', 't' -> "3"
            'l' -> "4"
            'm', 'n' -> "5"
            'r' -> "6"
            'h', 'w' -> lastWordValue
            else -> ""
        }
    }

    private fun repeatedValue(lastWordValue: String, currentWordValue: String): Boolean{
        return lastWordValue == currentWordValue
    }

    private fun isCompleted(tail: String): Boolean{
        return tail.length >= TAIL_MAX_LENGTH
    }

    private fun completeDigit(tail: String): String{
        var temp = tail
        if (!isCompleted(tail)) {
            for(i in tail.length until TAIL_MAX_LENGTH) {
                temp += "0"
            }
        }
        return temp
    }
}
