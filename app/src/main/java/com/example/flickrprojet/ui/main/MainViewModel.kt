package com.example.flickrprojet.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickrprojet.model.Photo
import com.example.flickrprojet.model.Photos
import com.example.flickrprojet.model.SearchResult
import com.example.flickrprojet.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainViewModel : ViewModel() {
    val rep:Repository= Repository()
    var i=0
    val LPhoto = MutableLiveData<Photo>()
    val Lphotos=MutableLiveData<Photos>()

    val call= object:Callback<SearchResult>{
        override fun onFailure(call: Call<SearchResult>, t: Throwable) {
            print("erreur")
        }

        override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
            var Sr=response.body()
            if (Sr != null) {
                Lphotos.value=Sr.photos
            }
            if (Sr != null) {
                LPhoto.value=Sr.photos.photo.get(0)
            }


        }

    }

    init {
        rep.getPhotos(call)

    }
    fun nextPhoto(){
        i=i+1
        val f= Lphotos.value?.photo?.size
        if(i== f!!){
            i=0
        }
        LPhoto.value= Lphotos.value?.photo?.get(i)

    }

}
