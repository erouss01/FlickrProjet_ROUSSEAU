package com.example.flickrprojet.ui.liste

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrprojet.R
import com.example.flickrprojet.model.Photo
import com.example.flickrprojet.ui.main.MainViewModel

class MyAdapter(val photos : MutableLiveData<List<Photo>>,val callback: (Int) -> Unit) : RecyclerView.Adapter<MyAdapter.MyViewHolder>()  {
    private lateinit var viewModel: MainViewModel
    class MyViewHolder(val v: LinearLayout) : RecyclerView.ViewHolder(v)// appelé quand le ViewHolder doit être créé (probablement parce que l'item devient visible)// on crée (inflate) le layout "user" et on le place dans le ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.photo,parent,false)
        val holder = MyViewHolder(layout as LinearLayout)
        layout.setOnClickListener{

            callback(holder.adapterPosition)
        }
        return holder
    }// appelé quand le recycerview a besoin de connaître la taille de la liste qu'il doit afficher
    override fun getItemCount(): Int = photos.value!!.size// appelé quand on doit peupler le ViewHolder avec le contenu de l'élément numéro "position"
    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val image = holder.v.findViewById<ImageView>(R.id.image)
        var photo = photos.value!!.get(position)
        val url =
            "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg"
        Glide.with(image.context).load(url).into(image)




    }
}