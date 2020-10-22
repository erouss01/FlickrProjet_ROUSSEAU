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
    var position = 0
    val repository = Repository()
    val mldPhoto = MutableLiveData<Photo>()

    val callback = object:Callback<SearchResult>{
        override fun onFailure(call: Call<SearchResult>, t: Throwable) {
            print("Erreur callback")
        }

        override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
            // val lPhoto = mlPhoto.get(0).photos?.photo
            var reponse: SearchResult? = response.body()
            if (response.isSuccessful) {
                mldPhoto.value=  response.body()?.photos?.photo?.get(0)
            }
        }
    }

    init {
        repository.getPhotos(callback)
    }

    fun nextPhoto() {
        position =+1
    }

}
