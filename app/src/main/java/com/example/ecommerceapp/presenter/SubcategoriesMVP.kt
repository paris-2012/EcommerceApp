package com.example.ecommerceapp.presenter

class SubcategoriesMVP {
    interface SubcategoriesView {
        fun setResult(names: MutableList<Array<String>>)
    }

    interface SubcategoriesPresenter {
        fun findProducts(category: String?): MutableList<Array<String>>
    }
}