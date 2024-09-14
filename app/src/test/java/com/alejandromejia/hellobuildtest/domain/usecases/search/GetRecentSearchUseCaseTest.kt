package com.alejandromejia.hellobuildtest.domain.usecases.search

import com.alejandromejia.hellobuildtest.data.local.repository.LocalRecentSearchRepository
import com.alejandromejia.hellobuildtest.domain.models.search.DRecentSearch
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetRecentSearchUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: LocalRecentSearchRepository
    private lateinit var getRecentSearchUseCase: GetRecentSearchUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRecentSearchUseCase = GetRecentSearchUseCase(repository)
    }

    @Test
    fun `when the db returns something than get values`() = runBlocking {
        // Given
        val mockResponse = listOf(
            DRecentSearch(
                id = 1,
                name = "Alejandro",
            )
        )

        coEvery { repository.getRecentSearch() } returns mockResponse

        // When
        val result = getRecentSearchUseCase()

        // Then
        coVerify(exactly = 1) { repository.getRecentSearch() }
        result.forEachIndexed { index, user ->
            assert(user.id == mockResponse[index].id)
            assert(user.name == mockResponse[index].name)
        }
    }

    @Test
    fun `when the db returns an error then throw exception`() = runBlocking {
        // Given
        coEvery { repository.getRecentSearch() } throws Exception("Database error")

        // When & Then
        val exception = Assert.assertThrows(Exception::class.java) {
            getRecentSearchUseCase()
        }
        Assert.assertEquals("Database error", exception.message)
        coVerify(exactly = 1) { repository.getRecentSearch() }
    }
}