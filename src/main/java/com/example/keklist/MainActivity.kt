package com.example.keklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.keklist.models.Token
import com.example.keklist.models.User
import com.example.keklist.models.UserToken
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signupBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        enterBtn.setOnClickListener {
            KeklistService.create().login(
                User(
                    login.text.toString(),
                    password.text.toString()
                )
            )
                .enqueue(object : retrofit2.Callback<UserToken> {
                    override fun onFailure(call: Call<UserToken>, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
                        Token.token = response.body()?.token
                        val intent = Intent(this@MainActivity, MainListActivity::class.java)
                        startActivity(intent)
                    }

                })
        }
    }
}
