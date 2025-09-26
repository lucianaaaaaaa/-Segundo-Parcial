package com.calyrsoft.ucbp1.features.profile.domain.model.vo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class ProfileSummaryTest {


    @Test
    fun `falla si ProfileSummary está vacío después de trim`() {
        val exception = assertThrows<IllegalArgumentException> {
            ProfileSummary.create("")
        }
        assertEquals("Summary cannot be empty or consist only of whitespace if provided.", exception.message)
    }

    @Test
    fun `falla si ProfileSummary solo contiene espacios`() {
        val exception = assertThrows<IllegalArgumentException> {
            ProfileSummary.create("   \n   ")
        }
        assertEquals("Summary cannot be empty or consist only of whitespace if provided.", exception.message)
    }

    @Test
    fun `falla si ProfileSummary es demasiado largo`() {
        val longSummary = "a".repeat(501)
        val exception = assertThrows<IllegalArgumentException> {
            ProfileSummary.create(longSummary)
        }
        assertEquals("Summary is too long. Maximum 500 characters allowed.", exception.message)
    }



    @Test
    fun `crea ProfileSummary en el límite de longitud máxima`() {
        val summaryText = "b".repeat(500)
        val summary = ProfileSummary.create(summaryText)
        assertEquals(summaryText, summary.value)
    }
}
