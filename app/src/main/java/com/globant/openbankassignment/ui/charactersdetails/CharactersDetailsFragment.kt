package com.globant.openbankassignment.ui.charactersdetails

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.openbankassignment.R
import com.openbank.domain.uimodel.CharacterDetailsUiModel
import com.globant.openbankassignment.ui.base.BaseFragment
import com.globant.openbankassignment.ui.characterslist.CharactersListActivity
import com.globant.openbankassignment.utils.InternetUtil
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_characters_details.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CharactersDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var characterDetailTypeAdapter: CharacterDetailTypeAdapter
    private lateinit var viewModel: CharactersDetailsViewModel
    private  var characterId:Long=0
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private lateinit var mBinding:ViewDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         mBinding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.fragment_characters_details,
            container, false
        )
        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)
                .get(CharactersDetailsViewModel::class.java)
        characterId=arguments?.getLong(CharactersListActivity.ARGUM_CHARACTERID)!!
        (activity as CharactersListActivity).supportActionBar?.title =
            arguments?.getString(CharactersListActivity.ARGUM_CHARACTERNAME)
                ?: getString(R.string.charactersList_fragment_label)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getCharactersDetailsList()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeToViewModel()
        Log.d("Arguments", arguments?.getInt(CharactersListActivity.ARGUM_CHARACTERID).toString())
    }

    private fun initRecyclerView() {
        characterDetailTypeAdapter = CharacterDetailTypeAdapter(requireContext())


        rv_charactersDetailsList.layoutManager = LinearLayoutManager(context)
        rv_charactersDetailsList.adapter = characterDetailTypeAdapter
        characterDetailTypeAdapter.notifyDataSetChanged()
    }

    private fun getCharactersDetailsList() {
        if (InternetUtil.isInternetConnected()) {
            showLoadingIndicator(true)
            viewModel.getCharactersDetails(characterId)
        }else{
            showAlertMessage(getString(R.string.lbl_error_msg),getString(R.string.lbl_msg_no_internet_connection))

            InternetUtil.observe(viewLifecycleOwner, Observer { status ->
                if (status != null && status) {
                    showLoadingIndicator(true)
                    viewModel.getCharactersDetails(characterId)
                }
            })
        }
    }

    private fun subscribeToViewModel() {
        viewModel.characterDetails.observe(viewLifecycleOwner, Observer {
            showLoadingIndicator(false)
            if (it != null) handleViewState(it)
            else showAlertMessage("",getString(R.string.lbl_no_data))
        })
        viewModel.getCharacterDetailsFailure.observe(viewLifecycleOwner, Observer<String> {
            showLoadingIndicator(false)
            if (!InternetUtil.isInternetConnected())
            {
                showAlertMessage(getString(R.string.lbl_internet_title),getString(R.string.lbl_msg_no_internet_connection))
            }else{
                showAlertMessage(getString(R.string.lbl_error_msg),it)
            }
        })
    }

    private fun handleViewState(characterDetailsList: List<CharacterDetailsUiModel>) {
        characterDetailTypeAdapter.setDetailsList(characterDetailsList)
    }
    private fun showLoadingIndicator(loading: Boolean) = if (loading) {

        pbLoading.visibility = View.VISIBLE
    } else {
        pbLoading.visibility = View.GONE
    }


}