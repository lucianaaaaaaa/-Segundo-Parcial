package com.calyrsoft.ucbp1.features.profile.data.repository

import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileCellphone
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileEmail
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileName
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfilePathUrl
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileSummary
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository

class ProfileRepository: IProfileRepository {
    override fun fetchData(): Result<ProfileModel> {
        return Result.success(
            ProfileModel(
                name = ProfileName.create("Homero J. Simpson"),
                email = ProfileEmail.create("homero.simpson@springfieldmail.com"),
                cellphone = ProfileCellphone.create("+1 (939) 555‑7422"),
                pathUrl = ProfilePathUrl.create("https://www.viaempresa.cat/uploads/s1/43/99/69/homer.jpg"),
                summary = ProfileSummary.create("Ciudadano de Springfield y dedicado inspector de seguridad en la Planta Nuclear.")
            )
        )
    }
}