package com.amarchaud.amTheme.ui.main.sub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amarchaud.amTheme.databinding.DialogCallBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(element: Triple<Int, String, String>): BottomSheetFragment {
            val fragment = BottomSheetFragment()

            val args = Bundle()

            args.putInt("image", element.first)
            args.putString("nom", element.second)
            args.putString("desc", element.third)

            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: DialogCallBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogCallBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            binding.image.setImageResource(requireArguments().getInt("image"))
            binding.name.text = requireArguments().getString("nom")
            binding.description.text = requireArguments().getString("desc")

            binding.call.setOnClickListener {
                dismiss()
            }
        }
    }
}