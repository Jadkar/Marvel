package com.globant.openbankassignment.ui.charactersdetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globant.openbankassignment.domain.uimodel.CharacterDetailsUiModel
import com.globant.openbankassignment.domain.uimodel.CharactersDeatilsType
import com.globant.openbankassignment.domain.uimodel.DetailCharacterConvertor
import com.globant.openbankassignment.domain.entity.Item
import com.globant.openbankassignment.databinding.RowItemCharacterDetailstypeBinding

class CharacterDetailTypeAdapter(
    private val mContext: Context
) : RecyclerView.Adapter<CharacterDetailTypeAdapter.CharactersDetailsTypeHolder>() {

    private var mCharacterDetailsUiModelList: List<CharacterDetailsUiModel> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersDetailsTypeHolder {
        val binding = RowItemCharacterDetailstypeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return CharactersDetailsTypeHolder(binding)
    }

    fun setDetailsList(characterDetailsList: List<CharacterDetailsUiModel>) {
        this.mCharacterDetailsUiModelList = characterDetailsList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CharactersDetailsTypeHolder, position: Int) {
        holder.bind(mCharacterDetailsUiModelList[position])
    }

    override fun getItemCount(): Int = mCharacterDetailsUiModelList.size

    inner class CharactersDetailsTypeHolder(private val itemRowBinding: RowItemCharacterDetailstypeBinding) :
        RecyclerView.ViewHolder(
            itemRowBinding.root
        ) {

        fun bind(characterDetailsUiModel: CharacterDetailsUiModel) {

            itemRowBinding.setVariable(BR.characterDetails,characterDetailsUiModel)

            var listItem: List<Item> = emptyList()
            when (characterDetailsUiModel.title) {

                CharactersDeatilsType.COMICS.value -> {
                    // handleComicsView()
                    listItem =
                        DetailCharacterConvertor.convertComicsItem(characterDetailsUiModel.comics!!)
                }
                CharactersDeatilsType.SERIES.value -> {
                    listItem =
                        DetailCharacterConvertor.convertSeriesItem(characterDetailsUiModel.series!!)
                }
                CharactersDeatilsType.STORIES.value -> {
                    listItem =
                        DetailCharacterConvertor.convertStoriesItem(characterDetailsUiModel.stories!!)
                }
                CharactersDeatilsType.EVENTS.value -> {
                    listItem =
                        DetailCharacterConvertor.convertEventsItem(characterDetailsUiModel.events!!)
                }
                CharactersDeatilsType.CHARACTERSDETAILSSOURCE.value -> {
                    listItem =
                        DetailCharacterConvertor.convertUrlsItem(characterDetailsUiModel.urls!!)
                }

            }

            val itemListDataAdapter =
                CharacterDetailsTypeItemAdapter(
                    listItem
                )
            itemRowBinding.rvDetailsType.setHasFixedSize(true)
            itemRowBinding.rvDetailsType.layoutManager = LinearLayoutManager(
                mContext,
                LinearLayoutManager.HORIZONTAL, false
            )
            itemRowBinding.rvDetailsType.adapter = itemListDataAdapter
            itemRowBinding.rvDetailsType.isNestedScrollingEnabled = false
        }

    }

}