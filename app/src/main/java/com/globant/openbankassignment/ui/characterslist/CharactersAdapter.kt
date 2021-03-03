package com.globant.openbankassignment.ui.characterslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.globant.openbankassignment.R
import com.globant.openbankassignment.data.model.Result
import com.globant.openbankassignment.databinding.RowItemCharactersListBinding

class CharactersAdapter(
    val mContext: Context,
    private val onCharactersItemClick: OnCharactersItemClick
) : RecyclerView.Adapter<CharactersAdapter.CharactersListHolder>() {

    private var characterList: List<Result> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListHolder {

        val binding = RowItemCharactersListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CharactersListHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersListHolder, position: Int) {
        holder.bind(characterList[position], onCharactersItemClick)
    }

    override fun getItemCount(): Int = characterList.size

    fun setCharactersData(characterList: List<Result>) {
        this.characterList = characterList
        notifyDataSetChanged()
    }

    inner class CharactersListHolder(private val itemRowBinding: RowItemCharactersListBinding) :
        RecyclerView.ViewHolder(
            itemRowBinding.root
        ) {

        fun bind(resultData: Result?, onCharactersItemClick: OnCharactersItemClick) {
            itemRowBinding.tvCharacterTitle.text = resultData?.name
            itemRowBinding.tvCharacterDescription.text = resultData?.description

            itemView.setOnClickListener {
                onCharactersItemClick.onCharacterSelected(resultData)
            }
            val thumbUrl = resultData?.thumbnail?.path + "." + resultData?.thumbnail?.extension
            val options = RequestOptions()

                .error(R.drawable.marvel)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .placeholder(R.drawable.marvel)
            Glide.with(mContext)
                .load(thumbUrl)
                .apply(options)
                .into(itemRowBinding.imgCharacters)
        }
    }

}