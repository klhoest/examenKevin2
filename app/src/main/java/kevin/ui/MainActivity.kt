package kevin.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.viiam.feednemo.R
import kevin.di.DaggerMainComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = DaggerMainComponent.create()
        val presenter = component.mainPresenter
    }


}
