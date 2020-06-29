package com.example.keklist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.keklist.models.Ad
import com.example.keklist.models.Token
import kotlinx.android.synthetic.main.activity_main_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalAd : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        addBtn.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        KeklistService.create().getUserAds(Token.token!!)
            .enqueue(object : Callback<List<Ad>>{
                override fun onFailure(call: Call<List<Ad>>, t: Throwable) {
                    Toast.makeText(this@PersonalAd, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<Ad>>, response: Response<List<Ad>>) {
                    val adapter = AdAdapter(this@PersonalAd, response.body()!!)
                    listView.adapter = adapter

                    listView.setOnItemClickListener {parent, view, position, id ->
                        val id = adapter.getItem(position).id

                        Toast.makeText(this@PersonalAd, id.toString(), Toast.LENGTH_SHORT).show()

                        //TODO добавить интент для перехода на подробную рекламу после того, как этот додик починит выдачу
                    }
                }
            })
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