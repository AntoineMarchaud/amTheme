package com.amarchaud.amTheme.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amarchaud.amTheme.MainActivity
import com.amarchaud.amTheme.R
import com.amarchaud.amTheme.databinding.FragmentCheckBinding

class CheckFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences

    private var _binding: FragmentCheckBinding? = null
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
            ""
        )

        _binding = FragmentCheckBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            option1.text.text = "Option 1"
            option2.text.text = "Option 2"
            option3.text.text = "Option 3"
            option4.text.text = "Option 4"
            option5.text.text = "Option 5"

            sharedPref.getStringSet(
                "index",
                null
            )?.let {
                option1.checkbox.isChecked = it.contains(0.toString())
                option2.checkbox.isChecked = it.contains(1.toString())
                option3.checkbox.isChecked = it.contains(2.toString())
                option4.checkbox.isChecked = it.contains(3.toString())
                option5.checkbox.isChecked = it.contains(4.toString())
            }
        }
    }

    override fun onPause() {
        super.onPause()

        val index = mutableSetOf<String>()
        with(binding) {
            if (option1.checkbox.isChecked) {
                index.add(0.toString())
            }
            if (option2.checkbox.isChecked) {
                index.add(1.toString())
            }
            if (option3.checkbox.isChecked) {
                index.add(2.toString())
            }
            if (option4.checkbox.isChecked) {
                index.add(3.toString())
            }
            if (option5.checkbox.isChecked) {
                index.add(4.toString())
            }
        }

        // save
        with(sharedPref.edit()) {
            putStringSet(
                "index",
                index
            )
            apply()
        }
    }
}