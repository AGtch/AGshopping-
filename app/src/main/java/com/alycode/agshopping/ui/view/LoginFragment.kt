package com.alycode.agshopping.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.alycode.agshopping.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var navController: NavController

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val RC_SIGN_IN = 123  // Can be any integer unique to the Activity
    private var showOneTapUI = true


    lateinit var loginFragmentBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        loginFragmentBinding = FragmentLoginBinding.inflate(inflater)

        return loginFragmentBinding.root
    }

    override fun onStart() {
        super.onStart()
        //   val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        //     updateUI(account!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        navController = findNavController()
//        FirebaseApp.initializeApp(requireContext())
//
//        val signInRequest = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestEmail()
//            .build()
//        googleSignInClient = GoogleSignIn.getClient(requireActivity(), signInRequest)


    }
}
//
//
//    private fun signInGoogle() {
//        val signInIntent: Intent = googleSignInClient.signInIntent
//
//        resultLauncher.launch(signInIntent )
//    }

//    private var resultLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult() ) { result ->
//            Log.d(
//                "registerForActivityResult",
//                "registerForActivityResult: ${result.data.toString()}\n  resultCode :- ${result.resultCode} , ${Activity.ACCOUNT_SERVICE}"
//            )
//            if (result.resultCode == Activity.ACCOUNT_SERVICE) {
//                // There are no request codes
//                val data: Intent? = result.data
//                val task: Task<GoogleSignInAccount> =
//                    GoogleSignIn.getSignedInAccountFromIntent(data)
//                handleResult(task)
//
//            }
//        }
//
//    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
//        try {
//            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
//            if (account != null) {
//                updateUI(account)
//            }
//        } catch (e: Exception) {
//            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    // this is where we update the UI after Google signin takes place
//    private fun updateUI(account: GoogleSignInAccount) {
//        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
//        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                navController.popBackStack()
//            }
//        }
//    }
//
//
//}