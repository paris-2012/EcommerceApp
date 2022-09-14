package com.example.ecommerceapp.presenter

class SectionMVP {
    interface SectionView {
        fun setResult(subCategory: MutableList<Array<String>>)
    }
    interface SectionPresenter {
        fun findSubCategories(category: String?): MutableList<Array<String>>
    }
}
