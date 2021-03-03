package com.amarchaud.amTheme.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amarchaud.amTheme.MainActivity
import com.amarchaud.amTheme.R
import com.amarchaud.amTheme.databinding.FragmentMarketBinding
import com.amarchaud.amTheme.ui.adapter.MarketAdapter

class MarketFragment : Fragment() {

    private var _binding: FragmentMarketBinding? = null
    private val binding get() = _binding!!

    private var fruitsAdapter = MarketAdapter()
    private var legumesAdapter = MarketAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (requireActivity() as MainActivity).changeToolbarTitle(
            requireContext().getString(
                R.string.market_title
            )
        )

        _binding = FragmentMarketBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            layoutTop.title.text = getString(R.string.fruit)
            layoutTop.recyclerViewMarket.adapter = fruitsAdapter
            fruitsAdapter.market = mutableListOf(
                Triple(R.drawable.strawberry, "Fraises", "De superbes fraises"),
                Triple(R.drawable.abricot, "Abricots", "De superbes abricots"),
                Triple(R.drawable.strawberry, "Fraises", "De superbes fraises"),
                Triple(R.drawable.abricot, "Abricots", "De superbes abricots")
            )
            fruitsAdapter.notifyDataSetChanged()

            layoutBottom.title.text = getString(R.string.legume)
            layoutBottom.recyclerViewMarket.adapter = legumesAdapter

            legumesAdapter.market = mutableListOf(
                Triple(R.drawable.aubergines, "Aubergine", "De superbes aubergines"),
                Triple(R.drawable.batavia, "Batavia", "De superbes batavias"),
                Triple(R.drawable.aubergines, "Aubergine", "De superbes aubergines"),
                Triple(R.drawable.batavia, "Batavia", "Le légume préféré de Batman")
            )
            legumesAdapter.notifyDataSetChanged()
        }
    }
}