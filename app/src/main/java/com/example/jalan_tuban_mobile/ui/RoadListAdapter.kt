package com.example.jalan_tuban_mobile.ui

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.jalan_tuban_mobile.R
import com.example.jalan_tuban_mobile.model.Road
import java.io.File
import java.security.PrivateKey

class RoadListAdapter(
    private val onItemClickListener: (Road) -> Unit,
) : ListAdapter<Road, RoadListAdapter.RoadViewHolder>(WORDS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoadViewHolder {
        return RoadViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RoadViewHolder, position: Int) {
        val road = getItem(position)
        holder.bind(road)
        holder.itemView.setOnClickListener {
            onItemClickListener(road)
        }
    }

    class RoadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val addressTextView: TextView = itemView.findViewById(R.id.addressTextView)
        private val latitudeTextView: TextView = itemView.findViewById(R.id.latitudeTextView)
        private val longitudeTextView: TextView = itemView.findViewById(R.id.longitudeTextView)
        private val photoImageView: ImageView = itemView.findViewById(R.id.photoImageView)


        fun bind(road: Road?) {
            nameTextView.text = road?.name
            addressTextView.text = road?.address

            // Menampilkan koordinat jika tersedia
            val latitude = road?.latitude
            val longitude = road?.longitude
            if (latitude != null && longitude != null) {
                latitudeTextView.visibility = View.VISIBLE
                longitudeTextView.visibility = View.VISIBLE

                latitudeTextView.text = "Latitude: $latitude"
                longitudeTextView.text = "longitude: $longitude"
            } else{
                latitudeTextView.visibility = View.GONE
                longitudeTextView.visibility = View.GONE
            }
            //set gambar ke photoImageView
            val photoPath = road?.photoPath
            if (photoPath != null) {
                val photoFile = File(photoPath)
                if (photoFile.exists()) {
                    val bitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
                    photoImageView.setImageBitmap(bitmap)
                } else {
                    photoImageView.setImageResource(R.drawable.ill_construction)
                }
            } else {
                photoImageView.setImageResource(R.drawable.ill_construction)
            }

        }


        companion object {
            fun create(parent: ViewGroup): RoadViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_road, parent, false)
                return RoadViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Road>() {
            override fun areItemsTheSame(oldItem: Road, newItem: Road): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Road, newItem: Road): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
