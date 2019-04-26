package com.wisnia.data.authentication.repository.token

import com.wisnia.data.authentication.model.Token
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

private const val AUTHENTICATION_HEADER_KEY = "Authentication-Callback"

class AuthenticationHeaderInterceptor @Inject constructor() {

    fun intercept(tokenResponse: Response<Token>): Single<Token> {
        val authenticationHeader = tokenResponse.headers().get(AUTHENTICATION_HEADER_KEY)
        val token = tokenResponse.body()
        token?.authenticationPage = authenticationHeader
        return Single.just(token)
    }
}
