package com.example.ecommerceapp.model.remote

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.*

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


    fun userLogin(user: LoginCredentials, callBack: OperationalCallbackLogin): Array<String> {
        val url = "${Constants.BASE_URL}${Constants.LOGIN_END_POINT}"
        val data = JSONObject()
        var message: Array<String> = arrayOf("a", "b")

        data.put("email_id", user.email)
        data.put("password", user.password)

        val request = JsonObjectRequest(Request.Method.POST, url, data, { response: JSONObject ->
            message = arrayOf(response.getString("message"), response.getJSONObject("user").getString("user_id"))
            Log.i(TAG, message.toString())
            callBack.onSuccess(message)
        }, { error: VolleyError ->
            error.printStackTrace()
            message = arrayOf(error.message.toString(), "0")
            Log.i(TAG, error.toString())
            callBack.onFailure(message)
        })
        requestQueue.add(request)
        return message
    }

    fun getCategories(callBack: OperationalCallbackCategories): MutableList<Array<String>> {
        val items: MutableList<Array<String>> = mutableListOf()
        val url = "${Constants.BASE_URL}${Constants.CATEGORY_END_POINT}"
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response: JSONObject ->
            val myCategories = response.getJSONArray("categories")
            for (i in 0 until myCategories.length()) {
                val myArray = arrayOf(
                    myCategories.getJSONObject(i).getString("category_name"),
                    myCategories.getJSONObject(i).getString("category_image_url"),
                    myCategories.getJSONObject(i).getString("category_id"))
                items.add(myArray)
            }
            callBack.onSuccess(items)
        }, { error: VolleyError ->
            error.printStackTrace()
            Log.i(TAG, error.toString())
            callBack.onFailure(items)
        })
        requestQueue.add(request)
        return items
    }

    fun getSubCategories(category: String?, callBack: OperationalCallbackCategories) : MutableList<Array<String>> {
        val items: MutableList<Array<String>> = mutableListOf()
        val url = "${Constants.BASE_URL}SubCategory?category_id=${category}"
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response: JSONObject ->
            val myCategories = response.getJSONArray("subcategories")
            for (i in 0 until myCategories.length()) {
                val myArray = arrayOf(
                    myCategories.getJSONObject(i).getString("subcategory_name"),
                    myCategories.getJSONObject(i).getString("subcategory_image_url"),
                    myCategories.getJSONObject(i).getString("subcategory_id"))
                items.add(myArray)
            }
            callBack.onSuccess(items)
        }, { error: VolleyError ->
            error.printStackTrace()
            Log.i(TAG, error.toString())
            callBack.onFailure(items)
        })
        requestQueue.add(request)
        return items
    }


    fun getProducts(subcategory: String?, callBack: OperationalCallbackCategories) : MutableList<Array<String>> {
        val items: MutableList<Array<String>> = mutableListOf()
        val url = "${Constants.BASE_URL}SubCategory/products/${subcategory}"
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response: JSONObject ->
            val myCategories = response.getJSONArray("products")
            for (i in 0 until myCategories.length()) {
                val myArray = arrayOf(
                    myCategories.getJSONObject(i).getString("product_name"),
                    myCategories.getJSONObject(i).getString("product_image_url"),
                    myCategories.getJSONObject(i).getString("product_id"),
                    myCategories.getJSONObject(i).getString("price"))
                items.add(myArray)
            }
            callBack.onSuccess(items)
        }, { error: VolleyError ->
            error.printStackTrace()
            Log.i(TAG, error.toString())
            callBack.onFailure(items)
        })
        requestQueue.add(request)
        return items
    }


    fun getProductInfo(productId: String?, callBack: OperationalCallbackProduct) : Array<String> {
        val url = "${Constants.BASE_URL}Product/details/${productId}"
        var myArray: Array<String> = arrayOf("a","b","c","d","e")
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response: JSONObject ->
            val myCategories = response.getJSONObject("product")

            val newArray = arrayOf(
                myCategories.getString("product_name"),
                myCategories.getString("product_image_url"),
                myCategories.getString("product_id"),
                myCategories.getString("price"),
                myCategories.getString("description")
            )
            myArray = newArray
            callBack.onSuccess(myArray)
        }, { error: VolleyError ->
            error.printStackTrace()
            Log.i(TAG, error.toString())
            callBack.onFailure(myArray)
        })
        requestQueue.add(request)
        return myArray
    }

    fun getAddresses(userId: String?, callBack: OperationalCallbackCategories) : MutableList<Array<String>> {
        val items: MutableList<Array<String>> = mutableListOf()
        val url = "${Constants.BASE_URL}/User/addresses/${userId}"
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response: JSONObject ->
            val myCategories = response.getJSONArray("addresses")
            for (i in 0 until myCategories.length()) {
                val myArray = arrayOf(
                    myCategories.getJSONObject(i).getString("address_id"),
                    myCategories.getJSONObject(i).getString("title"),
                    myCategories.getJSONObject(i).getString("address"))
                items.add(myArray)
            }
            callBack.onSuccess(items)
        }, { error: VolleyError ->
            error.printStackTrace()
            Log.i(TAG, error.toString())
            callBack.onFailure(items)
        })
        requestQueue.add(request)
        return items
    }

    fun addAddress(userId: String, title: String, address: String, callBack: OperationalCallback): String? {
        val url = "${Constants.BASE_URL}User/address"
        val data = JSONObject()
        var message: String? = null

        data.put("user_id", userId)
        data.put("title", title)
        data.put("address", address)

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

    fun orderConfirmation(order: Order, callBack: OperationalCallback): String? {
        val url = "${Constants.BASE_URL}Order/userOrders/${order.userId}"
        val data = JSONObject()
        var message: String? = null

        data.put("user_id", order.userId)
        val deliveryAddress = JSONObject()
        deliveryAddress.put("title", order.title)
        deliveryAddress.put("address", order.address)
        data.put("delivery_address", deliveryAddress)
        data.put("items", order.items)
        data.put("bill_amount", order.billAmount)
        data.put("payment_method", order.paymentMethod)
        Log.i("order json", data.toString())
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
    companion object {
        const val TAG = "TAG"
    }
}
