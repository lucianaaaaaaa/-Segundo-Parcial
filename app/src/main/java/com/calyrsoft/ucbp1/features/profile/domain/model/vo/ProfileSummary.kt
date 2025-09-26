package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class ProfileSummary private constructor(val value: String) {

    companion object {
        private const val MAX_LENGTH = 500

        private val ALLOWED_CONTENT_REGEX = Regex("^[\\p{Print}\\n\\r]*$")


        fun create(rawValue: String): ProfileSummary {
            val normalizedValue = rawValue.trim()

            require(normalizedValue.isNotBlank()) {
                "Summary cannot be empty or consist only of whitespace if provided."
            }
            require(normalizedValue.length <= MAX_LENGTH) {
                "Summary is too long. Maximum $MAX_LENGTH characters allowed."
            }


            return ProfileSummary(normalizedValue)
        }
    }

    override fun toString(): String = value
}
