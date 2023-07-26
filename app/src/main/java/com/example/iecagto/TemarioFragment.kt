package com.example.iecagto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class TemarioFragment : Fragment() {

    companion object {

        private const val ARG_PAGE_NUMBER = "page_number"

        fun newInstance(pageNumber: Int): TemarioFragment{
            val fragment = TemarioFragment()
            val args = Bundle()
            args.putInt(ARG_PAGE_NUMBER, pageNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_temario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pageNumber = arguments?.getInt(ARG_PAGE_NUMBER)
        val imageView : ImageView = view.findViewById(R.id.imgViewTem)
        val imageResourceId = resources.getIdentifier("temario_$pageNumber", "drawable", requireContext().packageName)
        imageView.setImageResource(imageResourceId)
    }
}