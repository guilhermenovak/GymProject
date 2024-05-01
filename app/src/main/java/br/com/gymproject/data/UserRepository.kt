package br.com.gymproject.data

import br.com.gymproject.data.local.database.User
import br.com.gymproject.data.network.UserRemoteDataSource

class UserRepository {
    private val userRemoteDataSource: UserRemoteDataSource = UserRemoteDataSource()

    fun insertUser(user: User) = userRemoteDataSource.insertUser(user)

    fun getUserByEmail(email: String?) = email?.let { userRemoteDataSource.getUserByEmail(it) }
}