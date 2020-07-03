package com.example.keklist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.keklist.models.Ad
import java.net.URL

class AdAdapter (private val context: Context, private val data: List<Ad>): BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.ad_item_list, parent, false)

        val nameView = rowView.findViewById(R.id.name) as TextView
        val priceView = rowView.findViewById(R.id.price) as TextView

        nameView.text = getItem(position).title
        priceView.text = getItem(position).price.toString() + " руб."

        return rowView
    }

    override fun getItem(position: Int): Ad {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }
}