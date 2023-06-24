package com.vaishnav.reactiveprogrammingtwoacts

import android.content.Intent
import android.database.Observable
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vaishnav.reactiveprogrammingtwoacts.ui.theme.ReactiveProgrammingTwoActsTheme
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : ComponentActivity() {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonToNextActicity : Button = findViewById(R.id.buttonToNextActicity)

        val buttonClicks = io.reactivex.Observable.create<Unit> {emitter ->
            buttonToNextActicity.setOnClickListener{
                emitter.onNext(Unit)
            }
            emitter.setCancellable {
                buttonToNextActicity.setOnClickListener(null)
            }
        }

        disposables.add(
            buttonClicks.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    startActivity(Intent(this@MainActivity, SecondActivity::class.java))
                }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
