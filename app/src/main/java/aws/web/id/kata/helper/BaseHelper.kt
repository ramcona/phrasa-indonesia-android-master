package aws.web.id.kata.helper

import android.content.Context
import android.content.Intent
import aws.web.id.kata.ui.activity.main.MainActivity
import aws.web.id.kata.network.ClientService
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import io.reactivex.disposables.Disposable

open class BaseHelper {

    val ApiServiceServer by lazy { ClientService().create(false) }
    var disposable: Disposable? = null
}

