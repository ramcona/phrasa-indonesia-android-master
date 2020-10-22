package aws.web.id.kata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aws.web.id.kata.R
import aws.web.id.kata.adapter.holder.TurunanHolder
import aws.web.id.kata.model.DefaultModel
import aws.web.id.kata.model.ReferensiModel
import aws.web.id.kata.model.RelationModel

class TurunanAdapter(
    private var data: List<RelationModel>) : RecyclerView.Adapter<TurunanHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TurunanHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_single_text, parent, false)
        return TurunanHolder(view)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: TurunanHolder, position: Int) {
        holder.setData(data[position])
    }
}