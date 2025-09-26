package com.calyrsoft.ucbp1.features.profile.domain.model.vo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class ProfileEmailTest {

    @Test
    fun `crea ProfileEmail válido y normaliza a minúsculas y quita espacios`() {
        val email = ProfileEmail.create("  John.Doe@Example.COM  ")
        assertEquals("john.doe@example.com", email.value)
    }

    



    @Test
    fun `crea ProfileEmail válido con subdominio y TLD largo`() {
        val email = ProfileEmail.create("test@sub.example.co.uk")
        assertEquals("test@sub.example.co.uk", email.value)
    }

    @Test
    fun `crea ProfileEmail válido con caracteres especiales permitidos en la parte local`() {
        val email = ProfileEmail.create("john.doe+test-alias@example-domain.com")
        assertEquals("john.doe+test-alias@example-domain.com", email.value)
    }

    @Test
    fun `crea ProfileEmail válido con números en el dominio`() {
        val email = ProfileEmail.create("user@123example.com")
        assertEquals("user@123example.com", email.value)
    }
}
