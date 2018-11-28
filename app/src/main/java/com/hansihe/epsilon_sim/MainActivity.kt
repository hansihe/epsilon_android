package com.hansihe.epsilon_sim

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val webViewClient: WebViewClient = object : WebViewClient() {
        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            Log.e("CALCHOST_ERROR", error!!.description.toString())
            super.onReceivedError(view, request, error)
        }
    }
    lateinit var webView: WebView

    lateinit var keypadTop: LinearLayout

    lateinit var vibratorService: Vibrator

    fun dispatchEpsilonEvent(event: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibratorService.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibratorService.vibrate(30)
        }
        webView.evaluateJavascript("dispatchEpsilonEvent($event)", null)
    }
    fun dispatchEpsilonKey(key: Int) {
        Log.d("KEYDISPATCH", "Key: $key")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibratorService.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibratorService.vibrate(30)
        }
        webView.evaluateJavascript("dispatchEpsilonKeyDown($key)", null)
        webView.evaluateJavascript("dispatchEpsilonKeyUp($key)", null)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        vibratorService = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        webView = findViewById(R.id.calc_host_webview)
        keypadTop = findViewById(R.id.keypad_top)
        keypadTop.orientation = LinearLayout.VERTICAL

        val cellSingleLP = LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
        val cellDoubleLP = LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 2.0f)


        val controlRow = LinearLayout(applicationContext)
        val controlRowLP = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f)

        val keyboardExpandBtn = View(applicationContext)
        keyboardExpandBtn.setBackgroundResource(R.drawable.keypad_button_border)
        controlRow.addView(keyboardExpandBtn, cellSingleLP)

        val homeBtn = KeypadButtonView(applicationContext, KeypadButtons.BtnDef(0, "home"))
        homeBtn.setBackgroundResource(R.drawable.keypad_button_border)
        controlRow.addView(homeBtn, cellSingleLP)
        homeBtn.setOnClickListener { dispatchEpsilonEvent(EpsilonEvents.Home) }

        val joypadBtn = JoypadView(applicationContext)
        joypadBtn.setBackgroundResource(R.drawable.keypad_button_border)
        controlRow.addView(joypadBtn, cellDoubleLP)
        joypadBtn.actionListener = fun(action: JoypadView.Event) {
            when (action) {
                JoypadView.Event.UP -> dispatchEpsilonEvent(EpsilonEvents.Up)
                JoypadView.Event.DOWN -> dispatchEpsilonEvent(EpsilonEvents.Down)
                JoypadView.Event.LEFT -> dispatchEpsilonEvent(EpsilonEvents.Left)
                JoypadView.Event.RIGHT -> dispatchEpsilonEvent(EpsilonEvents.Right)
                JoypadView.Event.PRESS -> dispatchEpsilonEvent(EpsilonEvents.OK)
            }
        }

        val okBtn = KeypadButtonView(applicationContext, KeypadButtons.BtnDef(0, "ok"))
        okBtn.setBackgroundResource(R.drawable.keypad_button_border)
        controlRow.addView(okBtn, cellSingleLP)
        okBtn.setOnClickListener { dispatchEpsilonEvent(EpsilonEvents.OK) }

        val backBtn = KeypadButtonView(applicationContext, KeypadButtons.BtnDef(0, "back"))
        backBtn.setBackgroundResource(R.drawable.keypad_button_border)
        controlRow.addView(backBtn, cellSingleLP)
        backBtn.setOnClickListener { dispatchEpsilonEvent(EpsilonEvents.Back) }

        keypadTop.addView(controlRow, controlRowLP)

        for (row in KeypadButtons.buttons) {
            val rowL = LinearLayout(applicationContext)
            rowL.orientation = LinearLayout.HORIZONTAL
            val rowLP = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f)
            keypadTop.addView(rowL, rowLP)

            for (cell in row) {
                val cellV = KeypadButtonView(applicationContext, cell)
                cellV.setBackgroundResource(R.drawable.keypad_button_border)
                rowL.addView(cellV, cellSingleLP)
                cellV.setOnClickListener { dispatchEpsilonKey(cell.keyNum) }
            }
            //rowL.weightSum = row.size.toFloat()
        }
        //keypadTop.weightSum = KeypadButtons.buttons.size.toFloat() + 1.0f

        webView.webViewClient = webViewClient
        webView.settings.javaScriptEnabled = true
        webView.settings.allowUniversalAccessFromFileURLs = true

        webView.loadUrl("file:android_asset/runner.html")

    }

    /*override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val char = event.unicodeChar

    }*/
}
