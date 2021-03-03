package com.globant.openbankassignment.ui.characterslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.openbankassignment.R
import com.globant.openbankassignment.data.entity.MarvelCharactersResponse
import com.globant.openbankassignment.data.entity.Result
import com.globant.openbankassignment.utils.ConstantKey
import com.globant.openbankassignment.utils.InternetUtil
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_charactres.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CharactersListFragment : Fragment(), OnCharactersItemClick {

    private lateinit var viewModel: CharactersListViewModel
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var charactersListActivity: CharactersListActivity

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        charactersListActivity = activity as CharactersListActivity
        if (context is CharactersListActivity) {
            charactersListActivity = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mBinding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.fragment_characters,
            container, false
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CharactersListViewModel::class.java)
        getCharactersList()

    }

    override fun onResume() {
        super.onResume()
        (activity as CharactersListActivity).supportActionBar?.title =
            getString(R.string.charactersList_fragment_label)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeToViewModel()
    }

    private fun initRecyclerView() {
        charactersAdapter = CharactersAdapter(requireContext(), this)

        rv_charactersList.layoutManager = LinearLayoutManager(context)
        rv_charactersList.adapter = charactersAdapter
        charactersAdapter.notifyDataSetChanged()

    }

    private fun getCharactersList() {
        if (InternetUtil.isInternetConnected()) {
            viewModel.getCharactersList(0)
        }
    }

    private fun subscribeToViewModel() {
        viewModel.charactersResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) handleViewState(it)
        })
    }

    private fun handleViewState(marvelCharactersResponse: MarvelCharactersResponse) {
        charactersAdapter.setCharactersData(marvelCharactersResponse.data?.results ?: emptyList())
    }

    override fun onCharacterSelected(result: Result?) {
        var bundle = bundleOf(
            ConstantKey.ARGUM_CHARACTERID to result?.id?.toInt(),
            ConstantKey.ARGUM_CHARACTERNAME to result?.name
        )
        view?.findNavController()
            ?.navigate(R.id.action_CharacterFragment_to_CharacterDetailsFragment, bundle)
    }
}