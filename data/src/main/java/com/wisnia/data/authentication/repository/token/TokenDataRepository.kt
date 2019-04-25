package com.wisnia.data.authentication.repository.token

import com.wisnia.domain.authentication.model.Token
import com.wisnia.domain.authentication.repository.TokenRepository
import io.reactivex.Single
import javax.inject.Inject

class TokenDataRepository @Inject constructor(private val httpTokenRepository: HttpTokenRepository) :
    TokenRepository {

    override val token: Single<Token>
        get() = httpTokenRepository.token
}