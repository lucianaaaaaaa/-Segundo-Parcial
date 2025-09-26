package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class ProfileName private constructor(val value: String) {

    companion object {
        private const val MIN_LENGTH = 2
        private const val MAX_LENGTH = 100

        private val ALLOWED_CHARS_REGEX = Regex("^[\\p{L} '\\-.]+$")

        fun create(rawValue: String): ProfileName {
            val normalizedValue = rawValue.trim()

            require(normalizedValue.isNotBlank()) {
                "Name cannot be empty or consist only of whitespace."
            }
            require(normalizedValue.length >= MIN_LENGTH) {
                "Name must be at least $MIN_LENGTH characters long."
            }
            require(normalizedValue.length <= MAX_LENGTH) {
                "Name cannot exceed $MAX_LENGTH characters."
            }
            require(ALLOWED_CHARS_REGEX.matches(normalizedValue)) {
                "Name contains invalid characters. Only letters, spaces, apostrophes, hyphens, and periods are allowed."
            }
            return ProfileName(normalizedValue)
        }
    }

    override fun toString(): String = value
}
