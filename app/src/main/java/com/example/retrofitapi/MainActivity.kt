package com.example.retrofitapi

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapi.adapters.NewsRVAdapter
import com.example.retrofitapi.databinding.ActivityMainBinding
import com.example.retrofitapi.newsly.NewsDataClass
import com.example.retrofitapi.newsly.RetroInstance
import com.example.retrofitapi.pixabay.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var progressDialog: ProgressDialog
    lateinit var adapter: NewsRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this@MainActivity)

        //Generate Img
        binding.generateImgBtn.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                getData()
            }
        }

        //Generate Text
        binding.generateTxtBtn.setOnClickListener {
            lifecycleScope.launch {
                getData2()
            }
        }

        //Generate News
        binding.generateNewsBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                getNews()
            }
        }
    }

    private suspend fun getData() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Data is being fetched")
        progressDialog.show()

        val body = RetrofitInstance.apiInterface.getQuotes(
            "34596869-2603d2ceeb94ebf38bae910e8",
            "News",
            "photo"
        ).body()


        Log.d("TAGBody", "getData: ${body.toString()}")

        try {
            val x = Math.random() * 20

//            Glide.with(this@MainActivity)
//                .load(body?.hits?.get(x.toInt())?.largeImageURL)
//                .into(binding.glideIV)

            Toast.makeText(this@MainActivity, "Response is successful", Toast.LENGTH_SHORT).show()
            progressDialog.dismiss()

        } catch (e: Exception) {

            Toast.makeText(this@MainActivity, "Response Failed ${e.message}", Toast.LENGTH_SHORT)
                .show()
            progressDialog.dismiss()
//            Log.d("TAGURL", "onResponse: ${response.body()?.url}")

        }
    }

    private fun getData2() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Data is being fetched")
        progressDialog.show()

        try {
//            binding.generateText.text = RetrofitInstance2.todoApi.getData().body()?.get(0)?.title
            Toast.makeText(this@MainActivity, "Response is successful", Toast.LENGTH_SHORT).show()
            progressDialog.dismiss()

        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Response failed ${e.message}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private suspend fun getNews() {
        withContext(Dispatchers.Main) {
            progressDialog.setMessage("Latest headlines are being fetched!")
            progressDialog.show()
        }

        try {

            val x = Math.random() * 4

            val news = RetroInstance.newsInterface.getNews("us", x.toInt())
            Log.d("TAGRand", "Random: ${x.toInt()}")

            withContext(Dispatchers.Main) {

                news.enqueue(object : Callback<NewsDataClass> {
                    override fun onResponse(
                        call: Call<NewsDataClass>,
                        response: retrofit2.Response<NewsDataClass>
                    ) {
                        val news2 = response.body()
                        if (news2 != null) {
                            adapter = NewsRVAdapter(this@MainActivity, news2.articles)

//                        adapter = news.body()?.let { NewsRVAdapter(this@MainActivity, it.articles) }!!
                            binding.newsRV.adapter = adapter
                            binding.newsRV.layoutManager = LinearLayoutManager(this@MainActivity)
                        } else {
                            Toast.makeText(this@MainActivity, "Empty", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<NewsDataClass>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
                progressDialog.dismiss()
            }

//                binding.generateText.text = news.body()?.articles?.get((Math.random()*20).toInt())?.title

//                val body : retrofit2.Response<NewsDataClass>
//                val news2:NewsDataClass = Response.Builder().body(body).build()


            //            binding.generateText.text = news.body()?.title?.get(0).toString()

        } catch (e: IOException) {
//            Toast.makeText(this, "Error fetching news: ${e.message}", Toast.LENGTH_LONG).show()
            Log.d("TAG500", "getNews: ${e.message}")
        }

    }
}