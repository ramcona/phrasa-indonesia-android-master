package aws.web.id.kata.ui.activity.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import aws.web.id.kata.R
import aws.web.id.kata.adapter.SinonimAdapter
import aws.web.id.kata.adapter.TautanAdapter
import aws.web.id.kata.adapter.TurunanAdapter
import aws.web.id.kata.helper.BaseActivity
import aws.web.id.kata.helper.Helper.log
import aws.web.id.kata.model.KategloModel
import aws.web.id.kata.model.RelationModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity :  BaseActivity(), DetailViews{
    lateinit var presenter: DetailPresenter
    var phrasa  = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setToolbar(getString(R.string.data))
        presenter = DetailPresenter(this, this)

        phrasa = intent.getStringExtra("kata") ?: ""
        presenter.get(phrasa)

    }

    override fun onLoading() {
        detail_shimmer.visibility = View.VISIBLE
        detail_div_pesan.visibility = View.GONE
        detail_shimmer.startShimmerAnimation()
    }

    @SuppressLint("SetTextI18n")
    override fun onSuccess(data: KategloModel) {
        val listSinonim:ArrayList<RelationModel> = ArrayList()
        val listTurunan:ArrayList<RelationModel> = ArrayList()
        val listGabungan:ArrayList<RelationModel> = ArrayList()


        detail_tv_nomina.text = """${data.lex_class_name} (${data.lex_class})"""
        detail_tv_phrasa.text = data.phrase
        detail_tv_sumber.text = data.ref_source_name

        //tautan
        detail_rv_tautan.layoutManager = LinearLayoutManager(this)
        detail_rv_tautan.adapter = TautanAdapter(data.reference)

        //get definision string
        var sDefinisi = ""
        for (df in 0 until data.definition.size){
            val sample  = data.definition[df].sample ?: ""
            sDefinisi += "${data.definition[df].def_num}. ${data.definition[df].def_text}: cont (${sample.replace(
                "--",
                data.definition[df].phrase
            )}) \n"
        }

        detail_tv_definisi.text = sDefinisi

        //peribahasa
        var sPeribahasa = ""
        for (pb in 0 until data.proverbs.size){
            sPeribahasa += "${data.proverbs[pb].proverb}. \nmaksudnya : ${data.proverbs[pb].meaning} \n\n"
        }

        detail_tv_peribahasa.text = sPeribahasa

        //translete atau terjeman
        var sTerjemah = ""
         for (tj in 0 until data.translations.size){
             sTerjemah += "Sumber : ${data.translations[tj].ref_source} \n${data.translations[tj].translation}\n\n"
         }
        detail_tv_terjemah.text = sTerjemah

        //memfilter relasi
        for (i in 0 until data.all_relation.size){
            val data2 = data.all_relation[i]
            if (data2.rel_type_name.equals("Turunan", false)){
                listTurunan.add(data2)
            }else if (data2.rel_type_name.equals("Gabungan kata", false)){
                listGabungan.add(data2)
            }else if (data2.rel_type_name.equals("Sinonim", false)){
                listSinonim.add(data2)
            }
        }

        //set data to recy relation
        detail_rv_sinonim.layoutManager = GridLayoutManager(this, 3)
        detail_rv_sinonim.adapter = SinonimAdapter(listSinonim)

        detail_rv_turunan.layoutManager = GridLayoutManager(this, 3)
        detail_rv_turunan.adapter = TurunanAdapter(listTurunan)

        detail_rv_gabunganKata.layoutManager = GridLayoutManager(this, 2)
        detail_rv_gabunganKata.adapter = TurunanAdapter(listGabungan)

        detail_shimmer.visibility = View.GONE
        detail_div_pesan.visibility = View.GONE
        detail_shimmer.stopShimmerAnimation()
    }

    override fun onFailed(msg: String, code: Int) {
        detail_shimmer.visibility = View.GONE
        detail_div_pesan.visibility = View.VISIBLE
        detail_shimmer.stopShimmerAnimation()

        detail_tv_reload.setOnClickListener {
            presenter.get(phrasa)
        }

        detail_tv_pesan.text = msg
    }
}