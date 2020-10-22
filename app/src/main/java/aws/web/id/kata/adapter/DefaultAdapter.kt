package aws.web.id.kata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aws.web.id.kata.R
import aws.web.id.kata.adapter.holder.DefaultHolder
import aws.web.id.kata.model.DefaultModel

class DefaultAdapter(
    private var data: List<DefaultModel>, val all:Boolean) : RecyclerView.Adapter<DefaultHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_default, parent, false)
        return DefaultHolder(view)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: DefaultHolder, position: Int) {
        holder.setData(data[position])
    }
}