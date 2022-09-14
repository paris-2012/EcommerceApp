package com.example.ecommerceapp.presenter

class ProductMVP {
    interface ProductView {
        fun setResult(productInfo: Array<String>)
    }
    interface ProductPresenter {
        fun findProductInfo(productInfo: String?): Array<String>
    }
}