package com.example.mystudyapp

import android.annotation.SuppressLint
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import android.widget.TextView

class TimerActivity : AppCompatActivity() {

    private val remainMinutesTextView: TextView by lazy{
        findViewById(R.id.remainMinutesTextView)
    }

    private val remainSecondsTextView: TextView by lazy{
        findViewById(R.id.remainSecondsTextView)
    }

    private val seekBar: SeekBar by lazy {
        findViewById(R.id.seekBar)
    }

    private val soundPool = SoundPool.Builder().build()

    private var currentCountDownTimer: CountDownTimer? = null
    private var tickingSoundId: Int? = null
    private var bellSoundId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        bindViews()
        initSounds()
    }

    override fun onResume() {
        super.onResume()
        soundPool.autoResume()
    }

    override fun onPause() {
        super.onPause()
        soundPool.autoPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        // soundPool 사용되지 않는 경우 메모리에서 정리
        soundPool.release()
    }

    private fun bindViews(){
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if(fromUser){
                        updateRemainTime(progress * 60 * 1000L)
                    }
                }

                // 사용자가 SeekBar 조작을 시작할때
                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    stopCountDown()

                }

                // 사용자가 SeekBar 조작을 멈췄을떄
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    seekBar ?: return
                    if(seekBar.progress == 0){
                        stopCountDown()
                    }else{
                        startCountDown()
                    }
                }
            }
        )
    }

    private fun initSounds(){

        // 타이머 틱톡 사운드
        tickingSoundId = soundPool.load(this, R.raw.button_sound, 1)

        // 타이머 종료시 사운드
        bellSoundId = soundPool.load(this, R.raw.button_sound, 1)

    }

    private fun createCountDownTimer(initialMillis: Long) =
        object : CountDownTimer(initialMillis, 1000L){
            override fun onTick(millisUntilFinished: Long) {
                updateRemainTime(millisUntilFinished)
                updateSeekBar(millisUntilFinished)
            }

            override fun onFinish() {
                completeCountDown()
            }
        }

    private fun stopCountDown(){
        currentCountDownTimer?.cancel()
        currentCountDownTimer = null
        soundPool.autoPause()
    }

    private fun startCountDown(){
        currentCountDownTimer = createCountDownTimer(seekBar.progress * 60 * 1000L)
        currentCountDownTimer?.start()

        tickingSoundId?.let{ soundId ->
            soundPool.play(soundId, 1F, 1F, 0, -1, 1F)
        }
    }


    private fun completeCountDown(){
        updateRemainTime(0)
        updateSeekBar(0)

        soundPool.autoPause()
        bellSoundId?.let{ soundId ->
            soundPool.play(soundId, 1F, 1F, 0, 0, 1F)
        }
    }


    @SuppressLint("SetTextI18n")
    private fun updateRemainTime(remainMillis: Long){

        val remainSeconds = remainMillis / 1000

        remainMinutesTextView.text = "%02d'".format(remainSeconds / 60)
        remainSecondsTextView.text = "%02d".format(remainSeconds % 60)
    }

    private fun updateSeekBar(remainMillis: Long){
        seekBar.progress = (remainMillis / 1000 / 60).toInt()

    }
}