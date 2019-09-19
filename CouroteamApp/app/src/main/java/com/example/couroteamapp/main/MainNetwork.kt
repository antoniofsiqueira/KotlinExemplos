package com.example.couroteamapp.main

import com.example.android.couroteamapp.util.FAKE_RESULTS
import com.example.android.couroteamapp.util.FakeNetworkCall
import com.example.android.couroteamapp.util.fakeNetworkLibrary

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
