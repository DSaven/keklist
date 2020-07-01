package com.example.keklist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.keklist.models.AdToSend
import com.example.keklist.models.FormCallback
import com.example.keklist.models.Token
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_form.description
import kotlinx.android.synthetic.main.activity_form.price
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        cancelBtn.setOnClickListener {
            finish()
        }

        saveBtn.isClickable = titleAd.text.isNotBlank() && imgURL.text.isNotBlank() && phoneNum.text.isNotBlank() && description.text.isNotBlank() && price.text.isNotBlank()

        saveBtn.setOnClickListener {

            var categoryId: Int = if (services.isSelected)
                2
            else
                1

            KeklistService.create().postAd(
                Token.token.toString(),
                AdToSend(titleAd.text.toString().trim(), categoryId, description.text.toString(), price.text.toString().toInt(), imgURL.text.toString(), phoneNum.text.toString())
            )
                .enqueue(object : Callback<FormCallback>{
                    override fun onFailure(call: Call<FormCallback>, t: Throwable) {
                        Toast.makeText(this@FormActivity, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<FormCallback>,
                        response: Response<FormCallback>
                    ) {
                        Toast.makeText(this@FormActivity, response.body()!!.status, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@FormActivity, MainListActivity::class.java)
                        startActivity(intent)
                    }
                })

        }
    }
}