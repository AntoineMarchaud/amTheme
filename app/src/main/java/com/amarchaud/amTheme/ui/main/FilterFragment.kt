package com.amarchaud.amTheme.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.amarchaud.amTheme.MainActivity
import com.amarchaud.amTheme.R
import com.amarchaud.amTheme.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        sharedPref = requireContext().getSharedPreferences(
            getString(R.string.shared_pref),
            Context.MODE_PRIVATE
        )

        (requireActivity() as MainActivity).changeToolbarTitle(
            requireContext().getString(
                R.string.filter_title
            )
        )

        _binding = FragmentFilterBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            this.price.image.setImageResource(R.drawable.ic_price)
            this.price.text.text = getString(R.string.price)
            this.price.layout.setOnClickListener {
                findNavController().navigate(FilterFragmentDirections.actionFilterFragmentToCheckFragment())
            }
            this.price.numberCheckedLayout.visibility = View.INVISIBLE
            sharedPref.getStringSet(
                "index",
                null
            )?.let {
                this.price.numberCheckedLayout.visibility = if(it.size > 0) View.VISIBLE else View.INVISIBLE
                this.price.numberChecked.text = it.size.toString()
            }

            this.size.image.setImageResource(R.drawable.ic_size)
            this.size.text.text = getString(R.string.size)

            this.weight.image.setImageResource(R.drawable.ic_price)
            this.weight.text.text = getString(R.string.weight)
            this.weight.line.visibility = View.GONE

            leftButton.isPressed = true
            this.leftButton.setOnTouchListener { v, event ->
                leftButton.isPressed = true
                rightButton.isPressed = false
                true
            }
            this.rightButton.setOnTouchListener { v, event ->
                leftButton.isPressed = false
                rightButton.isPressed = true
                true
            }

            this.buildings.image.setImageResource(R.drawable.ic_buildings)
            this.buildings.text.text = getString(R.string.buildings)

            this.taxis.image.setImageResource(R.drawable.ic_taxis)
            this.taxis.text.text = getString(R.string.taxis)
        }
    }
}