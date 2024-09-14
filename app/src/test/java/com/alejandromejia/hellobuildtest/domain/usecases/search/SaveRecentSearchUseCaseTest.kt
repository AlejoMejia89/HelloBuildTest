package com.alejandromejia.hellobuildtest.domain.usecases.search

import com.alejandromejia.hellobuildtest.data.local.repository.LocalRecentSearchRepository
import com.alejandromejia.hellobuildtest.domain.models.search.DRecentSearch
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test

class SaveRecentSearchUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: LocalRecentSearchRepository
    private lateinit var saveRecentSearchUseCase: SaveRecentSearchUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        saveRecentSearchUseCase = SaveRecentSearchUseCase(repository)
    }

    @Test
    fun `when the db save a recent search`() = runBlocking {
        // Given
        val mockData = DRecentSearch(
            id = 1,
            name = "Alejandro"
        )

        coEvery { repository.saveRecentSearch(mockData) } returns Unit

        // When
        val result = saveRecentSearchUseCase(mockData)

        // Then
        coVerify(exactly = 1) { repository.saveRecentSearch(mockData) }
        result.apply {
            result.apply {
                assertSame(mockData.name, "Alejandro")
            }
        }
    }

    @Test
    fun `when the db returns an error then throw exception`() = runBlocking {
        val mockData = DRecentSearch(
            id = 1,
            name = "Alejandro"
        )
        // Given
        coEvery { repository.saveRecentSearch(mockData) } throws Exception("Database error")

        // When & Then
        val exception = Assert.assertThrows(Exception::class.java) {
            saveRecentSearchUseCase(mockData)
        }
        Assert.assertEquals("Database error", exception.message)
        coVerify(exactly = 1) { repository.saveRecentSearch(mockData) }
    }

}