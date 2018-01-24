package com.client

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.looppay.ILoopPayService
import com.looppay.data.DeviceResult
import com.looppay.data.IBindDeviceCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var TAG: String? = "Client MainActivity";
    var mILoopPayService: ILoopPayService?=null
    var mIBindCallBack: IBindDeviceCallback? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        R.layout.activity_main


        bind.setOnClickListener{
            attemptToBindService()
        }

        ask_permission.setOnClickListener {
            askTakePciturePermission()
        }
    }

    private fun attemptToBindService() {
        val intent = Intent()
        intent.action = "com.looppay.aidl"
        intent.setPackage("com.looppay");
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE)

    }



    var mServiceConnection: ServiceConnection = object: ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            if(null ==  mILoopPayService ) {
                mILoopPayService = ILoopPayService.Stub.asInterface(service)
            }else{
                Log.i(TAG, "mILoopPayService is null")
            }
            Log.i(TAG, "mILoopPayService is not null")
            mILoopPayService!!.bindDevice("afdsafsafsadfsadf",null,object: IBindDeviceCallback.Stub() {
                override fun onSuccess(result: DeviceResult?) {
                    if(null != result)
                        Log.i(TAG,"IBindDeviceCallback: "+"deviceName: "+result.name+"  deviceBrand: "+result.brand)
                }
                override fun onFail(errorCode: Int, result: DeviceResult?) {
                    Log.i(TAG,"IBindDeviceCallback call back failed")

                }
            } )
            if(mILoopPayService == null){
                Log.e(TAG, "Service is cann't bind")
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(TAG, "Service is unbinded")

        }
    }

    fun askTakePciturePermission(){
        val permissionCheck = ContextCompat.checkSelfPermission(this@MainActivity,
                Manifest.permission.CAMERA);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Log.i(TAG,"Take picture right is granted")
        }else{
            ActivityCompat.requestPermissions(this,  arrayOf( Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE ), 0)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 0) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG,"Take picture right is granted")
            }
        }
    }



}
