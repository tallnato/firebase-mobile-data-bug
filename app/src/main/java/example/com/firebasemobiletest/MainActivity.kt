package example.com.firebasemobiletest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.log_textview)

        val firestore = FirebaseFirestore.getInstance()
        FirebaseFirestore.setLoggingEnabled(true)

        firestore.collection("cart")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        textView.text = "Size: ${task.result.size()}\n\n"
                        Log.d("MainActivity", "Size: ${task.result.size()}")

                        for (document in task.result) {
                            Log.d("MainActivity", document.id + " => " + document.data)
                            textView.text = textView.text.toString() + "\n" + document.id + " => " + document.data
                        }
                    } else {
                        textView.text = "Error getting documents " + task.exception
                        Log.e("MainActivity", "Error getting documents.", task.exception)
                    }
                }
                .addOnFailureListener {
                    textView.text = "Error getting documents " + it
                    Log.e("MainActivity", "Exception getting documents", it)
                }

        val realtime = FirebaseDatabase.getInstance()

        realtime.getReference()
                .child("stuff")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                        Log.e("MainActivity", "Error getting value.", p0?.toException())
                    }

                    override fun onDataChange(p0: DataSnapshot?) {
                        Log.d("MainActivity", "Datasnapshot " + p0.toString())
                    }
                })

    }
}
