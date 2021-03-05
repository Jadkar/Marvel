package com.globant.openbankassignment.ui.charactersdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.openbank.data.data.entity.Item
import com.globant.openbankassignment.databinding.RowItemCharacterDetailstypeItemBinding
import com.openbank.domain.uimodel.ItemUiModel

class CharacterDetailsTypeItemAdapter(
    private val mItemList: List<ItemUiModel>
) : RecyclerView.Adapter<CharacterDetailsTypeItemAdapter.CharactersDetailsTypeItemHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersDetailsTypeItemHolder {
        val binding = RowItemCharacterDetailstypeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CharactersDetailsTypeItemHolder(binding)
    }

    override fun getItemCount(): Int = mItemList.size

    override fun onBindViewHolder(holder: CharactersDetailsTypeItemHolder, position: Int) {
        holder.bind(mItemList[position])
    }

    inner class CharactersDetailsTypeItemHolder(private val itemRowBinding: RowItemCharacterDetailstypeItemBinding) :
        RecyclerView.ViewHolder(
            itemRowBinding.root
        ) {

        fun bind(item: ItemUiModel) {
            itemRowBinding.setVariable(BR.detailListItem,item)
        }
    }
}