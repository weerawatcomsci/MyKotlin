package com.example.hotumit.mykotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.akexorcist.recyclerviewwithitemtouchhelper.CustomItemTouchHelperCallback
import com.example.hotumit.mykotlin.R.id.rvAndroidVersion
import com.example.hotumit.mykotlin.adapter.InfoAdapter
import com.example.hotumit.mykotlin.model.PhotoItemCollectionDao
import com.example.hotumit.mykotlin.model.singleton.HttpManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class    MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
    }

    private fun getData(){
    val call = HttpManager.ApiService()

        call.loadPhotoList().enqueue(object : Callback<PhotoItemCollectionDao> {
            override fun onResponse(call: Call<PhotoItemCollectionDao>, response: Response<PhotoItemCollectionDao>) {
                if (response.isSuccessful()) {
                    val dao = response.body()
                    Log.e("tomtom","tomtom"+dao);
                    val adapter = InfoAdapter(dao)
                    rvAndroidVersion.layoutManager = LinearLayoutManager(this@MainActivity)
                    rvAndroidVersion.adapter = adapter


                }

            }

            override fun onFailure(call: Call<PhotoItemCollectionDao>, t: Throwable) {

            }
        })





    }
}
