package com.example.examencl3dam1.ServicioREST

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examencl3dam1.R
import com.example.examencl3dam1.VideoActivity


class CustomVideoAdapter (private val mList: List<VideoViewModel>) : RecyclerView.Adapter<CustomVideoAdapter.ViewHolder>() {


    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        val nombreVideo: TextView = ItemView.findViewById(R.id.lbl_nombre_video)
        val descripcionVideo: TextView = ItemView.findViewById(R.id.lbl_descripcion_video)
        val btnVer: Button = ItemView.findViewById(R.id.btnVer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        //Si deseo alguna conexion PODRIA SER AQUI (BD, SERVICIO WEB, ETC
        val itemViewModel = mList[position]

        holder.nombreVideo.text = itemViewModel.title
        holder.descripcionVideo.text = itemViewModel.description

        // Manejar clics en el botón "Ver"
        holder.btnVer.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, VideoActivity::class.java)
            intent.putExtra("VIDEO_URL", itemViewModel.url)
            context.startActivity(intent)
        }

    }

}