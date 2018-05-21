package example.com.firebasemobiletest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        db.collection("cart")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        log_textview.text = "Size: ${task.result.size()}\n\n"

                        for (document in task.result) {
                            Log.d("MainActivity", document.id + " => " + document.data)
                            log_textview.text = log_textview.text.toString() + "\n" + document.id + " => " + document.data
                        }
                    } else {
                        log_textview.text = "Error getting documents " + task.exception
                        Log.e("MainActivity", "Error getting documents.", task.exception)
                    }
                }

        // W/Firestore: (0.6.6-dev) [OnlineStateTracker]: Could not reach Firestore backend.
    }
}
