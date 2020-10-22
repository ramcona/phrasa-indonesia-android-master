package aws.web.id.kata.ui.defaults

import android.os.Bundle
import aws.web.id.kata.R
import aws.web.id.kata.helper.BaseActivity

class DefaultActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default)
        setToolbar(getString(R.string.default_activity))


    }
}