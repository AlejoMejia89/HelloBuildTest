package com.alejandromejia.hellobuildtest.domain.usecases.favorites

import com.alejandromejia.hellobuildtest.data.local.repository.LocalFavoriteRepository
import com.alejandromejia.hellobuildtest.domain.models.user.DUser
import com.alejandromejia.hellobuildtest.domain.models.user.DUserAddress
import com.alejandromejia.hellobuildtest.domain.models.user.DUserCompany
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test


class GetFavoritesUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: LocalFavoriteRepository
    private lateinit var getFavoritesUseCase: GetFavoritesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getFavoritesUseCase = GetFavoritesUseCase(repository)
    }

    @Test
    fun `when the db returns something than get values`() = runBlocking {
        // Given
        val mockResponse = listOf(
            DUser(
                id = 1,
                name = "Alejandro",
                userName = "Alejo",
                email = "alejo.mejia@gmail.com",
                address = DUserAddress(street = "1", suite = "2", city = "Bogota", zipcode = "111"),
                phone = "1234567",
                website = "alejo.com",
                company = DUserCompany(name = "HelloBuild", catchPhrase = "TheBest", bs = "1"),
                iconColor = 0,
                isFavorite = true
            )
        )

        coEvery { repository.getFavorites() } returns mockResponse

        // When
        val result = getFavoritesUseCase()

        // Then
        coVerify(exactly = 1) { repository.getFavorites() }
        result.forEachIndexed { index, user ->
            assert(user.id == mockResponse[index].id)
            assert(user.isFavorite == mockResponse[index].isFavorite)
        }
    }

    @Test
    fun `when the db returns an error then throw exception`() = runBlocking {
        // Given
        coEvery { repository.getFavorites() } throws Exception("Database error")

        // When & Then
        val exception = assertThrows(Exception::class.java) {
            getFavoritesUseCase()
        }
        assertEquals("Database error", exception.message)
        coVerify(exactly = 1) { repository.getFavorites() }
    }

}