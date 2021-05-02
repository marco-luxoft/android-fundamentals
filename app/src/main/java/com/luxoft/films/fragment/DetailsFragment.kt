package com.luxoft.films.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.luxoft.films.R
import com.luxoft.films.dto.Owner
import com.luxoft.films.viewmodel.GistViewModel
import com.luxoft.films.viewmodel.SharedViewModel


class DetailsFragment : Fragment() {

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
        val user = requireArguments().getString("key_user")
        val description = requireArguments().getString("key_description")
        val public = requireArguments().getBoolean("key_public", false)
        val createdAt = requireArguments().getString("key_createdAt")
        val updatedAt = requireArguments().getString("key_updatedAt")

        val profileImageView = root.findViewById<ImageView>(R.id.img_profile_details)
        val usernameTxtView = root.findViewById<TextView>(R.id.txt_user_name_details)
        val createdAtTxtView = root.findViewById<TextView>(R.id.txt_created_At)
        val updatedAtTxtView = root.findViewById<TextView>(R.id.txt_updated_At)
        val loginTxtView = root.findViewById<TextView>(R.id.txt_login)
        val circleImageView = root.findViewById<ImageView>(R.id.img_circle)

        val ownerUrlTxtView = root.findViewById<TextView>(R.id.txt_owner_url)
        val descriptionTxtView = root.findViewById<TextView>(R.id.txt_full_description)


        context?.let { fragmentContext ->
            owner?.let {
                Glide.with(fragmentContext).load(it.avatar_url)
                    .into(profileImageView)
                ownerUrlTxtView.text = it.repos_url
            }
            createdAtTxtView.text = createdAt
            updatedAtTxtView.text = updatedAt
            usernameTxtView.text =
                if (!user.isNullOrBlank()) user else fragmentContext.getString(R.string.unavailable)

            val descFormat = fragmentContext.getString(R.string.description)
            val desc =
                if (!description.isNullOrBlank()) description else fragmentContext.getString(R.string.unavailable)

            descriptionTxtView.text = String.format(descFormat, desc)

            val login =
                if (!description.isNullOrBlank()) description else fragmentContext.getString(
                    R.string.unavailable
                )
            val loginFormat = fragmentContext.getString(R.string.login)
            loginTxtView.text = String.format(loginFormat, login)
            circleImageView.setImageResource(if (public) R.drawable.green_circle else R.drawable.red_circle)
        }
        return root
    }
}