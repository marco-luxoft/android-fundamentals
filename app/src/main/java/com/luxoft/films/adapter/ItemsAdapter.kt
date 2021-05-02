package com.luxoft.films.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luxoft.films.R
import com.luxoft.films.dto.Gist

class ItemsAdapter(private val mListener: (Gist, Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var gists: List<Gist>? = null

    fun setItems(gists: List<Gist>) {
        this.gists = gists
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewUserName: TextView = view.findViewById(R.id.txt_username)
        val textViewLogin: TextView = view.findViewById(R.id.txt_login)
        val textViewDescription: TextView = view.findViewById(R.id.txt_description)
        val imageViewProfilePic: ImageView = view.findViewById(R.id.profile_pic)
        val imageViewPublic: ImageView = view.findViewById(R.id.img_public)
        val parentCardView: ConstraintLayout = view.findViewById(R.id.parent_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item1, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            gists?.let {
                val item = it[position] //Gist
                holder.textViewUserName.text =
                    item.user ?: holder.textViewUserName.context.getString(R.string.unavailable)
                holder.itemView.setOnClickListener {
                    mListener.invoke(item, position)
                }

                holder.textViewDescription.text =  if(!item.description.isNullOrBlank()) item.description else holder.textViewDescription.context.getString(R.string.unavailable)

                item.owner?.let { owner ->
                    val login = if(!owner.login.isNullOrBlank()) owner.login else holder.textViewLogin.context.getString(R.string.unavailable)
                    val loginFormat = holder.textViewLogin.context.getString(R.string.login)
                    holder.textViewLogin.text =  String.format(loginFormat, login)

                    Glide.with(holder.imageViewProfilePic.context).load(owner.avatar_url)
                        .into(holder.imageViewProfilePic)
                }
                holder.imageViewPublic.setImageResource(if (item.public) R.drawable.green_circle else R.drawable.red_circle)
            }

        }
    }

    override fun getItemCount(): Int = gists?.size ?: 0

    fun changeBackgroundColor(position : Int) {
        gists?.let {
            val gist = it[position]
            gist.public = !gist.public
            notifyItemChanged(position)
        }
    }
}