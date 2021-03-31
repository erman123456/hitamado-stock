package com.app.hitamado.ui.Delivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.hitamado.R
import com.app.hitamado.model.antarbarang.DataItem
import kotlinx.android.synthetic.main.item.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DeliveryAdapter:PagedListAdapter<DataItem,DeliveryAdapter.MainHolder>(callback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryAdapter.MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: DeliveryAdapter.MainHolder, position: Int) {
        holder.bind(getItem(position) ?: DataItem())
    }


    companion object {
        val callback = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

        }
    }

    class MainHolder(view: View):RecyclerView.ViewHolder(view){
            fun bind(dataItem: DataItem){
                itemView.tv_nama_pemesan_barang.text =dataItem.namaPemesan
                itemView.tv_nama_barang.text = String.format("${dataItem.namaBarang} (${dataItem.kodeBarang})")
                itemView.tv_barang_keluar.text = dataItem.keluar
                val formattedDate= dataItem.tanggal?.let { formateDate(it,"dd MMMM yyyy") }
                itemView.tv_tanggal.text = formattedDate
            }
        private fun formateDate(date:String,format:String):String{
            var formattedDate=""
            val sdf=SimpleDateFormat("dd-MM-yyyy",Locale.getDefault())
            sdf.timeZone= TimeZone.getTimeZone("UTC")
            try {
                val parseDate=sdf.parse(date)
                formattedDate=SimpleDateFormat(format).format(parseDate)
            }catch (e:ParseException){
                e.printStackTrace()
            }
            return formattedDate
        }
    }
}