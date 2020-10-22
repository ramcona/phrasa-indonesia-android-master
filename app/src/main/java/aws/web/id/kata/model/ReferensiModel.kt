package aws.web.id.kata.model

import java.io.Serializable

class ReferensiModel :Serializable {
    var ext_uid:String = ""
    var phrase:String = ""
    var label:String = ""
    var url:String = ""
    var updated:String = ""
    var updater:String = ""
}