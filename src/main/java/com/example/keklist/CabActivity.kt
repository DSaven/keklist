package com.example.keklist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
                    userNumberData.text = response.body()!!.phone_number


                    if (response.body()!!.access_id == 1)
                        adminData.text = "Админ"
                    else
                        adminData.text = "Пользователь"

                    if (response.body()!!.access_id != 1)
                        addAdmin.visibility = View.INVISIBLE
                }
            })

        addAdmin.setOnClickListener {
            val intent = Intent(this, AddAdminActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mainList -> {
                val intent = Intent(this, MainListActivity::class.java)
                startActivity(intent)
            }

            R.id.personalAd ->{
                val intent = Intent(this, PersonalAd::class.java)
                startActivity(intent)
            }
            R.id.search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.cab -> {
                val intent = Intent(this, CabActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}