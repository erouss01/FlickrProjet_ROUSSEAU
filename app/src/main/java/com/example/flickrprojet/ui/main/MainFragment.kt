package com.example.flickrprojet.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.flickrprojet.R
import com.example.flickrprojet.model.Photo
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel.LPhoto.observe(viewLifecycleOwner, Observer<Photo> { photo: Photo ->
            val url = "https://farm" + photo.farm + "staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + "jpg"
            getActivity()?.let { Glide.with(it).load(url).into(imageView) };
        })

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}