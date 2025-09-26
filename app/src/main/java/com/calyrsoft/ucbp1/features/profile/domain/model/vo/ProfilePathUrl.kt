package com.calyrsoft.ucbp1.features.profile.domain.model.vo

import android.util.Patterns

@JvmInline
value class ProfilePathUrl private constructor(val value: String) {

    companion object {
        private const val MAX_LENGTH = 2048

        fun create(rawValue: String): ProfilePathUrl {
            val normalizedValue = rawValue.trim()

            require(normalizedValue.isNotBlank()) {
                "Profile path URL cannot be empty or blank."
            }

            require(normalizedValue.length <= MAX_LENGTH) {
                "Profile path URL is too long (max $MAX_LENGTH characters)."
            }

            return ProfilePathUrl(normalizedValue)
        }
    }

    override fun toString(): String = value
}