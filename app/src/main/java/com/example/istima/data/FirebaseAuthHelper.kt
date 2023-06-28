package com.example.istima.data

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.navigation.NavHostController
import com.example.istima.Constants
import com.example.istima.utils.Global
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

const val TAG: String = "ABC"
const val RC_SIGN_IN: Int = 10
class FirebaseAuthHelper(private val context: Context, private var navController: NavHostController):
    ComponentActivity() {
    private val constants: Constants = Constants()

    private var mAuth: FirebaseAuth
    private var googleSignInClient: GoogleSignInClient
    private var gso: GoogleSignInOptions

    init {
        mAuth = FirebaseAuth.getInstance()
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(constants.firebaseClientId)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(context as Activity, signInIntent, RC_SIGN_IN, null )
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                val credential = GoogleAuthProvider.getCredential(account.idToken , null)
                mAuth.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful){
//                val intent : Intent = Intent(context, HomeActivity::class.java)
//                intent.putExtra("email" , account.email)
//                intent.putExtra("name" , account.displayName)
//                startActivity(intent)

                    }else{
                        Toast.makeText(context, it.exception.toString() , Toast.LENGTH_SHORT).show()
                    }
                }
                Global.googleAccount = account
                Global.signedIn = true
            }
        }else{
            Toast.makeText(context, task.exception.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    fun googleSignOut() {
        mAuth.signOut()
        Global.googleAccount = null
    }
}