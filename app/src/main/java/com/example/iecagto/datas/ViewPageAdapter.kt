package com.example.iecagto.datas

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.iecagto.TemarioFragment

class ViewPageAdapter(fm : FragmentManager):FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 6
    }

    override fun getItem(position: Int): Fragment {
        return TemarioFragment.newInstance(position+1)
    }
}