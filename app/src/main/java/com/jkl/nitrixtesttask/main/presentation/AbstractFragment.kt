package com.jkl.nitrixtesttask.main.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.jkl.nitrixtesttask.R

abstract class AbstractFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bind(inflater, container)
        return binding.root
    }

    protected abstract fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    protected fun navigateTo(clazz: Class<out Fragment>, bundle: Bundle? = null) {
        val fragment = clazz.getDeclaredConstructor().newInstance().apply { arguments = bundle }
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.mainContainer, fragment)
            ?.addToBackStack(clazz.name)
            ?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}