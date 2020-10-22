package com.example.flickrprojet.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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
    private lateinit var imageview : ImageView
    private lateinit var btn1 :Button
    private lateinit var btn2 :Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view = inflater.inflate(R.layout.main_fragment, container, false)
        imageview = view.findViewById(R.id.imageView)
        btn1 = view.findViewById(R.id.button1)
        btn2 = view.findViewById(R.id.button2)

        /*btn1.setOnClickListener(View.OnClickListener {
            viewModel.nextPhoto()
        })
        btn2.setOnClickListener(View.OnClickListener {

        })*/
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel = MainViewModel()
        viewModel.mldPhoto.observe(requireActivity(), Observer<Photo> { photo: Photo ->
            val url = "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg"
            getActivity()?.let { Glide.with(it).load(url).into(imageview) }
        })
    }

}