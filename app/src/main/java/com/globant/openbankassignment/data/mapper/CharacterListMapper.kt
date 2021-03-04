package com.globant.openbankassignment.data.mapper

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.globant.openbankassignment.R


class CharacterListMapper {
    var characterName: String? = null
    var characterDescription: String? = null
    var characterUrl: String? = null
    var characterId: Long? = null

    companion object {
        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, imageUrl: String?) {
            val options = RequestOptions()

                .error(R.drawable.marvel)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .placeholder(R.drawable.marvel)
            Glide.with(view.getContext())
                .load(imageUrl).apply(options)
                .into(view)
        }
    }

}