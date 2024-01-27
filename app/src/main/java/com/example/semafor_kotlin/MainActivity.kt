package com.example.semafor_kotlin

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import java.util.Timer
import java.util.TimerTask

class MainActivity : Activity() {
    private var imageSemafor: ImageView? = null
    private var imageArray: IntArray =
        intArrayOf(R.drawable.semafor_red, R.drawable.semafor_yellow, R.drawable.semafor_green)

    private var counter: Int = 0
    private var timer: Timer? = null
    private var isRun = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageSemafor = findViewById(R.id.semafor)
    }

    fun onClickStartStop(view: View) {
        view as ImageButton

        if (!isRun) {
            startTimer()

            view.setImageResource(R.drawable.button_stop)

            isRun = true
        } else {
            timer?.cancel()

            imageSemafor?.setImageResource(R.drawable.semafor_grey)
            view.setImageResource(R.drawable.button_start)

            isRun = false
        }
    }

    fun startTimer() {
        timer = Timer()

        counter = 0

        timer?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread{
                imageSemafor?.setImageResource(imageArray[counter])

                    counter++
                    if (counter == 3) counter = 0
                }
            }
        }, 0, 1000)
    }
}