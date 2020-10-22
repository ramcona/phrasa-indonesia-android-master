package aws.web.id.kata.ui.activity.main

interface MainViews {
    fun onLoading()
    fun onSuccess()
    fun onFailed(msg:String, code:Int)
}