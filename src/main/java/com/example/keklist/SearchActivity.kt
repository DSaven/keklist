package com.example.keklist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.keklist.models.Ad
import kotlinx.android.synthetic.main.activity_main_list.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.listView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchBtn.setOnClickListener {
            KeklistService.create().getAllAds()
                .enqueue(object : Callback<List<Ad>> {
                    override fun onFailure(call: Call<List<Ad>>, t: Throwable) {
                        Toast.makeText(this@SearchActivity, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<List<Ad>>, response: Response<List<Ad>>) {

                        val data = response.body()!!.filter { it.title.trim().toLowerCase() == searchTxt.text.toString().trim().toLowerCase() }

                        val adapter = AdAdapter(this@SearchActivity, data)
                        listView.adapter = adapter

                        listView.setOnItemClickListener {parent, view, position, id ->
                            val id = adapter.getItem(position).id

                            val intent = Intent(this@SearchActivity, AdActivity::class.java)
                            intent.putExtra("id", id)
                            startActivity(intent)
                        }
                    }

                })
        }
    }
}