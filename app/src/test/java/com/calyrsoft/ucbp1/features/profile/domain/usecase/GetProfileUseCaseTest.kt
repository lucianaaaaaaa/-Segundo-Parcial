package com.calyrsoft.ucbp1.features.profile.domain.usecase

import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileCellphone
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileEmail
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileName
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfilePathUrl
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileSummary
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetProfileUseCaseTest {

    private class FakeRepoSuccess : IProfileRepository {
        override suspend fun fetchData(): Result<ProfileModel> {
            val model = ProfileModel(
                pathUrl = ProfilePathUrl.create("https://example.com/profiles/avatar_batman.png"),
                name    = ProfileName.create("Bruce Wayne"),
                email   = ProfileEmail.create("bruce@wayne.enterprises"),
                cellphone = ProfileCellphone.create("65626652"),
                summary = ProfileSummary.create("The Dark Knight, protector of Gotham City.")
            )
            return Result.success(model)
        }
    }

    private class FakeRepoFailure : IProfileRepository {
        override suspend fun fetchData(): Result<ProfileModel> {
            return Result.failure(RuntimeException("Data unavailable"))
        }
    }

    @Test
    fun devuelve_success_y_respeta_delay_virtual_de_3000ms() = runTest {
        val usecase = GetProfileUseCase(FakeRepoSuccess())

        val start = testScheduler.currentTime
        val result = usecase.invoke()
        val end = testScheduler.currentTime

        assertTrue(result.isSuccess)
        assertEquals(3000, (end - start).toInt())
    }

    @Test
    fun failure_del_repositorio() = runTest {
        val usecase = GetProfileUseCase(FakeRepoFailure())
        val result = usecase.invoke()
        assertTrue(result.isFailure)
    }
}
