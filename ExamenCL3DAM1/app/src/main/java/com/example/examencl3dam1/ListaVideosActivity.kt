package com.example.examencl3dam1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examencl3dam1.ServicioREST.CustomVideoAdapter
import com.example.examencl3dam1.ServicioREST.VideoViewModel
import com.example.examencl3dam1.ServicioREST.QuoteAPI
import com.example.examencl3dam1.ServicioREST.RetrofitHelper
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListaVideosActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_videos)

        val quotesAPI = RetrofitHelper.getRetrofitInstance().create(QuoteAPI::class.java)

        val data = ArrayList<VideoViewModel>()

        val librosRecycler: RecyclerView = findViewById(R.id.rv_list_videos)
        librosRecycler.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            try {
                val response = quotesAPI.getQuotes()
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        val categories = result.categories
                        // Itera a través de las categorías y los videos dentro de ellas
                        categories.forEach { category ->
                            category.videos.forEach { video ->
                                val title = video.title
                                val description = video.description
                                val url = video.sources[0]
                                data.add(VideoViewModel(title, description,url))
                            }
                        }
                        runOnUiThread {
                            val adapter = CustomVideoAdapter(data)
                            librosRecycler.adapter = adapter
                        }
                    } else {
                        Log.e("API Error", "Result is null")
                    }
                } else {
                    Log.e("API Error", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                val response = quotesAPI.getQuotes()
                if (response.isSuccessful) {
                    val result = response.body()
                    Log.e("API Error","Exception 2: ${result}")
                }
                Log.e("API Error", "Exception 1: ${e.message} + ${response}")
            }
        }
    }
}