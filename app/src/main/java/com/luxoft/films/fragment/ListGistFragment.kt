package com.luxoft.films.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luxoft.films.R
import com.luxoft.films.adapter.ItemsAdapter
import com.luxoft.films.dto.Gist
import com.luxoft.films.viewmodel.GistViewModel

class ListGistFragment : Fragment() {

    private val model: GistViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerview_items)
        val loadingView = root.findViewById<ProgressBar>(R.id.pg_loading)
        val myAdapter = ItemsAdapter { gist, i ->
            openDetailsFragment(gist, i)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(
                recyclerView.context,
                RecyclerView.VERTICAL,
                false
            )
            adapter = myAdapter
        }
        //model.getGists()?.value never do this
        model.getGists()?.observe(viewLifecycleOwner, Observer<List<Gist>> { gists ->
            loadingView.visibility = View.GONE
            myAdapter.setItems(gists)
            myAdapter.notifyDataSetChanged()
        })

        model.getListPositionLiveData().observe(viewLifecycleOwner, { position ->
            myAdapter.changeBackgroundColor(position)
            val item = model.getGists()?.value?.get(position)
            item?.public = item?.let {
                !it.public
            } ?: false
            print(item?.public)
        })


        model.getErrorLiveData().observe(viewLifecycleOwner, { error ->
            loadingView.visibility = View.GONE
            print(error)
        })
        return root
    }

    private fun openDetailsFragment(gist: Gist, i: Int) {
        val bundle = bundleOf(
            "key_owner" to gist.owner,
            "key_user" to gist.user,
            "key_description" to gist.description,
            "key_public" to gist.public,
            "key_createdAt" to gist.created_at,
            "key_updatedAt" to gist.updated_at,
            "key_list_position" to i
        )
        activity?.supportFragmentManager?.commit { //Commit is asynchronous
            setReorderingAllowed(true)
            addToBackStack(null)
            replace<DetailsFragment>(
                R.id.main_fragment_container_view,
                args = bundle,
                tag = "detail_fragment"
            )
        }
    }
}