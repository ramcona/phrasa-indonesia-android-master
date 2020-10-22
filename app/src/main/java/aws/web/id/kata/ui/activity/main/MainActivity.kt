package aws.web.id.kata.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.SearchView
import aws.web.id.kata.R
import aws.web.id.kata.helper.BaseActivity
import aws.web.id.kata.ui.activity.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusPutih()

        main_sv_kata.isIconified = true
        main_sv_kata.onActionViewExpanded()
        main_sv_kata.queryHint = getString(R.string.masukan_phrasa)

        main_sv_kata.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val go = Intent(this@MainActivity, DetailActivity::class.java)
                go.putExtra("kata", query ?: "")
                startActivity(go)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        Handler(Looper.getMainLooper()).postDelayed( { main_sv_kata.clearFocus() }, 300)
    }
}