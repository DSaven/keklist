package com.example.keklist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.keklist.models.Ad
import kotlinx.android.synthetic.main.activity_main_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StartListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_list)

        KeklistService.create().getAllAds()
            .enqueue(object : Callback<List<Ad>>{
                override fun onFailure(call: Call<List<Ad>>, t: Throwable) {
                    Toast.makeText(this@StartListActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<Ad>>, response: Response<List<Ad>>) {
                    val adapter = AdAdapter(this@StartListActivity, response.body()!!)
                    listView.adapter = adapter

                    listView.setOnItemClickListener {parent, view, position, id ->
                        val id = adapter.getItem(position).id

                        val intent = Intent(this@StartListActivity, AdActivity::class.java)
                        intent.putExtra("id", id)
                        startActivity(intent)
                    }
                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_start, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.authorization -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}