package com.example.keklist

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.keklist.models.Token
import com.example.keklist.models.UserInfo
import kotlinx.android.synthetic.main.acitivity_cab.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_cab)

        KeklistService.create().getUser(Token.token.toString())
            .enqueue(object : Callback<UserInfo> {
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    Toast.makeText(this@CabActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    nameData.text = response.body()!!.name

                    if (response.body()!!.access_id == 1)
                        adminData.text = "Админ"
                    else
                        adminData.text = "Пользователь"
                }
            })
    }
}