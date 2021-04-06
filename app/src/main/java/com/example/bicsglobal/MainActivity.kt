package com.example.bicsglobal

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bicsglobal.adapter.ProductListAdapter
import com.example.bicsglobal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel
    private var productListAdapter:ProductListAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        if (isInternetAvailable(this)){
            binding.progressBar.visibility= View.VISIBLE
            viewModel.productList()
        }else{
            showAlert()
        }
        viewModel.productlistResponsedata.observe(this@MainActivity, Observer {
            it?.let {
                if (it.status!=null){
                    binding.progressBar.visibility=View.GONE
                    productListAdapter = ProductListAdapter(this@MainActivity,it?.articles)
                    val mGridLayoutManager = GridLayoutManager(this, 1)
                    mGridLayoutManager.orientation = LinearLayoutManager.VERTICAL
                    binding.rvProducts.setLayoutManager(mGridLayoutManager)
                    binding.rvProducts.setNestedScrollingEnabled(false)
                    binding.rvProducts.setAdapter(productListAdapter)                }
            }
        })


    }
    fun isInternetAvailable(activity: Context?): Boolean {
        if (activity != null) {
            val cm =
                activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo: NetworkInfo? = null
            if (cm != null && cm.activeNetworkInfo != null) activeNetworkInfo =
                cm.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable && activeNetworkInfo.isConnected
        }
        return false
    }
    fun showAlert(){
        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(this, R.style.AlertDialogCustom)
        builder.setTitle("Network Error")
        builder.setMessage("Please check your internet connection")
            .setPositiveButton("ok",
                DialogInterface.OnClickListener { dialog, which -> // continue with delete
                    dialog.cancel()
                })

            .show()
    }

}