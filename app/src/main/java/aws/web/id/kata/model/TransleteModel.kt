package aws.web.id.kata.model

import java.io.Serializable

class TransleteModel:Serializable {
    var lemma:String = ""
    var ref_source:String = ""
    var translation:String = ""
}