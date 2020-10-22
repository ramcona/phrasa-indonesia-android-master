package aws.web.id.kata.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import aws.web.id.kata.R
import aws.web.id.kata.helper.BaseActivity
import aws.web.id.kata.ui.activity.main.MainActivity

class SplashScreenActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        statusPutih()

        Handler().postDelayed({

                startActivity(Intent(applicationContext, MainActivity::class.java))

            finish()
        }, 3000)

    }
}