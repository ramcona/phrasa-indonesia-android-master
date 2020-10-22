package aws.web.id.kata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aws.web.id.kata.R
import aws.web.id.kata.adapter.holder.TautanHolder
import aws.web.id.kata.model.DefaultModel
import aws.web.id.kata.model.ReferensiModel

class TautanAdapter(
    private var data: List<ReferensiModel>) : RecyclerView.Adapter<TautanHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TautanHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tautan, parent, false)
        return TautanHolder(view)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: TautanHolder, position: Int) {
        holder.setData(data[position])
    }
}