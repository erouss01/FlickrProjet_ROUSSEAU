package com.example.flickrprojet.ui.full

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.flickrprojet.R

class FullFragment : Fragment() {


    companion object {
        fun newInstance() = FullFragment()
    }

    private lateinit var viewModel: FullViewModel
    private lateinit var imageview: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view=inflater.inflate(R.layout.full_fragment, container, false)
        imageview = view.findViewById<ImageView>(R.id.imageView2)
        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FullViewModel::class.java)
        val url = FullFragmentArgs.fromBundle(arguments!!).url
        Glide.with(requireActivity()).load(url).into(imageview)
    }
}