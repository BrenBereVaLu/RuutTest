package com.example.ruuttest.presentation.views.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ruuttest.R
import com.example.ruuttest.databinding.FragmentPieChartBinding
import com.example.ruuttest.domain.responses.incomeStatement.IncomeStatementResponse
import com.example.ruuttest.presentation.bases.BaseFragment
import com.example.ruuttest.presentation.viewModels.IncomeStatementViewModel
import com.example.ruuttest.utils.ViewModelFactoryAlpha
import com.example.ruuttest.utils.collectLatestLifeCycleFlow
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class PieChartFragment : BaseFragment() {

    private lateinit var binding: FragmentPieChartBinding

    private val incomeViewModel: IncomeStatementViewModel by viewModels {
        ViewModelFactoryAlpha.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPieChartBinding.inflate(layoutInflater)
        activity?.title = getString(R.string.title_income)

        showLoading()
        requestIncome()
        incomeObserver() //to observe responses from api

        return binding.root
    }

    private fun incomeObserver() {
        incomeViewModel.apply {
            collectLatestLifeCycleFlow(uiStateError) { uiStateError ->
                if (uiStateError.isNotBlank() || uiStateError.isNotEmpty()) {
                    onErroronIncome(uiStateError)
                }
            }
            collectLatestLifeCycleFlow(uiIncomeState) {
                if (it.data != null) {
                    hideLoading()
                    onSuccessIncome(it.data)
                }
            }
        }
    }

    private fun onErroronIncome(uiStateError: String) {
        hideLoading()
        showErrorDialog(uiStateError,0,"")
    }

    private fun onSuccessIncome(data: IncomeStatementResponse) {
        with(binding){
            val float : Float = data.annualReports[0].totalRevenue.toFloat()
            val float2 : Float = data.annualReports[0].operatingIncome.toFloat()
            val float3 : Float = data.annualReports[0].incomeBeforeTax.toFloat()
            val float4 : Float = data.annualReports[0].costOfRevenue.toFloat()

            val list: ArrayList<PieEntry> = ArrayList()

            /**
             * Use objects for constants in your code.
             */
            list.add(PieEntry(float, "totalRevenue"))
            list.add(PieEntry(float2,"operateIncome"))
            list.add(PieEntry(float3,"beforeTax"))
            list.add(PieEntry(float4,"costRevenue"))

            /**
             * Use objects for constants in your code.
             */
            val pieDataSet = PieDataSet(list,"USD")
            pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
            pieDataSet.valueTextSize=15f
            pieDataSet.valueTextColor= Color.BLACK

            val pieData = PieData(pieDataSet)
            pieChart.data = pieData
            pieChart.description.text = data.symbol+" "+ data.annualReports[0].reportedCurrency
            /**
             * Use objects for constants in your code.
             */
            pieChart.centerText = "AnnualReports "+data.annualReports[0].fiscalDateEnding
            pieChart.animateY(2000)
        }
    }

    private fun requestIncome() {
        incomeViewModel.requestIncome()
    }

}