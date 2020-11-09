package com.example.flickrprojet.ui.liste

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickrprojet.model.Photo
import com.example.flickrprojet.model.Photos
import com.example.flickrprojet.model.SearchResult
import com.example.flickrprojet.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {
    val rep: Repository = Repository()
    var i=0
    val Lphotos= MutableLiveData<List<Photo>>()

    val call= object: Callback<SearchResult> {
        override fun onFailure(call: Call<SearchResult>, t: Throwable) {
            print("erreur")
        }

        override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
            var Sr=response.body()
            if (Sr != null) {
                Lphotos.value=Sr.photos.photo
            }



        }

    }

    init {
        rep.getPhotos(call)

    }

}