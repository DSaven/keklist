package com.example.keklist

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.keklist.models.FormCallback
import com.example.keklist.models.SignUpModel
import com.example.keklist.models.Token
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        cancelBtn.setOnClickListener {
            finish()
        }

        registerBtn.setOnClickListener {

            if (login.text.toString().isNotBlank() && name.text.toString().isNotBlank() && password.text.toString().isNotBlank() && passwordRep.text.toString() == password.text.toString()) {
                KeklistService.create().createAdmin(Token.token.toString(), SignUpModel(name.text.toString(), login.text.toString(), password.text.toString(), passwordRep.text.toString(), phone.text.toString()))
                    .enqueue(object : Callback<FormCallback> {
                        override fun onFailure(call: Call<FormCallback>, t: Throwable) {
                            Toast.makeText(this@AddAdminActivity, t.message, Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(
                            call: Call<FormCallback>,
                            response: Response<FormCallback>
                        ) {
                            finish()
                        }
                    })
            } else
                Toast.makeText(this, "Часть полей незаполнены или пароли не совпадают", Toast.LENGTH_SHORT).show()
        }
    }
}