package com.example.keklist
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import com.bumptech.glide.Glide
import com.example.keklist.models.Ad
import com.example.keklist.models.FormCallback
import com.example.keklist.models.Token
import com.example.keklist.models.UserInfo
import kotlinx.android.synthetic.main.activity_ad.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad)

        val id = intent.getIntExtra("id", 0)

        KeklistService.create().getAdbyID(id)
            .enqueue(object: Callback<Ad> {
                override fun onFailure(call: Call<Ad>, t: Throwable) {
                    Toast.makeText(this@AdActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Ad>, response: Response<Ad>) {
                    val ad = response.body()!!
                    adTitle.text = ad.title
                    description.text = ad.description
                    price.text = ad.price.toString() + " руб."
                    phoneNum.text = ad.phone_number
                    GlideApp.with(this@AdActivity)
                        .load(ad.img)
                        .error(ContextCompat.getDrawable(this@AdActivity, R.drawable.errorimg))
                        .into(image)


                    if(!Token.token.isNullOrEmpty()){
                        KeklistService.create().getUser(Token.token!!)
                            .enqueue(object : Callback<UserInfo> {
                                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                                    Toast.makeText(this@AdActivity, t.message, Toast.LENGTH_SHORT).show()
                                }

                                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                                    val user = response.body()!!

                                    if (user.access_id == 1 || user.name == ad.user_name) {
                                        changeBtn.visibility = View.VISIBLE
                                        deleteBtn.visibility = View.VISIBLE
                                    }

                                    deleteBtn.setOnClickListener {
                                        KeklistService.create().deleteAd(Token.token.toString(), ad.id)
                                            .enqueue(object : Callback<FormCallback>{
                                                override fun onFailure(
                                                    call: Call<FormCallback>,
                                                    t: Throwable
                                                ) {
                                                    Toast.makeText(this@AdActivity, t.message, Toast.LENGTH_SHORT).show()
                                                }

                                                override fun onResponse(
                                                    call: Call<FormCallback>,
                                                    response: Response<FormCallback>
                                                ) {
                                                    Toast.makeText(this@AdActivity, response.body()!!.status, Toast.LENGTH_SHORT).show()
                                                    val intent = Intent(this@AdActivity, MainListActivity::class.java)
                                                    startActivity(intent)
                                                }
                                            })
                                    }

                                    changeBtn.setOnClickListener {
                                        Toast.makeText(this@AdActivity, id.toString(), Toast.LENGTH_SHORT).show()

                                        val intent = Intent(this@AdActivity, UpdateFormActivity::class.java)
                                        intent.putExtra("id", id)
                                        startActivity(intent)
                                    }
                                }
                            })
                    }

                }
            })
    }
}