package com.lekstel.aqi.base.presentation.view

import android.os.Bundle
import android.view.*
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), BaseView {

    @get:MenuRes
    open var menuResId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(layoutResId, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(menuResId != null)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menuResId?.also { inflater.inflate(it, menu) }
    }
}