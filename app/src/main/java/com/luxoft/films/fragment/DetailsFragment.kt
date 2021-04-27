package com.luxoft.films.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.luxoft.films.R
import com.luxoft.films.dto.Owner
import com.luxoft.films.viewmodel.GistViewModel
import com.luxoft.films.viewmodel.SharedViewModel


class DetailsFragment : Fragment() {

    private val model: GistViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_details, container, false)
        // Inflate the layout for this fragment
        val owner = requireArguments().getParcelable<Owner>("key_owner")
        val description = requireArguments().getString("key_description")
        val public = requireArguments().getBoolean("key_public", false)
        val createdAt = requireArguments().getString("key_createdAt")
        val updatedAt = requireArguments().getString("key_updatedAt")
        return root
    }
}