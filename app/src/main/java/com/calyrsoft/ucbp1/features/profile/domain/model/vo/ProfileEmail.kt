package com.calyrsoft.ucbp1.features.profile.domain.model.vo

@JvmInline
value class ProfileEmail private constructor(val value: String){

    companion object{
        fun create(raw: String): ProfileEmail{
            require(raw.isNotEmpty()){
                "Email must not be empty"
            }
            val normalized=raw.trim().lowercase()
            require(raw.contains("@")) {
                "Email must contain an '@'"
            }
            return ProfileEmail(normalized)
        }
    }

    override fun toString():String=value


}