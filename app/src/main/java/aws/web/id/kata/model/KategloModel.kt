package aws.web.id.kata.model

import java.io.Serializable

class KategloModel:Serializable {
    var phrase:String = ""
    var phrase_type:String = ""
    var lex_class:String = ""
    var roget_class:String = ""
    var pronounciation:String = ""
    var etymology:String = ""
    var ref_source:String = ""
    var def_count:String = ""
    var actual_phrase:String = ""
    var info:String = ""
    var notes:String = ""
    var updated:String = ""
    var lex_class_name:String = ""
    var ref_source_name:String = ""
    var definition:List<DefinisiModel> = ArrayList()
    var reference:List<ReferensiModel> = ArrayList()
    var proverbs:List<PeribahasaModel> = ArrayList()
    var translations:List<TransleteModel> = ArrayList()
    var all_relation:List<RelationModel> = ArrayList()
}