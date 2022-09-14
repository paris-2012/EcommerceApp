package com.example.ecommerceapp.presenter
import com.example.ecommerceapp.model.remote.*

class CategoriesPresenter(
    private val volleyHandler: VolleyHandler,
    private val categoriesView: CategoriesMVP.CategoriesView
) : CategoriesMVP.CategoriesPresenter {
    override fun findCategories(): MutableList<Array<String>> {
        val message = volleyHandler.getCategories(object : OperationalCallbackCategories {

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