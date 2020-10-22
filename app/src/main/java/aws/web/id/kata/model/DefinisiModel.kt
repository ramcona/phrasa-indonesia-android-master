package aws.web.id.kata.model

import java.io.Serializable

class DefinisiModel:Serializable {
    var def_uid:String = ""
    var phrase:String = ""
    var def_num:String = ""
    var def_text:String = ""
    var lex_class:String = ""
    var discipline:String = ""
    var sample:String? = ""
    var see:String = ""
    var updated:String = ""
    var updater:String = ""
    var wikipedia_updated:String = ""
    var lex_class_ref:String = ""
}