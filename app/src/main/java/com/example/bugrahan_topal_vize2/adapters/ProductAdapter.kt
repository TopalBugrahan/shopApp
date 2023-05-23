package com.example.bugrahan_topal_vize2.adapters

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bugrahan_topal_vize2.R
import com.example.bugrahan_topal_vize2.models.Product
import com.example.bugrahan_topal_vize2.models.Products

class ProductAdapter(private val context: Activity , private val products:MutableList<Product>):ArrayAdapter<Product>(context,R.layout.products_costume_list_view,products) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView=context.layoutInflater.inflate(R.layout.products_costume_list_view,null)

        val prdTitle=rootView.findViewById<TextView>(R.id.prdTitle)
        val prdDes=rootView.findViewById<TextView>(R.id.prdDes)
        val prdStock=rootView.findViewById<TextView>(R.id.prdStock)
        val prdPrice=rootView.findViewById<TextView>(R.id.prdPrice)
        val prdImage=rootView.findViewById<ImageView>(R.id.prdImage)

        val product=products.get(position)

        prdTitle.text=product.title.toString()
        prdDes.text=product.description
        prdPrice.text = product.price.toString()+"$"
        prdStock.text=product.stock.toString()+" Adet Kaldı"

        if(product.stock<10){
            prdStock.setTextColor(Color.parseColor("#FF0000"))
        }

        Glide.with(context)
            .load(product.images[0])
            .override(100,100)
            .centerCrop()
            .into(prdImage)

        return rootView
    }
}