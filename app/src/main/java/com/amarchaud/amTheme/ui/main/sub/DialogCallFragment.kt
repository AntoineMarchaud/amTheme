package com.amarchaud.amTheme.ui.main.sub

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.amarchaud.amTheme.R
import com.amarchaud.amTheme.databinding.DialogCallBinding


class DialogCallFragment : DialogFragment() {

    companion object {
        fun newInstance(element: Triple<Int, String, String>): DialogCallFragment {
            val fragment = DialogCallFragment()

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

    init {
        setStyle(STYLE_NORMAL, R.style.MaterialDialogSheet)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        dialog?.window?.setGravity(Gravity.BOTTOM)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
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

    override fun onStart() {
        super.onStart()

        // stretch dialog to full parent width
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}