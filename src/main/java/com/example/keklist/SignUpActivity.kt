package com.example.keklist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.keklist.models.FormCallback
import com.example.keklist.models.SignUpModel
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

//        registerBtn.isClickable = name.text.isNotBlank() && login.text.isNotBlank() && password.text.isNotBlank() && password.text.toString() == passwordRep.text.toString()

        cancelBtn.setOnClickListener {
            finish()
        }

        registerBtn.setOnClickListener {

            if (login.text.toString().isNotBlank() && name.text.toString().isNotBlank() && password.text.toString().isNotBlank() && passwordRep.text.toString() == password.text.toString()) {
                KeklistService.create().createUser(SignUpModel(login.text.toString(), name.text.toString(), password.text.toString(), passwordRep.text.toString(), phone.text.toString()))
                    .enqueue(object : Callback<FormCallback> {
                        override fun onFailure(call: Call<FormCallback>, t: Throwable) {
                            Toast.makeText(this@SignUpActivity, t.message, Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(
                            call: Call<FormCallback>,
                            response: Response<FormCallback>
                        ) {
                            Toast.makeText(this@SignUpActivity, response.body()!!.status, Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    })
            } else
                Toast.makeText(this, "Часть полей незаполнены или пароли не совпадают", Toast.LENGTH_SHORT).show()


        }
    }
}