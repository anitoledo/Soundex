package com.example.tddc

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class SoundexTest {

    // Ask about fixtures, is this approch ok? The instantiation was duplicated in all tests so the author use a fixture
    val soundex = Soundex()

    @Test
    fun retainsSoleLetterOfOneLetterWord() {
        assertEquals("A000", soundex.encode("A"))
    }

    @Test
    fun padsWithZerosToEnsureThreeDigits(){
        assertEquals("I000", soundex.encode("I"))
    }

    @Test
    fun replacesConsonantsWithAppropriateDigits(){
        assertEquals("A100", soundex.encode("Ab"))
        assertEquals("A200", soundex.encode("Ac"))
        assertEquals("A300", soundex.encode("Ad"))
        assertEquals("A200", soundex.encode("Ax"))
    }

    @Test
    fun replacesMultipleConsonantsWithDigits(){
        assertEquals("A234", soundex.encode("Acdl"))
    }

    @Test
    fun limitsLengthToFourCharacters(){
        assertEquals(soundex.encode("Dcdlb").length, 4)
    }

    @Test
    fun ignoresVowelLikeLetters(){
        assertEquals("B234", soundex.encode("BaAeEiIoOuUhHyYcdl"))
    }

    @Test
    fun combinesDuplicateEncodings(){
        assertEquals(soundex.encodedDigit('b'), soundex.encodedDigit('f'))
        assertEquals(soundex.encodedDigit('c'), soundex.encodedDigit('g'))
        assertEquals(soundex.encodedDigit('d'), soundex.encodedDigit('t'))

        assertEquals("A123", soundex.encode("Abfcgdt"))
    }

    @Test
    fun uppercasesFirstLetter(){
        assertEquals(true, soundex.encode("abcd").startsWith("A"))
    }

    @Test
    fun ignoresCaseWhenEncodingConsonants(){
        assertEquals(soundex.encode("BCDL"), soundex.encode("Bcdl"))
    }

    @Test
    fun combinesDuplicateCodesWhen2ndLetterDuplicates1st(){
        assertEquals("B230", soundex.encode("Bbcd"))
    }

    @Test
    fun doesNotCombineDuplicateEncodingsSeparatedByVowels(){
        assertEquals("J110", soundex.encode("Jbob"))
    }
}
