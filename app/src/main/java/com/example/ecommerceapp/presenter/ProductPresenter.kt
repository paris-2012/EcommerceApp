package com.example.ecommerceapp.presenter

import com.example.ecommerceapp.model.remote.OperationalCallbackCategories
import com.example.ecommerceapp.model.remote.OperationalCallbackProduct
import com.example.ecommerceapp.model.remote.VolleyHandler

class ProductPresenter(
    private val volleyHandler: VolleyHandler,
    private val productView: ProductMVP.ProductView
    ) : ProductMVP.ProductPresenter {
        override fun findProductInfo(productInfo: String?): Array<String> {
            val message = volleyHandler.getProductInfo(productInfo, object : OperationalCallbackProduct {

                override fun onSuccess(message: Array<String>) {
                    productView.setResult(message)
                }

                override fun onFailure(message: Array<String>) {
                    productView.setResult(message)
                }
            })
            return message
        }

    }