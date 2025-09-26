package com.calyrsoft.ucbp1.features.profile.domain.model.vo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class ProfileCellphoneTest {

    @Test
    fun `crea ProfileCellphone válido y normaliza espacios`() {
        val cellphone = ProfileCellphone.create("  +1 (123) 456-7890  ")
        assertEquals("+1 (123) 456-7890", cellphone.value)
    }

    @Test
    fun `falla si ProfileCellphone está vacío`() {
        val exception = assertThrows<IllegalArgumentException> {
            ProfileCellphone.create("")
        }
        assertEquals("Cellphone number cannot be empty.", exception.message)
    }

    @Test
    fun `falla si ProfileCellphone solo contiene espacios`() {
        val exception = assertThrows<IllegalArgumentException> {
            ProfileCellphone.create("   ")
        }
        assertEquals("Cellphone number cannot be empty.", exception.message)
    }

    @Test
    fun `falla si ProfileCellphone contiene caracteres inválidos`() {
        val exception1 = assertThrows<IllegalArgumentException> {
            ProfileCellphone.create("123-ABC-7890")
        }
        assertEquals("Cellphone number contains invalid characters. Only digits, '+', '-', '()', and spaces are allowed.", exception1.message)

        val exception2 = assertThrows<IllegalArgumentException> {
            ProfileCellphone.create("123!4567890")
        }
        assertEquals("Cellphone number contains invalid characters. Only digits, '+', '-', '()', and spaces are allowed.", exception2.message)
    }

    @Test
    fun `falla si ProfileCellphone tiene muy pocos dígitos`() {
        val exception = assertThrows<IllegalArgumentException> {
            ProfileCellphone.create("123456")
        }
        assertEquals("Cellphone number is too short (minimum 7 digits).", exception.message)
    }

    @Test
    fun `falla si ProfileCellphone tiene demasiados dígitos`() {
        val exception = assertThrows<IllegalArgumentException> {
            ProfileCellphone.create("1234567890123456")
        }
        assertEquals("Cellphone number is too long (maximum 15 digits).", exception.message)
    }

    @Test
    fun `crea ProfileCellphone válido con formato internacional`() {
        val cellphone = ProfileCellphone.create("+44 1234 567890")
        assertEquals("+44 1234 567890", cellphone.value)
        assertEquals("441234567890", cellphone.getDigitsOnly())
    }

    @Test
    fun `crea ProfileCellphone válido con formato local y paréntesis`() {
        val cellphone = ProfileCellphone.create("(123) 456-7890")
        assertEquals("(123) 456-7890", cellphone.value)
        assertEquals("1234567890", cellphone.getDigitsOnly())
    }

    @Test
    fun `crea ProfileCellphone en el límite mínimo de dígitos`() {
        val cellphone = ProfileCellphone.create("1234567")
        assertEquals("1234567", cellphone.value)
    }

    @Test
    fun `crea ProfileCellphone en el límite máximo de dígitos`() {
        val cellphone = ProfileCellphone.create("123456789012345")
        assertEquals("123456789012345", cellphone.value)
    }
}
