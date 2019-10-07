package com.example.amconsofttestproject.presentation.activities.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.amconsofttestproject.R
import com.example.amconsofttestproject.databinding.ActivityMainBinding
import com.example.amconsofttestproject.presentation.activities.second.SecondActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserInfo
import kotlinx.android.synthetic.main.activity_main.*

const val GOOGLESIGNIN = 379

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.handler = this

        mAuth = FirebaseAuth.getInstance()

        val gso: GoogleSignInOptions = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestProfile()
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        updateUI()
    }

    fun googleSignIn() {
        pbCircular.visibility = VISIBLE
        val signIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signIntent, GOOGLESIGNIN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLESIGNIN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn
                .getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                if (account != null) firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        Log.d("TAG", "firebaseAuthWithGoogle: " + account.id)

        val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    pbCircular.visibility = INVISIBLE

                    Log.d("TAG", "signIn success")
                    makeToast("Successfully signed in!")

                    updateUI()
                } else {
                    pbCircular.visibility = INVISIBLE
                    Log.w("TAG", "signIn failure", task.exception)

                    makeToast("Sign in failed!")
                    updateUI()
                }

            }
    }

    private fun updateUI() {
        if (mAuth.currentUser != null) {
            var name = mAuth.currentUser!!.displayName
            for (userInfo: UserInfo in mAuth.currentUser!!.providerData) {
                if (mAuth.currentUser!!.displayName == null && userInfo.displayName != null) {
                    name = userInfo.displayName
                }
            }
            tvFirebaseLogin.text = getString(R.string.welcome)
            tvUserName.text = name
            tvUserEmail.text = mAuth.currentUser!!.email
            Glide.with(this)
                .load(mAuth.currentUser!!.photoUrl.toString())
                .centerCrop()
                .into(ivAvatar)

            btnLogin.visibility = INVISIBLE
            btnLogout.visibility = VISIBLE
        } else {
            tvFirebaseLogin.text = getString(R.string.firebase_login)
            tvUserName.text = ""
            tvUserEmail.text = ""
            Glide.with(this)
                .load(R.drawable.ic_firebase_logo)
                .into(ivAvatar)
            btnLogin.visibility = VISIBLE
            btnLogout.visibility = INVISIBLE
        }
    }

    private fun makeToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }

    fun logOut() {
        FirebaseAuth.getInstance().signOut()
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                updateUI()
                makeToast("Successfully logged out!")
            }
    }

    fun openSecondActivity() {
        startActivity(SecondActivity.newInstance(this))
    }
}
