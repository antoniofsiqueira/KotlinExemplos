package com.example.coroutineapp.main

import com.example.coroutineapp.util.FAKE_RESULTS
import com.example.coroutineapp.util.FakeNetworkCall
import com.example.coroutineapp.util.fakeNetworkLibrary

/**
 * Main network interface which will fetch a new welcome title for us
 */
interface MainNetwork {
    fun fetchNewWelcome(): FakeNetworkCall<String>
}

/**
 * Default implementation of MainNetwork.
 */
object MainNetworkImpl : MainNetwork {
    override fun fetchNewWelcome() = fakeNetworkLibrary(FAKE_RESULTS)
}