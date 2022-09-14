package com.example.ecommerceapp.model.remote

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import com.android.volley.Cache
import com.android.volley.Network
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.ImageLoader

class CustomVolleyRequestQueue(private val context: Context) {
    private lateinit var requestQueue: RequestQueue
    var imageLoader: ImageLoader
    init {
        imageLoader = ImageLoader(getRequestQueue(), object : ImageLoader.ImageCache {
            val cache = LruCache<String, Bitmap>(20)
            override fun getBitmap(url: String?) : Bitmap? {
                return cache[url]
            }
            override fun putBitmap(url: String?, bitmap: Bitmap?) {
                cache.put(url, bitmap)
            }
        })
    }
    private fun getRequestQueue(): RequestQueue {
        val cache: Cache = DiskBasedCache(context.cacheDir, 10 * 1024 * 1024)
        val network: Network = BasicNetwork(HurlStack())
        requestQueue = RequestQueue(cache, network)
        requestQueue.start()
        return requestQueue
    }
    companion object {
        var instance: CustomVolleyRequestQueue? = null
        @Synchronized
        fun getInstance(context: Context): CustomVolleyRequestQueue {
            if (instance == null) {
                instance = CustomVolleyRequestQueue(context)
            }
            return instance!!
        }
    }
}