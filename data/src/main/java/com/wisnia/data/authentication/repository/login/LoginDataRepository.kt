package com.wisnia.data.authentication.repository.login

import com.wisnia.data.authentication.repository.session.HttpSessionRepository
import com.wisnia.data.authentication.repository.token.HttpTokenRepository
import com.wisnia.domain.authentication.model.GuestSession
import com.wisnia.domain.authentication.model.Session
import com.wisnia.domain.authentication.repository.LoginRepository
import io.reactivex.Single
import javax.inject.Inject

class LoginDataRepository @Inject constructor(
    private val httpLoginRepository: HttpLoginRepository,
    private val httpTokenRepository: HttpTokenRepository,
    private val httpSessionRepository: HttpSessionRepository
) : LoginRepository {

    override fun signIn(username: String, password: String): Single<Session> =
        httpTokenRepository.token
            .flatMap { httpLoginRepository.signIn(username, password, it.token) }
            .flatMap { httpSessionRepository.session(it.token) }

    override val signInAsGuest: Single<GuestSession>
        get() = httpSessionRepository.guestSession
}