package com.example.elucidate

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.elucidate.databinding.FragmentChartBinding
import com.example.elucidate.databinding.FragmentTitleBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
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
        var lineChart= binding.lineChart
        //var barChart= binding.barChart
        //var pieChart= binding.pieChart
        
        setupLineChartData(lineChart,30)

        //setupBarChartData(barChart)
        //setUpPieChartData(pieChart)

        return binding.root
    }

    private fun setupLineChartData(lineChart: LineChart, numberOfDays: Int) {
        val id = globalUser.id
        val yVals = ArrayList<Entry>()

        var x = 0f

        val retrieved7DaysMoods = viewModel.retrieveDayRangeMoodsAsc(id, numberOfDays)
        //val size=retrieved7DaysMoods.
        retrieved7DaysMoods.observe(viewLifecycleOwner, Observer { it ->
            yVals.clear()
            val moodRatingsList = viewModel.accessRetrievedMoodRatingData(it)
            //val size = moodRatingsList.size
            Log.d("Voldemort", "$moodRatingsList")
            for (item in moodRatingsList) {
                yVals.add(Entry(x, item, "x"))
                x++
                Log.d("Tonks", "$x- $yVals")
            }
            Log.d("Molly", "$yVals")
            //})
            val size = yVals.size
            Log.d("Lucius", "$yVals")


            /*yVals.add(Entry(0f, 30f, "0"))
        yVals.add(Entry(1f, 2f, "1"))
        yVals.add(Entry(2f, 4f, "2"))
        yVals.add(Entry(3f, 6f, "3"))
        yVals.add(Entry(4f, 8f, "4"))
        yVals.add(Entry(5f, 10f, "5"))
        yVals.add(Entry(6f, 22f, "6"))
        yVals.add(Entry(7f, 12.5f, "7"))
        yVals.add(Entry(8f, 22f, "8"))
        yVals.add(Entry(9f, 32f, "9"))
        yVals.add(Entry(10f, 54f, "10"))
        yVals.add(Entry(11f, 28f, "11"))*/

            //7 days
            /*yVals.add(Entry(0f, 0f, "0"))
        yVals.add(Entry(1f, 2f, "1"))
        yVals.add(Entry(2f, 4f, "2"))
        yVals.add(Entry(3f, 6f, "3"))
        yVals.add(Entry(4f, 8f, "4"))
        yVals.add(Entry(5f, 10f, "5"))
        yVals.add(Entry(6f, 7f, "6"))
        yVals.add(Entry(7f, 7f, "7"))*/

            val set1: LineDataSet
            set1 = LineDataSet(yVals, "DataSet 1")

            // set1.fillAlpha = 110
            // set1.setFillColor(Color.RED);

            // set the line to be drawn like this "- - - - - -"
            // set1.enableDashedLine(5f, 5f, 0f);
            // set1.enableDashedHighlightLine(10f, 5f, 0f);
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
            //lineChart.setDrawGridBackground()
            lineChart.xAxis.labelCount = size
            lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        })
    }

    /*
    private fun setUpPieChartData(pieChart: PieChart) {

        val yVals = ArrayList<PieEntry>()
        yVals.add(PieEntry(30f))
        yVals.add(PieEntry(2f))
        yVals.add(PieEntry(4f))
        yVals.add(PieEntry(22f))
        yVals.add(PieEntry(12.5f))

        val dataSet = PieDataSet(yVals, "")
        dataSet.valueTextSize=0f
        val colors = java.util.ArrayList<Int>()
        colors.add(Color.GRAY)
        colors.add(Color.BLUE)
        colors.add(Color.RED)
        colors.add(Color.GREEN)
        colors.add(Color.MAGENTA)

        dataSet.setColors(colors)
        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.centerTextRadiusPercent = 0f
        pieChart.isDrawHoleEnabled = false
        pieChart.legend.isEnabled = false
        pieChart.description.isEnabled = false
    }
*/


    /*
    private fun setupBarChartData(barChart : BarChart) {
        // create BarEntry for Bar Group
        val bargroup = ArrayList<BarEntry>()
        bargroup.add(BarEntry(0f, 30f, "0"))
        bargroup.add(BarEntry(1f, 2f, "1"))
        bargroup.add(BarEntry(2f, 4f, "2"))
        bargroup.add(BarEntry(3f, 6f, "3"))
        bargroup.add(BarEntry(4f, 8f, "4"))
        bargroup.add(BarEntry(5f, 10f, "5"))
        bargroup.add(BarEntry(6f, 22f, "6"))
        bargroup.add(BarEntry(7f, 12.5f, "7"))
        bargroup.add(BarEntry(8f, 22f, "8"))
        bargroup.add(BarEntry(9f, 32f, "9"))
        bargroup.add(BarEntry(10f, 54f, "10"))
        bargroup.add(BarEntry(11f, 28f, "11"))

        // creating dataset for Bar Group
        val barDataSet = BarDataSet(bargroup, "")

        //barDataSet.color = ContextCompat.getColor(R.color.black)
        barDataSet.color=R.color.black
        val data = BarData(barDataSet)
        barChart.setData(data)
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.labelCount = 11
        barChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
        barChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
        barChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
        barChart.description.isEnabled = false
        barChart.animateY(1000)
        barChart.legend.isEnabled = false
        barChart.setPinchZoom(true)
        barChart.data.setDrawValues(false)
    }
*/

}