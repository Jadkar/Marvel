package com.globant.openbankassignment.ui.charactersdetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globant.openbankassignment.domain.uimodel.CharacterDetailsMapper
import com.globant.openbankassignment.domain.uimodel.CharactersDeatilsType
import com.globant.openbankassignment.domain.uimodel.DeatilsItemtypeconvertor
import com.globant.openbankassignment.data.entity.Item
import com.globant.openbankassignment.databinding.RowItemCharacterDetailstypeBinding

class CharacterDetailTypeAdapter(
    private val mContext: Context
) : RecyclerView.Adapter<CharacterDetailTypeAdapter.CharactersDetailsTypeHolder>() {

    private var mCharacterDetailsMapperList: List<CharacterDetailsMapper> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersDetailsTypeHolder {
        val binding = RowItemCharacterDetailstypeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return CharactersDetailsTypeHolder(binding)
    }

    fun setDetailsList(characterDetailsList: List<CharacterDetailsMapper>) {
        this.mCharacterDetailsMapperList = characterDetailsList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CharactersDetailsTypeHolder, position: Int) {
        holder.bind(mCharacterDetailsMapperList[position])
    }

    override fun getItemCount(): Int = mCharacterDetailsMapperList.size

    inner class CharactersDetailsTypeHolder(private val itemRowBinding: RowItemCharacterDetailstypeBinding) :
        RecyclerView.ViewHolder(
            itemRowBinding.root
        ) {

        fun bind(characterDetailsMapper: CharacterDetailsMapper) {

            itemRowBinding.setVariable(BR.characterDetails,characterDetailsMapper)

            var listItem: List<Item> = emptyList()
            when (characterDetailsMapper.title) {

                CharactersDeatilsType.COMICS.value -> {
                    // handleComicsView()
                    listItem =
                        DeatilsItemtypeconvertor.convertComicsItem(characterDetailsMapper.comics!!)
                }
                CharactersDeatilsType.SERIES.value -> {
                    listItem =
                        DeatilsItemtypeconvertor.convertSeriesItem(characterDetailsMapper.series!!)
                }
                CharactersDeatilsType.STORIES.value -> {
                    listItem =
                        DeatilsItemtypeconvertor.convertStoriesItem(characterDetailsMapper.stories!!)
                }
                CharactersDeatilsType.EVENTS.value -> {
                    listItem =
                        DeatilsItemtypeconvertor.convertEventsItem(characterDetailsMapper.events!!)
                }
                CharactersDeatilsType.CHARACTERSDETAILSSOURCE.value -> {
                    listItem =
                        DeatilsItemtypeconvertor.convertUrlsItem(characterDetailsMapper.urls!!)
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