package com.example.bugrahan_topal_vize2.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bugrahan_topal_vize2.R
import com.example.bugrahan_topal_vize2.models.Product
import com.example.bugrahan_topal_vize2.models.ProductForCard

class CartAdapter(private val context: Activity, private val products:MutableList<ProductForCard>): ArrayAdapter<ProductForCard>(context, R.layout.cart_costume_list_view,products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.cart_costume_list_view, null)

        val titleCard = rootView.findViewById<TextView>(R.id.titleCard)
        val priceCart = rootView.findViewById<TextView>(R.id.priceCart)
        val totleCart = rootView.findViewById<TextView>(R.id.totleCart)
        val countCart = rootView.findViewById<TextView>(R.id.countCart)

        val data = products.get(position)

        titleCard.text = data.title
        priceCart.text = "Birim Fiyat: "+data.price.toString()+"$"
        totleCart.text = "Toplam Fiyat: "+data.total.toString() +"$"
        countCart.text = "Adet: "+data.quantity.toString() +" Tane"

        return rootView

    }
}