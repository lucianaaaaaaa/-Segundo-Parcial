package com.calyrsoft.ucbp1.features.profile.domain.model

import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileCellphone
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileEmail
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileName
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfilePathUrl
import com.calyrsoft.ucbp1.features.profile.domain.model.vo.ProfileSummary
import org.junit.Test
import org.junit.Assert.*

class ProfileModelTest {

    private fun aModel(): ProfileModel =
        ProfileModel(
            pathUrl = ProfilePathUrl.create("https://example.com/a.png"),
            name = ProfileName.create("Bruce Wayne"),
            email = ProfileEmail.create("bruce@wayne.enterprises"),
            cellphone = ProfileCellphone.create("+1-555-0000"),
            summary = ProfileSummary.create("The Dark Knight")
        )

    @Test
    fun construye_con_VOs_validos() {
        val m = aModel()
        assertEquals("Bruce Wayne", m.name.value)
        assertEquals("bruce@wayne.enterprises", m.email.value)
        assertEquals("+1-555-0000", m.cellphone.value)
        assertTrue(m.summary.value.contains("Dark Knight"))
    }

    @Test
    fun equals_y_hashCode_funcionan_por_data_class() {
        val m1 = aModel()
        val m2 = aModel()
        assertEquals(m1, m2)
        assertEquals(m1.hashCode(), m2.hashCode())
    }

    @Test
    fun copy_modifica_un_campo_y_mantiene_el_resto() {
        val original = aModel()
        val changed = original.copy(
            email = ProfileEmail.create("batman@gotham.gov")
        )

        assertNotEquals(original, changed)
        assertEquals("batman@gotham.gov", changed.email.value)
        assertEquals(original.name, changed.name)
        assertEquals(original.cellphone, changed.cellphone)
        assertEquals(original.pathUrl, changed.pathUrl)
        assertEquals(original.summary, changed.summary)
    }

    @Test
    fun desestructuracion_respeta_orden_de_propiedades() {
        val m = aModel()
        val (pathUrl, name, email, cellphone, summary) = m
        assertEquals(m.pathUrl, pathUrl)
        assertEquals(m.name, name)
        assertEquals(m.email, email)
        assertEquals(m.cellphone, cellphone)
        assertEquals(m.summary, summary)
    }
}
