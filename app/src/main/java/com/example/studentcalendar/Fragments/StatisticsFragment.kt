package com.example.studentcalendar.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentcalendar.Adapters.RecyclerAdapter
import com.example.studentcalendar.ItemClass

import com.example.studentcalendar.R
import kotlinx.android.synthetic.main.fragment_statistics.*

/**
 * A simple [Fragment] subclass.
 */
class StatisticsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_statistics, container, false)

        val exampleList = generateDummyList(500)
        var recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = RecyclerAdapter(exampleList)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        return rootView
    }

    private fun generateDummyList(size: Int): List<ItemClass> {

        val list = ArrayList<ItemClass>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_sentiment_satisfied
                1 -> R.drawable.ic_sentiment_very_dissatisfied
                else -> R.drawable.ic_sentiment_satisfied
            }

            val item = ItemClass(drawable, "Item $i", "Line 2")
            list += item
        }

        return list
    }
}