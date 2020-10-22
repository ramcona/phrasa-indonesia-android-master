package aws.web.id.kata.ui.defaults

interface DefaultViews {
    fun onLoading()
    fun onSuccess()
    fun onFailed(msg:String, code:Int)
}