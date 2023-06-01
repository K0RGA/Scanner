package com.example.scanner.data

import android.util.Log
import com.example.scanner.data.model.LoggedInUser
import com.example.scanner.data.model.ResponseLoginDTO
import com.example.scanner.data.model.UserDTO
import com.example.scanner.network.ScannerApi
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class LoginDataSource @Inject constructor(private val scannerApi: ScannerApi) {

    suspend fun login(user: UserDTO): Result<ResponseLoginDTO> {
        var res: Response<ResponseLoginDTO>
        try {
            res = scannerApi.login(user)
            val tokens = res.body()
            return Result.Success(tokens!!)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }
}