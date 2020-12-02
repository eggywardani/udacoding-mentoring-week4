package com.eggy.wisatamenarakudus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eggy.wisatamenarakudus.R
import com.eggy.wisatamenarakudus.model.DataItem
import kotlinx.android.synthetic.main.item_pengunjung.view.*

class VisitorAdapter (private val list:List<DataItem?>?, val itemClick:OnClickListener):RecyclerView.Adapter<VisitorAdapter.VisitorViewHolder>(){

    interface OnClickListener {
        fun detail(item: DataItem?)
        fun delete(item: DataItem?)
    }

    class VisitorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnHapus = itemView.btn_hapus
        fun bind(item: DataItem?) {
            with(itemView) {
                tv_nama.text = item?.nama
                tv_nohp.text = item?.nohp
                tv_asal_kota.text = item?.asalKota
                tv_alamat.text = item?.alamat
                tv_tanggal.text = item?.tanggalKunjungan
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitorViewHolder {
        return VisitorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pengunjung, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VisitorViewHolder, position: Int) {
        holder.bind(list?.get(position))

        holder.itemView.setOnClickListener {
            itemClick.detail(list?.get(position))
        }

        holder.btnHapus.setOnClickListener {
            itemClick.delete(list?.get(position))
        }

    }

    override fun getItemCount(): Int = list?.size ?: 0
}