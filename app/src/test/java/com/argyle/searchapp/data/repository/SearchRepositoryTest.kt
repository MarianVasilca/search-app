package com.argyle.searchapp.data.repository

import com.argyle.searchapp.data.model.SearchLinkItemsError
import com.argyle.searchapp.data.network.NetworkAPI
import com.argyle.searchapp.data.network.response.LinkItem
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import java.io.IOException

@ExperimentalCoroutinesApi
class SearchRepositoryTest {

    private lateinit var subject: SearchRepositoryImpl
    private lateinit var networkAPI: NetworkAPI

    @Test(expected = SearchLinkItemsError::class)
    fun searchLinkItems_throws() = runBlockingTest {
        networkAPI = mock {
            onBlocking {
                searchLinkItems(anyInt(), anyString())
            } doAnswer { throw IOException() }
        }

        subject = SearchRepositoryImpl(networkAPI)

        subject.searchLinkItems(15, "")
    }

    @Test
    fun searchLinkItems_successful() = runBlockingTest {

        val expectedResult = listOf<LinkItem>()

        networkAPI = mock {
            onBlocking {
                searchLinkItems(anyInt(), anyString())
            } doAnswer { expectedResult }
        }

        subject = SearchRepositoryImpl(networkAPI)

        val result = subject.searchLinkItems(15, "")

        Truth.assertThat(result).isEqualTo(expectedResult)
    }
}