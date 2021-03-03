package com.amarchaud.amTheme.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.amarchaud.amTheme.MainActivity
import com.amarchaud.amTheme.R
import com.amarchaud.amTheme.databinding.FragmentTeamBinding
import com.amarchaud.amTheme.ui.adapter.TeamAdapter
import com.amarchaud.amTheme.ui.interfaces.ITeamClickListener


class TeamFragment : Fragment(), ITeamClickListener {

    private var _binding: FragmentTeamBinding? = null
    private val binding get() = _binding!!

    private var teamAdapter = TeamAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (requireActivity() as MainActivity).changeToolbarTitle(
            requireContext().getString(
                R.string.team_title
            )
        )

        _binding = FragmentTeamBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = teamAdapter

            teamAdapter.team = mutableListOf(
                Triple(R.drawable.antoine, "Antoine", "Moi"),
                Triple(R.drawable.batman, "Batman", "Superhéros"),
                Triple(R.drawable.antoine, "Antoine", "Moi"),
                Triple(R.drawable.batman, "Batman", "Superhéros"),
                Triple(R.drawable.antoine, "Antoine", "Moi"),
                Triple(R.drawable.batman, "Batman", "Superhéros"),
                Triple(R.drawable.antoine, "Antoine", "Moi"),
                Triple(R.drawable.batman, "Batman", "Superhéros"),
                Triple(R.drawable.antoine, "Antoine", "Moi"),
                Triple(R.drawable.batman, "Batman", "Superhéros"),
                Triple(R.drawable.antoine, "Antoine", "Moi"),
                Triple(R.drawable.batman, "Batman", "Superhéros")
            )
            teamAdapter.notifyDataSetChanged()
        }
    }

    override fun onClick(element: Triple<Int, String, String>) {

        val dialog : DialogFragment= DialogCallFragment.newInstance(element)
        dialog.show(childFragmentManager, null)
        /*
        findNavController().navigate(
            TeamFragmentDirections.actionTeamFragmentToDialogCallFragment(
                element.first,
                element.second,
                element.third
            )
        )*/
    }

}