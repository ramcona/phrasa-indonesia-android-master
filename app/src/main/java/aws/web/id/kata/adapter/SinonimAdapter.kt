package aws.web.id.kata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aws.web.id.kata.R
import aws.web.id.kata.adapter.holder.SinonimHolder
import aws.web.id.kata.model.DefaultModel
import aws.web.id.kata.model.ReferensiModel
import aws.web.id.kata.model.RelationModel

class SinonimAdapter(
    private var data: List<RelationModel>) : RecyclerView.Adapter<SinonimHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinonimHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_single_text, parent, false)
        return SinonimHolder(view)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: SinonimHolder, position: Int) {
        holder.setData(data[position])
    }
}