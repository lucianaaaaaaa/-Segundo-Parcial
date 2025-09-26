package com.calyrsoft.ucbp1.features.profile.domain.model.vo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class ProfilePathUrlTest {

    @Test
    fun `crea ProfilePathUrl válida y normaliza espacios`() {
        val url = ProfilePathUrl.create("  http://example.com/image.png  ")
        assertEquals("http://example.com/image.png", url.value)
    }

    @Test
    fun `crea ProfilePathUrl válida con https y query y fragmento`() {
        val url = ProfilePathUrl.create("https://sub.example.com/path?query=value#fragment")
        assertEquals("https://sub.example.com/path?query=value#fragment", url.value)
    }

    @Test
    fun `falla si ProfilePathUrl está vacía`() {
        val exception = assertThrows<IllegalArgumentException> {
            ProfilePathUrl.create("")
        }
        assertEquals("Profile path URL cannot be empty or blank.", exception.message)
    }

    @Test
    fun `falla si ProfilePathUrl solo contiene espacios`() {
        val exception = assertThrows<IllegalArgumentException> {
            ProfilePathUrl.create("   ")
        }
        assertEquals("Profile path URL cannot be empty or blank.", exception.message)
    }



    @Test
    fun `falla si ProfilePathUrl es demasiado larga`() {
        val longUrl = "http://example.com/" + "a".repeat(2040)
        val exception = assertThrows<IllegalArgumentException> {
            ProfilePathUrl.create(longUrl)
        }
        assertEquals("Profile path URL is too long (max 2048 characters).", exception.message)
    }
}
