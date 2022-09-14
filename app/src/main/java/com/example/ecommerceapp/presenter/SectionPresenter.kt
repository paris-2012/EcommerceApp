package com.example.ecommerceapp.presenter

import com.example.ecommerceapp.model.remote.OperationalCallbackCategories
import com.example.ecommerceapp.model.remote.VolleyHandler

class SectionPresenter(
    private val volleyHandler: VolleyHandler,
    private val sectionView: SectionMVP.SectionView
) : SectionMVP.SectionPresenter {
    override fun findSubCategories(category: String?): MutableList<Array<String>> {
        val message = volleyHandler.getSubCategories(category ?: "1", object : OperationalCallbackCategories {

            override fun onSuccess(message: MutableList<Array<String>>) {
                sectionView.setResult(message)
            }

            override fun onFailure(message: MutableList<Array<String>>) {
                sectionView.setResult(message)
            }
        })
        return message
    }

}