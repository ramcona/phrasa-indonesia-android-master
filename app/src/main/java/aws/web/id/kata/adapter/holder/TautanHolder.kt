package aws.web.id.kata.adapter.holder

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import aws.web.id.kata.R
import aws.web.id.kata.model.DefaultModel
import aws.web.id.kata.model.ReferensiModel
import kotlinx.android.synthetic.main.item_single_text.view.*

class TautanHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvNama = itemView.findViewById<TextView>(R.id.itautan_tv_data)
    val context = itemView.context

    fun setData(model: ReferensiModel){
        tvNama.text = model.label

        tvNama.rootView.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(model.url)
            )

            context.startActivity(intent)
        }
    }

}