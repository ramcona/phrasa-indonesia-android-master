package aws.web.id.kata.app

import android.app.Application
import androidx.constraintlayout.widget.ConstraintLayout
import aws.web.id.kata.helper.HelperToast
import aws.web.id.kata.helper.MyDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior

class App : Application() {

    companion object {
        var toast = HelperToast()
        lateinit var myDialog: MyDialog
        private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

        val curdate:String = java.text.SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            java.util.Locale("ID")
        ).format(
            java.util.Date()
        )
    }


    override fun onCreate() {
        super.onCreate()
    }
}