package com.example.ruuttest.presentation.views.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ruuttest.R
import com.example.ruuttest.databinding.FragmentBarChartBinding
import com.example.ruuttest.domain.responses.balanceSheet.BalanceSheetResponse
import com.example.ruuttest.presentation.bases.BaseFragment
import com.example.ruuttest.presentation.viewModels.BalanceSheetViewModel
import com.example.ruuttest.utils.ViewModelFactoryBalance
import com.example.ruuttest.utils.collectLatestLifeCycleFlow
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class BarChartFragment : BaseFragment() {

    private lateinit var binding: FragmentBarChartBinding

    private val balanceViewModel: BalanceSheetViewModel by viewModels {
        ViewModelFactoryBalance.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBarChartBinding.inflate(layoutInflater)
        activity?.title = getString(R.string.title_balance_sheet)

        showLoading()
        requestBalance()
        balanceObserver() //to observe responses from api

        return binding.root
    }

    private fun balanceObserver() {
        balanceViewModel.apply {
            collectLatestLifeCycleFlow(uiStateError) { uiStateError ->
                if (uiStateError.isNotBlank() || uiStateError.isNotEmpty()) {
                    onErroronBalance(uiStateError)
                }
            }
            collectLatestLifeCycleFlow(uiBalanceState) {
                if (it.data != null) {
                    hideLoading()
                    onSuccessBalance(it.data)
                }
            }
        }
    }

    private fun onErroronBalance(uiStateError: String) {
        hideLoading()
        showErrorDialog(uiStateError,0,"")
    }

    private fun onSuccessBalance(data: BalanceSheetResponse) {
        with(binding){
            val float = data.annualReports[0].currentNetReceivables.toFloat()
            val float2 = data.annualReports[0].totalNonCurrentAssets.toFloat()
            val float3 = data.annualReports[0].propertyPlantEquipment.toFloat()
            val float4 = data.annualReports[0].currentAccountsPayable.toFloat()

            val list: ArrayList<BarEntry> = ArrayList()

            list.add(BarEntry(float,float))
            list.add(BarEntry(float2,float2))
            list.add(BarEntry(float3,float3))
            list.add(BarEntry(float4,float4))

            val barDataSet = BarDataSet(list,"Annual Reports")
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
            barDataSet.valueTextColor= Color.BLACK

            val barData = BarData(barDataSet)

            barChart.setFitBars(true)
            barChart.data = barData
            barChart.description.text = data.symbol+" "+ data.annualReports[0].reportedCurrency
            barChart.animateY(2000)
        }
    }

    private fun requestBalance() {
        balanceViewModel.requestBalance()
    }
}