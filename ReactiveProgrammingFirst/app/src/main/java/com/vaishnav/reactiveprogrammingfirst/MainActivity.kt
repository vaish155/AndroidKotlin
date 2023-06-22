package com.vaishnav.reactiveprogrammingfirst

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : ComponentActivity() {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.button)
        val textView: TextView = findViewById(R.id.textView)

        val buttonClicks = Observable.create<Unit>{ emitter ->
            button.setOnClickListener{
                emitter.onNext(Unit)
            }
            emitter.setCancellable{
                button.setOnClickListener(null)
            }
        }

        val helloWorldObservable = buttonClicks
            .map{"Hello World, It works!"}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        disposables.add(
            helloWorldObservable.subscribe(
                {text -> textView.text = text},
                {error -> error.printStackTrace()}
            )
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
