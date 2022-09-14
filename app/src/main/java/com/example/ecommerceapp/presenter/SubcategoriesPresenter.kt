package com.example.ecommerceapp.presenter

import com.example.ecommerceapp.model.remote.OperationalCallbackCategories
import com.example.ecommerceapp.model.remote.VolleyHandler

class SubcategoriesPresenter(
    private val volleyHandler: VolleyHandler,
    private val categoriesView: SubcategoriesMVP.SubcategoriesView
) : SubcategoriesMVP.SubcategoriesPresenter {
    override fun findProducts(category: String?): MutableList<Array<String>> {
        val message = volleyHandler.getProducts(category ?: "1", object : OperationalCallbackCategories {

            override fun onSuccess(message: MutableList<Array<String>>) {
                categoriesView.setResult(message)
            }

            override fun onFailure(message: MutableList<Array<String>>) {
                categoriesView.setResult(message)
            }
        })
        return message
    }

}