package aws.web.id.kata.ui.activity.detail

import aws.web.id.kata.model.KategloModel

interface DetailViews {
    fun onLoading()
    fun onSuccess(data:KategloModel)
    fun onFailed(msg:String, code:Int)
}