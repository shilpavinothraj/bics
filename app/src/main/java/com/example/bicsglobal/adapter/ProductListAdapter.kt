package com.example.bicsglobal.adapter

import Articles
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bicsglobal.R
import com.example.bicsglobal.productdetail.ProductDetailActivity

class ProductListAdapter  (val mContext: Context,val products: List<Articles>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>(){
    lateinit var view: View


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.productlist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ProductListAdapter.ViewHolder, position: Int) {
        holder.bindItems(products.get(position), view, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(objectmenu: Articles, views: View, position: Int) {
            var productimage = view.findViewById<AppCompatImageView>(R.id.productimage)
            var product_heading = view.findViewById<AppCompatTextView>(R.id.tv_product_title)
            var layout=view.findViewById<ConstraintLayout>(R.id.layout)
            var add_btn=view.findViewById<AppCompatButton>(R.id.btn_view_product)




            add_btn.setOnClickListener(View.OnClickListener {
                layout.performClick()
            })
            layout.setOnClickListener(View.OnClickListener {
                var intent = Intent(mContext, ProductDetailActivity::class.java)
                intent.putExtra("image", products.get(position).urlToImage)
                intent.putExtra("title", products.get(position).title)
                intent.putExtra("link", products.get(position).url)
                intent.putExtra("detail", products.get(position).description)
                intent.putExtra("publish", products.get(position).publishedAt)
                intent.putExtra("author", products.get(position).author)
                mContext!!.startActivity(intent)

            })


            if (objectmenu.urlToImage!=null)
            Glide.with(mContext)
                    .load(objectmenu.urlToImage)
                    .placeholder(R.drawable.no_img)
                    .dontAnimate().into(productimage)
            product_heading.text=objectmenu.title

        }
    }
}