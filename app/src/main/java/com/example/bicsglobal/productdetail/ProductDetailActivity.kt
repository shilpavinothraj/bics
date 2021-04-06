package com.example.bicsglobal.productdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.bicsglobal.R
import com.example.bicsglobal.databinding.ActivityProductDetailBinding


class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityProductDetailBinding
     var url: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)
        binding.productName.text= SpannableStringBuilder( intent.getStringExtra("title"))
        binding.tvDescription.text= SpannableStringBuilder( intent.getStringExtra("detail"))
        if ( intent.getStringExtra("image")!=null){
             url= SpannableStringBuilder( intent.getStringExtra("image")).toString()
        }
        binding.tvToolbarTitle.text= SpannableStringBuilder( intent.getStringExtra("title"))
        binding.publish.text= SpannableStringBuilder( intent.getStringExtra("publish"))
        if (intent.getStringExtra("author")!=null){
            binding.author.text= SpannableStringBuilder( intent.getStringExtra("author"))
        }else{
            binding.author.visibility=View.GONE
        }
        var link:String= SpannableStringBuilder( intent.getStringExtra("link")).toString()

        if (url!=null)
            Glide.with(this)
                .load(url)
                .placeholder(R.drawable.no_img)
                .dontAnimate().into(binding.productimage)
        binding.toolbarBack.setOnClickListener(View.OnClickListener {
            onBackPressed()

        })
        val anim: Animation = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 500
        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        if (link!=null){
            binding.tvMore.visibility=View.VISIBLE
        }else{
            binding.tvMore.visibility=View.GONE
        }
        binding.tvMore.startAnimation(anim)
        binding.tvMore.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse(link)
            startActivity(intent)
        })

    }
}