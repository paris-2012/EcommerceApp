package com.example.ecommerceapp.presenter


class CategoriesMVP {
    interface CategoriesView {
        fun setResult(names: MutableList<Array<String>>)
    }

    interface CategoriesPresenter {
        fun findCategories(): MutableList<Array<String>>
    }
}