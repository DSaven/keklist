package com.example.keklist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.keklist.models.Ad
import com.example.keklist.models.AdToSend
import com.example.keklist.models.FormCallback
import com.example.keklist.models.Token
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

                    titleAd.setText(ad.title)
                    description.setText(ad.description)
                    imgURL.setText(ad.img)
                    price.setText(ad.price.toString())

                    if (ad.category == "Товары")
                        goodsBtn.isChecked = true
                    else
                        servicesBtn.isChecked = true

//                    saveBtn.isEnabled = titleAd.text.isNotBlank() && imgURL.text.isNotBlank() && phoneNum.text.isNotBlank() && description.text.isNotBlank() && price.text.isNotBlank()
//                    saveBtn.isClickable = titleAd.text.isNotBlank() && imgURL.text.isNotBlank() && phoneNum.text.isNotBlank() && description.text.isNotBlank() && price.text.isNotBlank()

                    saveBtn.setOnClickListener {

                        var categoryId: Int = if (servicesBtn.isSelected)
                            2
                        else
                            1

                        KeklistService.create().updateAd(Token.token.toString(), id,
                        AdToSend(titleAd.text.toString().trim(), categoryId, description.text.toString(), price.text.toString().toInt(), imgURL.text.toString())
                        )
                            .enqueue(object : Callback<FormCallback>{
                                override fun onFailure(call: Call<FormCallback>, t: Throwable) {
                                    Toast.makeText(this@UpdateFormActivity, t.message, Toast.LENGTH_SHORT).show()
                                }

                                override fun onResponse(
                                    call: Call<FormCallback>,
                                    response: Response<FormCallback>
                                ) {
                                    Toast.makeText(this@UpdateFormActivity, response.body()!!.status, Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@UpdateFormActivity, MainListActivity::class.java)
                                    startActivity(intent)
                                }
                            })
                    }
                }
            })

        cancelBtn.setOnClickListener {
            finish()
        }
    }
}