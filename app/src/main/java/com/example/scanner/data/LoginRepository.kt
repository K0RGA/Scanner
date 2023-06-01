package com.example.scanner.data

import android.util.Log
import com.example.scanner.AppComponent
import com.example.scanner.data.model.LoggedInUser
import com.example.scanner.data.model.ResponseLoginDTO
import com.example.scanner.data.model.UserDTO
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository @Inject constructor(val dataSource: LoginDataSource) {


    suspend fun login(username: String, password: String): Result<ResponseLoginDTO> {
        val user = UserDTO(username, password)
        val result = dataSource.login(user)

        if (result is Result.Success) {
            AppComponent.accessToken = result.data.accesToken
            AppComponent.refreshToken = result.data.refreshToken
        }

        return result
    }

}