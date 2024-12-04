package com.example.readytoenjoy.core.data.adven

import com.example.readytoenjoy.core.network.adevn.AdvenNetworkRepositoryInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultAdvenRepository @Inject constructor(
    private val advenNetworkRepository: AdvenNetworkRepositoryInterface
): AdvenRepositoryInterface {
    override suspend fun login() {
        TODO("Not yet implemented")
    }

}