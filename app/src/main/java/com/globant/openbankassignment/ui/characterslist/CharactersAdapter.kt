package com.globant.openbankassignment.ui.characterslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.globant.openbankassignment.BR
import com.globant.openbankassignment.R
import com.globant.openbankassignment.databinding.RowItemCharactersListBinding
import com.openbank.domain.uimodel.CharacterListUiModel

class CharactersAdapter(
    private val onCharactersItemClick: OnCharactersItemClick
) : RecyclerView.Adapter<CharactersAdapter.CharactersListHolder>() {

    private var characterList: List<CharacterListUiModel> = emptyList()


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

    fun setCharactersData(characterList: List<CharacterListUiModel>) {
        this.characterList = characterList
        notifyDataSetChanged()
    }

    inner class CharactersListHolder(private val itemRowBinding: RowItemCharactersListBinding) :
        RecyclerView.ViewHolder(
            itemRowBinding.root
        ) {

        fun bind(resultData: CharacterListUiModel?, onCharactersItemClick: OnCharactersItemClick) {

            itemRowBinding.setVariable(BR.characterList, resultData)


            itemView.setOnClickListener {
                onCharactersItemClick.onCharacterSelected(resultData)
            }
        }
    }

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