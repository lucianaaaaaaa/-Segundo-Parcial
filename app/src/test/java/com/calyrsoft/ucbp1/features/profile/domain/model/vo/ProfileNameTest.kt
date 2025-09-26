package com.calyrsoft.ucbp1.features.profile.domain.model.vo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class ProfileNameTest {

    @Test
    fun `crea ProfileName válido y normaliza espacios`() {
        val name = ProfileName.create("  John Doe  ")
        assertEquals("John Doe", name.value)
    }

    @Test
    fun `crea ProfileName válido con caracteres permitidos`() {
        val name = ProfileName.create("Jean-Luc O'Malley.Sr")
        assertEquals("Jean-Luc O'Malley.Sr", name.value)
    }

    @Test
    fun `falla si ProfileName está vacío`() {
        val exception = assertThrows<IllegalArgumentException> {
            ProfileName.create("")
        }
        assertEquals("Name cannot be empty or consist only of whitespace.", exception.message)
    }

    @Test
    fun `falla si ProfileName solo contiene espacios`() {
        val exception = assertThrows<IllegalArgumentException> {
            ProfileName.create("   ")
        }
        assertEquals("Name cannot be empty or consist only of whitespace.", exception.message)
    }

    @Test
    fun `falla si ProfileName es demasiado corto`() {
        val exception = assertThrows<IllegalArgumentException> {
            ProfileName.create("J")
        }
        assertEquals("Name must be at least 2 characters long.", exception.message)
    }

    @Test
    fun `falla si ProfileName es demasiado largo`() {
        val longName = "a".repeat(101)
        val exception = assertThrows<IllegalArgumentException> {
            ProfileName.create(longName)
        }
        assertEquals("Name cannot exceed 100 characters.", exception.message)
    }

    @Test
    fun `falla si ProfileName contiene caracteres inválidos`() {
        val exception1 = assertThrows<IllegalArgumentException> {
            ProfileName.create("John Doe123")
        }
        assertEquals("Name contains invalid characters. Only letters, spaces, apostrophes, hyphens, and periods are allowed.", exception1.message)

        val exception2 = assertThrows<IllegalArgumentException> {
            ProfileName.create("John@Doe")
        }
        assertEquals("Name contains invalid characters. Only letters, spaces, apostrophes, hyphens, and periods are allowed.", exception2.message)
    }

    @Test
    fun `crea ProfileName en el límite de longitud mínima`() {
        val name = ProfileName.create("Jo")
        assertEquals("Jo", name.value)
    }

    @Test
    fun `crea ProfileName en el límite de longitud máxima`() {
        val nameValue = "a".repeat(100)
        val name = ProfileName.create(nameValue)
        assertEquals(nameValue, name.value)
    }
}
