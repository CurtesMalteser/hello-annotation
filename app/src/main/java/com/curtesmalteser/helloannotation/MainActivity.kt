package com.curtesmalteser.helloannotation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.curtesmalteser.helloannotations.HelloAnnotation

@HelloAnnotation
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helloAnnotation = HelloMainActivity()

        helloAnnotation.logDebug("this is helloAnnotation hello world!")

    }
}