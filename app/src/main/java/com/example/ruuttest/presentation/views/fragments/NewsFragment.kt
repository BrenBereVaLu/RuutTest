package com.example.ruuttest.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruuttest.R
import com.example.ruuttest.data.datas.NewsData
import com.example.ruuttest.databinding.FragmentNewsBinding
import com.example.ruuttest.domain.responses.newsStatement.NewsSentimentResponse
import com.example.ruuttest.presentation.bases.BaseFragment
import com.example.ruuttest.presentation.viewModels.NewsSentimentViewModel
import com.example.ruuttest.presentation.views.adapters.NewsAdapter
import com.example.ruuttest.utils.ViewModelFactoryNews
import com.example.ruuttest.utils.collectLatestLifeCycleFlow
import java.util.Locale

class NewsFragment : BaseFragment() {

    private lateinit var binding: FragmentNewsBinding
    private var mList = ArrayList<NewsData>()
    private lateinit var adapter: NewsAdapter
    private lateinit var bannerImage: String

    private val newsViewModel: NewsSentimentViewModel by viewModels {
        ViewModelFactoryNews.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(layoutInflater)
        activity?.title = getString(R.string.title_home_news)

        showLoading()
        requestNews()
        newsObserver() //to observe responses from api
        configRecyclerView()
        configSerachView()

        return binding.root
    }

    private fun configRecyclerView() {
        with(binding){
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun configSerachView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<NewsData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                showErrorDialog("No Data found",0,"")
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun newsObserver() {
        newsViewModel.apply {
            collectLatestLifeCycleFlow(uiStateError) { uiStateError ->
                if (uiStateError.isNotBlank() || uiStateError.isNotEmpty()) {
                    onErroronNews(uiStateError)
                }
            }
            collectLatestLifeCycleFlow(uiNewsState) {
                if (it.data != null) {
                    hideLoading()
                    onSuccessNews(it.data)
                }
            }
        }
    }

    private fun onErroronNews(uiStateError: String) {
        hideLoading()
        showErrorDialog(uiStateError,0,"")
    }

    private fun onSuccessNews(data: NewsSentimentResponse) {
        for (item in data.feed){
            hideLoading()
            println(item.title)
            bannerImage = if(item.bannerImage != null){
                item.bannerImage
            }else{
                ""
            }
            mList.add(NewsData(item.title, item.authors.toString(),item.summary, bannerImage))
        }
        adapter = NewsAdapter(mList)
        binding.recyclerView.adapter = adapter
    }

    private fun requestNews() {
        newsViewModel.requestNews()
    }

}