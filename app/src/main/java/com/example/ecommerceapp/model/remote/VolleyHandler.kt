package com.example.ecommerceapp.model.remote

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class   VolleyHandler(context: Context) {
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun userRegistration(user: User, callBack: OperationalCallback): String? {
        val url = "${Constants.BASE_URL}${Constants.REGISTRATION_END_POINT}"
        val data = JSONObject()
        var message: String? = null

        data.put("full_name", user.name)
        data.put("mobile_no", user.mobile)
        data.put("email_id", user.email)
        data.put("password", user.password)

        val request = JsonObjectRequest(Request.Method.POST, url, data, { response: JSONObject ->
            message = response.getString("message")
            Log.i(TAG, message.toString())
            callBack.onSuccess(message.toString())
        }, { error: VolleyError ->
            error.printStackTrace()
            message = error.message.toString()
            Log.i(TAG, error.toString())
            callBack.onFailure(message.toString())
        })
        requestQueue.add(request)
        return message
    }


    fun userLogin(user: LoginCredentials, callBack: OperationalCallback): String? {
        val url = "${Constants.BASE_URL}${Constants.LOGIN_END_POINT}"
        val data = JSONObject()
        var message: String? = null

        data.put("email_id", user.email)
        data.put("password", user.password)

        val request = JsonObjectRequest(Request.Method.POST, url, data, { response: JSONObject ->
            message = response.getString("message")
            Log.i(TAG, message.toString())
            callBack.onSuccess(message.toString())
        }, { error: VolleyError ->
            error.printStackTrace()
            message = error.message.toString()
            Log.i(TAG, error.toString())
            callBack.onFailure(message.toString())
        })
        requestQueue.add(request)
        return message
    }

    fun getCategories(): MutableList<String> {
        val items: MutableList<String> = mutableListOf()
        val url = "${Constants.BASE_URL}${Constants.CATEGORY_END_POINT}"
        var message: String? = null
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response: JSONObject ->
            message = response.getString("message")
            Log.i("dhello", message.toString())
            //callBack.onSuccess(message.toString())
            val myCategories = response.getJSONArray("categories")
            Log.i("dhello", myCategories.toString())
            Log.i("dhello", myCategories.getString(1))
        }, { error: VolleyError ->
            error.printStackTrace()
            message = error.message.toString()
            Log.i(TAG, error.toString())
            //callBack.onFailure(message.toString())
        })
        requestQueue.add(request)
        return items
    }

    companion object {
        const val TAG = "TAG"
    }
}
