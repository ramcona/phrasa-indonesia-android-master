package aws.web.id.kata.network.response

import aws.web.id.kata.model.KategloModel
import java.io.Serializable

class KategloResponse : Serializable {
    val kateglo:KategloModel = KategloModel()

}