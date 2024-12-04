package com.example.readytoenjoy.core.data.adven

import com.example.readytoenjoy.core.data.activity.Activity
import com.example.readytoenjoy.core.local.UserDao
import com.example.readytoenjoy.core.local.UserEntity
import com.example.readytoenjoy.core.network.ReadyToEnjoyApiService
import com.example.readytoenjoy.core.network.activity.ActivityResponse
import com.example.readytoenjoy.core.network.adevn.AdvenResponseLR
import com.example.readytoenjoy.core.network.adevn.LoginRequest
import com.example.readytoenjoy.core.network.adevn.User
import org.intellij.lang.annotations.Identifier
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton



fun AdvenResponseLR.toExternal(): User {
    return User(
        jwt= this.jwt,
        id = this.user.id,
        name = this.user.name,
        email =  this.user.email,
    )
}
@Singleton
class LoginRepository @Inject constructor(private val userDao: UserDao,private val api: ReadyToEnjoyApiService) {

    suspend fun login(identifier: String, password: String): Boolean {
        val response = api.login(LoginRequest(identifier, password))
        val userEntity = UserEntity(
            //id = response.user.id,
           // name = response.user.username,
            //email = response.user.email,
           // jwt = response.jwt
        )
        userDao.createUser(userEntity) // Guardar usuario en Room
        return userEntity

    }

    suspend fun getLocalUser(): UserEntity?{
        return userDao.getUser()
    }
}