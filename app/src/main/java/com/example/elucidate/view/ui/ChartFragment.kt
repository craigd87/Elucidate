package com.example.elucidate.view.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.elucidate.R
import com.example.elucidate.databinding.FragmentChartBinding
import com.example.elucidate.globalUser
import com.example.elucidate.viewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


/**
 * A simple [Fragment] subclass.
 * Use the [ChartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChartFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentChartBinding.inflate(layoutInflater)

        val lineChart= binding.lineChart

        setupLineChartData(lineChart,7, binding)

        binding.radioGroup.setOnCheckedChangeListener{group,checkedId->
            when(checkedId){
                R.id.rb7Days -> setupLineChartData(lineChart,7, binding)
                R.id.rb30Days ->setupLineChartData(lineChart,30, binding)

            }
        }

        return binding.root
    }

    private fun setupLineChartData(lineChart: LineChart, numberOfDays: Int, binding: FragmentChartBinding) {

        val id = globalUser.id
        val yVals = ArrayList<Entry>()
        var x = 0f
        val retrieved7DaysMoods = viewModel.retrieveDayRangeMoodsAsc(id, numberOfDays)

        retrieved7DaysMoods.observe(viewLifecycleOwner, Observer { it ->
            yVals.clear()
            val moodRatingsList = viewModel.accessRetrievedMoodRatingData(it)

            if(moodRatingsList.isEmpty()){
                Toast.makeText(activity, "No data yet", Toast.LENGTH_SHORT).show()
            }

            for (item in moodRatingsList) {
                yVals.add(Entry(x, item, "x"))
                x++

            }

            val size = yVals.size
            val set1: LineDataSet

            set1 = LineDataSet(yVals, "DataSet 1")
            set1.color = Color.BLUE
            set1.setCircleColor(Color.BLUE)
            set1.lineWidth = 1f
            set1.circleRadius = 3f
            set1.setDrawCircleHole(false)
            set1.valueTextSize = 0f
            set1.setDrawFilled(false)

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1)
            val data = LineData(dataSets)

            // set data
            lineChart.setData(data)
            lineChart.description.isEnabled = false
            lineChart.legend.isEnabled = false
            lineChart.setPinchZoom(true)
            lineChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
            lineChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
            lineChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
            lineChart.xAxis.labelCount = size
            lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            lineChart.xAxis.setDrawLabels(false)
            lineChart.invalidate()
            lineChart.refreshDrawableState()
            binding.tvDays.text="$numberOfDays Days"

        })
    }

}