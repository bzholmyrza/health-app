package kz.edu.astanait.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_progress_bar.*

class ProgressBarActivity : AppCompatActivity() {
    var restTimer: CountDownTimer? = null
    var restProgress: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)
        setSupportActionBar(findViewById(R.id.toolbar_exercise_activity))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<Toolbar>(R.id.toolbar_exercise_activity).setNavigationOnClickListener{
            onBackPressed()
        }
        setupRestView()
    }

    override fun onDestroy() {
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress = 0
        }
        super.onDestroy()
    }

    private fun setProgressBar(){
        progressBar.progress = restProgress
        restProgress = 0
        progressBar.max = 30
        restTimer = object : CountDownTimer(30000, 1000){
            override fun onTick(millisUntilFinished: Long) {

                restProgress++
                progressBar.progress = 30 - restProgress
                tvTimer.text = (30 - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ProgressBarActivity, "Let's start exercise", Toast.LENGTH_LONG).show()

            }
        }.start()
    }

    private fun setRestProgressBar(){
        progressBar.progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 10 - restProgress
                tvTimer.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ProgressBarActivity, "Let's new start exercise", Toast.LENGTH_LONG).show()
                setProgressBar()
            }
        }.start()
    }

    private fun setupRestView(){
        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }
}