package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class ProfileCellphone private constructor(val value: String) {

    companion object {
        private const val MIN_DIGITS = 7
        private const val MAX_DIGITS = 15
        private val ALLOWED_CHARS_REGEX = Regex("^[0-9()+\\-\\s]*$")

        fun create(rawValue: String): ProfileCellphone {
            val normalizedValue = rawValue.trim()

            require(normalizedValue.isNotBlank()) {
                "Cellphone number cannot be empty."
            }
            require(ALLOWED_CHARS_REGEX.matches(normalizedValue)) {
                "Cellphone number contains invalid characters. Only digits, '+', '-', '()', and spaces are allowed."
            }

            val digitsOnly = normalizedValue.filter { it.isDigit() }
            require(digitsOnly.length >= MIN_DIGITS) {
                "Cellphone number is too short (minimum $MIN_DIGITS digits)."
            }
            require(digitsOnly.length <= MAX_DIGITS) {
                "Cellphone number is too long (maximum $MAX_DIGITS digits)."
            }

            return ProfileCellphone(normalizedValue)
        }
    }

    fun getDigitsOnly(): String {
        return value.filter { it.isDigit() }
    }

    override fun toString(): String = value
}
