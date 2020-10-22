package aws.web.id.kata.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.provider.Settings
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import aws.web.id.kata.BuildConfig
import java.io.File

import java.util.ArrayList

class Validasi {


    fun isRelease(): Boolean {
        return BuildConfig.BUILD_TYPE.equals("release")
    }

    fun mintaijinkamera(context: Context): Boolean {
        val camera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

        val listPermissionsNeed = ArrayList<String>()
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeed.add(Manifest.permission.CAMERA)
        }
        if (!listPermissionsNeed.isEmpty()) {
            ActivityCompat.requestPermissions(context as Activity, listPermissionsNeed.toTypedArray<String>(), 443)
            return false
        }

        return true
    }

    fun mintaijindokumen(context: Context): Boolean {
        val r = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
        val w = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        val listPermissionsNeed = ArrayList<String>()
        if (r != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeed.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (w != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeed.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!listPermissionsNeed.isEmpty()) {
            ActivityCompat.requestPermissions(context as Activity, listPermissionsNeed.toTypedArray<String>(), 443)
            return false
        }

        return true
    }

    fun mintaijinlokasi(context: Context): Boolean {
        val r_lokasi =
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        val r_lokasi_caorse =
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)


        val listPermissionsNeed = ArrayList<String>()

        if (r_lokasi != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeed.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (r_lokasi_caorse != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeed.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if (!listPermissionsNeed.isEmpty()) {
            ActivityCompat.requestPermissions(context as Activity, listPermissionsNeed.toTypedArray<String>(), 443)
            return false
        }

        return true
    }

    fun mintaijin(context: Context): Boolean {
        val camera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        val r_penyimpanan =
            ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
        val w_penyimpanan =
            ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        val listPermissionsNeed = ArrayList<String>()
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeed.add(Manifest.permission.CAMERA)
        }
        if (r_penyimpanan != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeed.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (w_penyimpanan != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeed.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (!listPermissionsNeed.isEmpty()) {
            ActivityCompat.requestPermissions(
                context as Activity,
                listPermissionsNeed.toTypedArray(),
                443
            )
            return false
        }

        return true
    }

    fun isEmpty(edt:EditText):Boolean{
        return edt.text.toString().isEmpty()
    }



    fun validasiPhone(telp: String, code:String): String {

        var phonenum = telp
        phonenum = phonenum.replace(" ", "")
        phonenum = phonenum.replace("-", "")
        when {
            phonenum.startsWith("08") -> phonenum = "+${code}" + phonenum.substring(1)
            phonenum.startsWith("8") -> phonenum = "+${code}$phonenum"
        }

        if (!phonenum.contains("+")){
            phonenum = "+$code$phonenum"
        }
        return phonenum
    }


    /*FUNCTION FOR DETECT ROOTED AT CURRENT DEVICE*/
    fun isRooted(): Boolean? {
        return findBinary("su")
    }

    private fun findBinary(binaryName: String): Boolean? {
        var found = false
        run {
            val places = arrayOf(
                "/sbin/",
                "/system/bin/",
                "/system/xbin/",
                "/data/local/xbin/",
                "/data/local/bin/",
                "/system/sd/xbin/",
                "/system/bin/failsafe/",
                "/data/local"
            )

            for (where in places) {
                if (File(where + binaryName).exists()) {
                    found = true
                    break
                }
            }

        }
        return found
    }
    /*END DETECT ROOTED CURRENT DEVICE*/

    //===========================

    /*STARRING DETECT MOKE OR PHONE USER FAKE GPS*/
    fun getListOfFakeLocationAppsFromAll(context: Context): List<String> {
        val fakeApps = ArrayList<String>()
        val packages = context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        for (aPackage in packages) {
            val isSystemPackage = aPackage.flags and ApplicationInfo.FLAG_SYSTEM != 0
            if (!isSystemPackage && hasAppPermission(
                    context,
                    aPackage.packageName,
                    "android.permission.ACCESS_MOCK_LOCATION"
                )
            ) {
                fakeApps.add(getApplicationName(context, aPackage.packageName))
            }
        }
        return fakeApps
    }

    private fun hasAppPermission(context: Context, app: String, permission: String): Boolean {
        val packageManager = context.packageManager
        val packageInfo: PackageInfo
        try {
            packageInfo = packageManager.getPackageInfo(app, PackageManager.GET_PERMISSIONS)
            if (packageInfo.requestedPermissions != null) {
                for (requestedPermission in packageInfo.requestedPermissions) {
                    if (requestedPermission == permission) {
                        return true
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return false
    }

    public fun getVersion():String{
        return BuildConfig.VERSION_NAME
    }

    private fun getApplicationName(context: Context, packageName: String): String {
        var appName = packageName
        val packageManager = context.packageManager
        try {
            appName = packageManager.getApplicationLabel(
                packageManager.getApplicationInfo(
                    packageName,
                    PackageManager.GET_META_DATA
                )
            ).toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return appName
    }

    fun isMockLocationOn(location: Location, context: Context): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return location.isFromMockProvider
        } else {
            var mockLocation = "0"
            try {
                mockLocation = Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ALLOW_MOCK_LOCATION
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return mockLocation != "0"
        }
    }
    /*END DETECT MOKE OR PHONE USER FAKE GPS*/
}