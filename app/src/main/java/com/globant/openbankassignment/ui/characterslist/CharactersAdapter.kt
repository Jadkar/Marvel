package com.globant.openbankassignment.ui.characterslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.openbankassignment.BR
import com.globant.openbankassignment.data.mapper.CharacterListMapper
import com.globant.openbankassignment.databinding.RowItemCharactersListBinding

class CharactersAdapter(
    private val onCharactersItemClick: OnCharactersItemClick
) : RecyclerView.Adapter<CharactersAdapter.CharactersListHolder>() {

    private var characterList: List<CharacterListMapper> = emptyList()

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

    fun setCharactersData(characterList: List<CharacterListMapper>) {
        this.characterList = characterList
        notifyDataSetChanged()
    }

    inner class CharactersListHolder(private val itemRowBinding: RowItemCharactersListBinding) :
        RecyclerView.ViewHolder(
            itemRowBinding.root
        ) {

        fun bind(resultData: CharacterListMapper?, onCharactersItemClick: OnCharactersItemClick) {

            itemRowBinding.setVariable(BR.resultData, resultData)
            itemRowBinding.characterUrl = resultData?.characterUrl

            itemView.setOnClickListener {
                onCharactersItemClick.onCharacterSelected(resultData)
            }
        }
    }

}