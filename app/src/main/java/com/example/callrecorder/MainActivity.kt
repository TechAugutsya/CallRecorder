package com.example.callrecorder
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast

import com.example.callrecord.CallRecord
import com.example.callrecord.helper.LogUtils

import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var callRecord: CallRecord

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //callRecord = CallRecord.init(this);

        callRecord = CallRecord.Builder(this)
            .setLogEnable(true)
            .setRecordFileName("CallRecorderTestFile")
            .setRecordDirName("CallRecorderTest")
            .setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION)
            .setShowSeed(true)
            .build()

        callRecord.changeReceiver(MyCallRecordReceiver(callRecord))

        callRecord.enableSaveFile()

/*
        val callRecord = CallRecord.Builder(this)
            .setRecordFileName("Record_" + SimpleDateFormat("ddMMyyyyHHmmss", Locale.US).format(Date()))
            .setRecordDirName("CallRecord")
            .setRecordDirPath(Environment.getExternalStorageDirectory().path)
            .setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            .setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
            .setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION)
            .setShowSeed(true)
            .build()
*/
        callRecord.startCallRecordService()




    }

    fun StartCallRecordClick(view: View) {
        LogUtils.i(TAG, "StartCallRecordClick")
        callRecord.startCallReceiver()
        Toast.makeText(this@MainActivity, "Call Recording Start's", Toast.LENGTH_SHORT).show()


        callRecord.enableSaveFile();
        callRecord.changeRecordDirName("NewDirName");
    }

    fun StopCallRecordClick(view: View) {
        LogUtils.i(TAG, "StopCallRecordClick")
        callRecord.stopCallReceiver()
        Toast.makeText(this@MainActivity, "Call Recording Stop's", Toast.LENGTH_SHORT).show()


        callRecord.disableSaveFile();
        callRecord.changeRecordFileName("NewFileName");
    }
}