package aws.web.id.kata.helper

import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(){

    lateinit var root: View
    lateinit var myDialog: MyDialog
    var toast = HelperToast()


}