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

class RemoveRecentSearchUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: LocalRecentSearchRepository
    private lateinit var removeRecentSearchUseCase: RemoveRecentSearchUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        removeRecentSearchUseCase = RemoveRecentSearchUseCase(repository)
    }

    @Test
    fun `when the db remove a recent search`() = runBlocking {
        // Given
        val mockData = DRecentSearch(id = 1, name = "Alejandro")

        coEvery { repository.removeRecentSearch(mockData) } returns Unit

        // When
        val result = removeRecentSearchUseCase(mockData)

        // Then
        coVerify(exactly = 1) { repository.removeRecentSearch(mockData) }
        result.apply {
            result.apply {
                assertSame(mockData.name, "Alejandro")
            }
        }
    }

    @Test
    fun `when the db returns an error then throw exception`() = runBlocking {
        val mockData = DRecentSearch(id = 1, name = "Alejandro")
        // Given
        coEvery { repository.removeRecentSearch(mockData) } throws Exception("Database error")

        // When & Then
        val exception = Assert.assertThrows(Exception::class.java) {
            removeRecentSearchUseCase(mockData)
        }
        Assert.assertEquals("Database error", exception.message)
        coVerify(exactly = 1) { repository.removeRecentSearch(mockData) }
    }
}