package com.example.iecagto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class Sesion1Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sesion1, container, false)

        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        val adapter = MyPagerAdapter(childFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        return view
    }
}

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 3 // Tres secciones
    }

    override fun getItem(position: Int): Fragment {
        return PagerItemFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Sección 1"
            1 -> "Sección 2"
            2 -> "Sección 3"
            else -> null
        }
    }
}

class PagerItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_pager_item, container, false)

        // Aquí puedes configurar el ViewPager para esta sección (view.findViewById<ViewPager>(R.id.viewPagerInner))

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        // Aquí puedes actualizar el progreso del ProgressBar según el valor del ViewPager

        return view
    }
}