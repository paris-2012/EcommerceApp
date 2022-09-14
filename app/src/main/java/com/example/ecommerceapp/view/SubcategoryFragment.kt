package com.example.ecommerceapp.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentSubcategoryBinding
import com.example.ecommerceapp.model.local.CartDao
import com.example.ecommerceapp.model.remote.VolleyHandler
import com.example.ecommerceapp.presenter.CategoriesPresenter
import com.example.ecommerceapp.presenter.SubcategoriesMVP
import com.example.ecommerceapp.presenter.SubcategoriesPresenter
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */


class SubcategoryFragment(private val subcategoryId: String) : Fragment(R.layout.fragment_subcategory), SubcategoriesMVP.SubcategoriesView {
    private var _binding: FragmentSubcategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var subAdapter: SubcategoriesAdapter
    private lateinit var presenter: SubcategoriesPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubcategoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val volleyHandler = VolleyHandler(view.context)
        presenter = SubcategoriesPresenter(volleyHandler, this)
        setResult(presenter.findProducts(subcategoryId))
    }
    override fun setResult(names: MutableList<Array<String>>) {

        binding.recyclerView.layoutManager = GridLayoutManager(view?.context, 2)
        subAdapter = SubcategoriesAdapter(names, view?.context!!)
        binding.recyclerView.adapter = subAdapter
    }
}