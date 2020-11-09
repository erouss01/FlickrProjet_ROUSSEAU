package com.example.flickrprojet.ui.liste

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrprojet.model.Photo
import com.example.flickrprojet.R
import com.example.flickrprojet.ui.main.MainViewModel

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel
    private lateinit var recycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view=inflater.inflate(R.layout.list_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ListViewModel()
        viewModel.Lphotos.observe(requireActivity(), Observer<List<Photo>> {
            recycler = view!!.findViewById<RecyclerView>(R.id.recyclerview)
            recycler.layoutManager = GridLayoutManager(requireContext(),2)
            val adapter=MyAdapter(viewModel.Lphotos, { position ->
                var photo = viewModel.Lphotos.value!!.get(position)
                val url =
                    "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg"
                val action = ListFragmentDirections.actionListFragmentToFullFragment(url)
                findNavController().navigate(action)
            })
            recycler.adapter=adapter


        })

    }


}