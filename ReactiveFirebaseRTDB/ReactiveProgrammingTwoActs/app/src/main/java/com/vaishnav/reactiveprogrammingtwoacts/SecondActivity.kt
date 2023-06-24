package com.vaishnav.reactiveprogrammingtwoacts

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        readFromFirebase().subscribe (
            {value -> Log.d(TAG, "Received Value: $value")},
            {error -> error.printStackTrace()}
                )

        writeToFirebase("Reactive Post Message").subscribe(
            {Toast.makeText(this@SecondActivity, "Done", Toast.LENGTH_SHORT)},
            { error -> error.printStackTrace() }
        )
    }


    private fun writeToFirebase(post_message: String) : Completable
    {
        return Completable.create{emitter ->
            val databaseReference = FirebaseDatabase.getInstance().reference.child("reactive_post_message")
            databaseReference.setValue(post_message).addOnSuccessListener {
                emitter.onComplete()
            }.addOnFailureListener{
                emitter.onError(it)
            }
        }.subscribeOn(Schedulers.io())
    }

    private fun readFromFirebase() : Observable<String> {
        return Observable.create<String?> { emitter ->
            val databaseReference = FirebaseDatabase.getInstance().reference.child("reactive_msg")
            val valueEventListener = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.getValue(String::class.java)
                    if(value != null)
                    {
                        emitter.onNext(value)
                    }else
                    {
                        emitter.onError(Throwable("Data not available"))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    emitter.onError(error.toException())
                }
            }

            databaseReference.addListenerForSingleValueEvent(valueEventListener)

            emitter.setCancellable {
                databaseReference.removeEventListener(valueEventListener)
            }

        }.subscribeOn(Schedulers.io())
    }
}