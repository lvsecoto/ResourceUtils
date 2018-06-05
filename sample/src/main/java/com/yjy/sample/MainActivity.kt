package com.yjy.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mFragment : Fragment? = null
        get() = ShapeUtilsFragment.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragment = supportFragmentManager.findFragmentById(R.id.container)

        if (fragment == null) {
            fragment = mFragment
            supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment)
                .commit()
        }
    }
}
