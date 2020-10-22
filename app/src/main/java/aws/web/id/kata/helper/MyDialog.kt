package aws.web.id.kata.helper

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import aws.web.id.kata.R

class MyDialog(val myContext: Context) {
    public lateinit var oke:Button
    public lateinit var alasan:EditText
    public lateinit var setujui:Button
    public lateinit var cancel:Button
    public lateinit var judul:TextView
    public lateinit var pesan:TextView
    private var mDialog: Dialog? = null

    /*CALL FIRST BEFORE CALL OTHER*/
    fun setup(){
        mDialog = Dialog(myContext)
        mDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog!!.setContentView(R.layout.custom_dialog)
        oke = mDialog!!.findViewById(R.id.dialog_btn_positive) as Button
        cancel = mDialog!!.findViewById(R.id.dialog_btn_negative) as Button
        judul = mDialog!!.findViewById(R.id.dialog_tv_judul) as TextView
        pesan = mDialog!!.findViewById(R.id.dialog_tv_pesan) as TextView
    }

    fun tutup(){
        if (mDialog != null){
            mDialog!!.dismiss()
        }
    }

    fun tampil(){
        try{
            if (mDialog != null){
                mDialog!!.show()
            }
        }catch (e:WindowManager.BadTokenException){

        }

    }

    fun bisabatal(value:Boolean){
        if (mDialog != null){
            mDialog!!.setCancelable(value)
        }
    }

}