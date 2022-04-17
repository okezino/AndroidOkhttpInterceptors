package com.example.test.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.test.network.NetworkCall
import com.example.test.R
import com.example.test.UserInput
import com.example.test.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    lateinit var userEmail : EditText
    lateinit var userPassword : EditText
    lateinit var loginButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userEmail = findViewById(R.id.email_et)
        userPassword = findViewById(R.id.password_et)
        loginButton = findViewById(R.id.login_btn)


        loginButton.setOnClickListener {


             val user = UserInput("eve.holt@reqres.in", "cityslicka")

            NetworkCall.loginApiService.loginUsers(user)
                .enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                   val result =  response.body()

                    result?.let {
                        Log.d(TAG, it.token)
                        Toast.makeText(this@MainActivity, it.token, Toast.LENGTH_SHORT).show()
                    } ?: Toast.makeText(this@MainActivity, "Something went Wrong", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Network Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }


    }


}