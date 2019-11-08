package com.example.tddc

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class SoundexTest {

    val soundex = Soundex()

    @Test
    fun retainFirstLetter() {
        assertEquals("A123", soundex.encode("Abcd"))
    }

    @Test
    fun dropVowelOcurrences() {
        assertEquals("A300", soundex.encode("Aead"))
    }

    @Test
    fun replaceConsonants(){
        assertEquals("A123", soundex.encode("Abcd"))
    }

    @Test
    fun concatenateAdjacentConsonantsSameValue(){
        assertEquals("A123", soundex.encode("Abbcd"))
        assertEquals("A123", soundex.encode("Abbbccdd"))
    }

    @Test
    fun ifVowelRepeatConsonantValue(){
        assertEquals("A112", soundex.encode("Abbabccdd"))
    }

    @Test
    fun ifHorWConcatenateConsonantValue(){
        assertEquals("A123", soundex.encode("Abbhbccdd"))
    }

    @Test
    fun trimWhenFourDigitEncode(){
        assertEquals("A300", soundex.encode("Aead"))
    }
}
