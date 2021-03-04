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
import com.globant.openbankassignment.domain.uimodel.CharacterListUiModel
import com.globant.openbankassignment.ui.base.BaseFragment
import com.globant.openbankassignment.utils.InternetUtil
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CharactersListFragment : BaseFragment(), OnCharactersItemClick {

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
        getCharactersList()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CharactersListViewModel::class.java)

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
        charactersAdapter = CharactersAdapter( this)

        rvCharactersList.layoutManager = LinearLayoutManager(context)
        rvCharactersList.adapter = charactersAdapter
        charactersAdapter.notifyDataSetChanged()

    }

    private fun getCharactersList() {
        if (InternetUtil.isInternetConnected()) {
            showLoadingIndicator(true)
            viewModel.getCharactersList(0)
        }else{
            showAlertMessage(getString(R.string.lbl_error_msg),getString(R.string.lbl_msg_no_internet_connection))
            InternetUtil.observe(viewLifecycleOwner, Observer { status ->
                if (status != null && status) {
                    showLoadingIndicator(true)
                    viewModel.getCharactersList(0)
                }
            })
        }
    }

    private fun subscribeToViewModel() {
        viewModel.charactersResponse.observe(viewLifecycleOwner, Observer {
            showLoadingIndicator(false)
            if (it != null) handleViewState(it)
            else showAlertMessage("",getString(R.string.lbl_no_data))
        })

        viewModel.getCharactersFailure.observe(viewLifecycleOwner, Observer<String> {
            showLoadingIndicator(false)
            if (!InternetUtil.isInternetConnected())
            {
                showAlertMessage(getString(R.string.lbl_internet_title),getString(R.string.lbl_msg_no_internet_connection))
            }else{
                showAlertMessage(getString(R.string.lbl_error_msg),it)
            }
        })
    }

    private fun handleViewState(characterMapperList: List<CharacterListUiModel>) {
        charactersAdapter.setCharactersData(characterMapperList)

    }

    override fun onCharacterSelected(result: CharacterListUiModel?) {
        var bundle = bundleOf(
            CharactersListActivity.ARGUM_CHARACTERID to result?.characterId,
            CharactersListActivity.ARGUM_CHARACTERNAME to result?.characterName
        )
        view?.findNavController()
            ?.navigate(R.id.action_CharacterFragment_to_CharacterDetailsFragment, bundle)
    }

    private fun showLoadingIndicator(loading: Boolean) = if (loading) {
        pbLoading.visibility = View.VISIBLE
    } else {
       pbLoading.visibility = View.GONE
    }
}