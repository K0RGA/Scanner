package com.example.scanner.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.scanner.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartsFragment : Fragment() {

    private val viewModel: ChartsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val chart = view.findViewById<LineChart>(R.id.chart)
        val generateButton = view.findViewById<Button>(R.id.generateButton)

        viewModel.chartData.observe(viewLifecycleOwner, Observer { data ->
            drawChart(chart, data)
        })

        generateButton.setOnClickListener {
            viewModel.generateChartData()
        }
        return view
    }

    private fun drawChart(chart: LineChart, data: List<Float>) {
        val entries = mutableListOf<Entry>()
        for (i in data.indices) {
            entries.add(Entry(i.toFloat(), data[i]))
        }

        val dataSet = LineDataSet(entries, "Chart Data")
        dataSet.color = Color.BLUE
        dataSet.setDrawValues(false)

        val lineDataSets = mutableListOf<ILineDataSet>()
        lineDataSets.add(dataSet)

        val lineData = LineData(lineDataSets)

        chart.data = lineData
        chart.invalidate()

        chart.apply {
            setDrawGridBackground(false)
            description.isEnabled = false
            axisRight.isEnabled = false

            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.granularity = 1f

            axisLeft.setDrawGridLines(true)
            axisLeft.axisMinimum = 0f

            legend.isEnabled = false
        }

        chart.animateX(1000)
    }

    companion object {
        fun newInstance() = ChartsFragment()
    }

}