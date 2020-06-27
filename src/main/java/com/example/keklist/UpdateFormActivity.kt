package com.example.keklist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.keklist.models.Ad
import kotlinx.android.synthetic.main.activity_form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val id = intent.getIntExtra("id", 0)

        KeklistService.create().getAdbyID(id)
            .enqueue(object : Callback<Ad> {
                override fun onFailure(call: Call<Ad>, t: Throwable) {
                    Toast.makeText(this@UpdateFormActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Ad>, response: Response<Ad>) {
                    val ad = response.body()!!

                    //TODO ДОБАВИТЬ КАТЕГОРИИ
                    titleAd.setText(ad.title)
                    description.setText(ad.description)
                    imgURL.setText(ad.img)
                    price.setText(ad.price.toString())

                    saveBtn.setOnClickListener {
                        //TODO ДОБАВИТЬ МЕТОД АПДЕЙТА
                    }
                }
            })

        cancelBtn.setOnClickListener {
            finish()
        }
    }
}