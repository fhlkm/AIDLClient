package com.client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
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
            }else{
                Log.i(TAG, "mILoopPayService is null")
            }
            if(mILoopPayService == null){
                Log.e(TAG, "Service is cann't bind")
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(TAG, "Service is unbinded")

        }


    }
}
