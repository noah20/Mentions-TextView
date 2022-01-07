package com.sol.mentionsandtags

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.sol.mentionsandtags.databinding.ActivityMainBinding
import com.sol.textviewutils.OnViewClick
import kotlin.system.measureTimeMillis

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), OnViewClick, View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
       val runTime = measureTimeMillis {
           binding = ActivityMainBinding.inflate(layoutInflater)
           setContentView(binding.root)
           val adapter = TextAdapter(SampleText.getListOfText(),this,this)
           binding.rvMain.adapter = adapter
       }

        Log.d(TAG, "onCreate: noah time : $runTime")

    }

    override fun onHashClick(tag: String) {
        Toast.makeText(this,"Hash click : $tag", Toast.LENGTH_LONG).show()
    }

    override fun onMentionClick(mention: String) {
        Toast.makeText(this,"mention click : $mention", Toast.LENGTH_LONG).show()

    }

    override fun onClick(v: View?) {
        Toast.makeText(this,"main text view click", Toast.LENGTH_LONG).show()
    }

}