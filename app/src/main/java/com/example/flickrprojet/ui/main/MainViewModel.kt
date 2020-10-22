package com.example.flickrprojet.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickrprojet.model.Photo
import com.example.flickrprojet.model.SearchResult
import com.example.flickrprojet.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val rep = Repository()
    var i=0
    val listep : MutableList<SearchResult> = mutableListOf<SearchResult>()
    val l : List<Photo> =listep.get(0).photos.photo
    val LPhoto = MutableLiveData<Photo>()
    val call = object: Callback<SearchResult> {
        override fun onFailure(call: Call<SearchResult>, t: Throwable) {
            print("erreur")
        }

        override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
            var Sr = response.body()
            if (Sr != null) {
                listep.add(Sr)
                LPhoto.value = l.get(i)
            }
        }
    }

    init {
        rep.getPhotos(call)
    }
    fun nextPhoto(){
        i=i+1
        LPhoto.value=l.get(i)
    }
}
