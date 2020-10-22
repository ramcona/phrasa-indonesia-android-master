package aws.web.id.kata.network.response

import java.io.Serializable

class DefaultResponse : Serializable {

    val error:Boolean  = false
    val code:Int = 0
    val message:String = ""

}