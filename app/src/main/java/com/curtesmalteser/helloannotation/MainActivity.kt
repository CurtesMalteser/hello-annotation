package com.curtesmalteser.helloannotation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.curtesmalteser.helloannotations.HelloAnnotation

@HelloAnnotation
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helloAnnotation = HelloMainActivity()

        helloAnnotation.logDebug("Hello world from ${helloAnnotation::class.java.simpleName}!")

    }
}