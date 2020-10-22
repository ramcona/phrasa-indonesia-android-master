package aws.web.id.kata.adapter.holder

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import aws.web.id.kata.R
import aws.web.id.kata.model.DefaultModel
import aws.web.id.kata.model.ReferensiModel
import aws.web.id.kata.model.RelationModel
import aws.web.id.kata.ui.activity.detail.DetailActivity
import kotlinx.android.synthetic.main.item_single_text.view.*

class TurunanHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvNama = itemView.findViewById<TextView>(R.id.isingle_tv_data)
    val context = itemView.context

    @SuppressLint("SetTextI18n")
    fun setData(model: RelationModel){
        tvNama.text = """${model.related_phrase} (${model.lex_class})"""

        tvNama.rootView.setOnClickListener {
            val go = Intent(context, DetailActivity::class.java)
            go.putExtra("kata", model.related_phrase ?: "")
            context.startActivity(go)
        }
    }

}