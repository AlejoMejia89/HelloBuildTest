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
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RemoveFavoritesUseCaseTest{

    @RelaxedMockK
    private lateinit var repository: LocalFavoriteRepository
    private lateinit var removeFavoritesUseCase: RemoveFavoritesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        removeFavoritesUseCase = RemoveFavoritesUseCase(repository)
    }

    @Test
    fun `when the db remove a favorite user`() = runBlocking {
        // Given
        val mockData = DUser(
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

        coEvery { repository.removeFavorite(any()) } returns Unit

        // When
        val result = removeFavoritesUseCase(mockData)

        // Then
        coVerify(exactly = 1) { repository.removeFavorite(any()) }
        result.apply {
            result.apply {
                assertTrue(mockData.isFavorite)
            }
        }
    }

    @Test
    fun `when the db returns an error then throw exception`() = runBlocking {
        val mockData = DUser(
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
        // Given
        coEvery { repository.removeFavorite(mockData) } throws Exception("Database error")

        // When & Then
        val exception = Assert.assertThrows(Exception::class.java) {
            removeFavoritesUseCase(mockData)
        }
        Assert.assertEquals("Database error", exception.message)
        coVerify(exactly = 1) { repository.removeFavorite(mockData) }
    }
}