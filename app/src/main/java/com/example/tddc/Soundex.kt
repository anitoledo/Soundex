package com.example.tddc

private const val maxCodeLength = 4
private const val notADigit = "*"

class Soundex(){

    fun encode(word: String): String {
        return zeroPad(upperFront(head(word)) + tail(encodedDigits(word)))
    }

    private fun head(word: String): String {
        return word.substring(0, 1)
    }

    private fun upperFront(word: String): String {
        return word.first().toString().toUpperCase()
    }

    private fun tail(word: String): String {
        return word.substring(1)
    }

    private fun encodedDigits(word: String): String {
        var encoding = encodeHead(word)
        encoding = encodeTail(encoding, word)
        return encoding
    }

    private fun encodeHead(word: String): String {
        return encodedDigit(word.first())
    }

    private fun encodeTail(encoding: String, word: String): String {
        var temp = encoding
        val tail = tail(word)
        tail.forEachIndexed { i, letter ->
            if(!isComplete(temp)) {
                temp += encodeLetter(temp, letter, word[i])
            }

        }
        return temp
    }

    private fun encodeLetter(encoding: String, letter: Char, lastLetter: Char): String {
        val digit = encodedDigit(letter)
        if (digit != notADigit && (digit != lastDigit(encoding) || isVowel(lastLetter))){
            return encodedDigit(letter)
        }
        return ""
    }

    private fun isVowel(letter: Char): Boolean {
        val vowels = listOf('a', 'e', 'i', 'o', 'u', 'y')
        return vowels.contains(letter)
    }

    private fun lastDigit(encoding: String): String {
        if(encoding.isEmpty()) return ""
        return encoding.last().toString()
    }

    private fun isComplete(encoding: String): Boolean {
        return encoding.length == maxCodeLength
    }

    fun encodedDigit(letter: Char): String {
        val encodings = mapOf(
            'b' to "1",
            'c' to "2",
            'd' to "3",
            'l' to "4",
            'm' to "5",
            'r' to "6",
            'f' to "1",
            'g' to "2",
            's' to "2",
            't' to "3",
            'n' to "5",
            'p' to "1",
            'j' to "2",
            'x' to "2",
            'v' to "1",
            'k' to "2",
            'z' to "2",
            'q' to "2"
        )

        val it = encodings[lower(letter)].orEmpty()
        return if(it.isEmpty()) notADigit else it
    }

    private fun lower(c: Char): Char {
        return c.toLowerCase()
    }

    private fun zeroPad(word: String): String {
        val zerosNeeded = maxCodeLength - word.length
        return "$word${IntArray(zerosNeeded, {0}).joinToString("")}"
    }
}
