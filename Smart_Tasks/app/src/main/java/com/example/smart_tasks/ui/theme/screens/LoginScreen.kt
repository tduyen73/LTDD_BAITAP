package com.example.smart_tasks.ui.theme.screens
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smart_tasks.R
import com.example.smart_tasks.ui.theme.BlueUTH
import com.google.android.gms.auth.api.signin.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as Activity
    val auth = FirebaseAuth.getInstance()

    // --- GOOGLE SIGN-IN CONFIG ---
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.result
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { signInTask ->
                    if (signInTask.isSuccessful) {
                        Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()

                        // 🔹 Lưu account vào SavedStateHandle để truyền sang ProfileScreen
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("account", account)

                        navController.navigate("profile") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        Toast.makeText(context, "Login failed!", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // --- UI ---
    Scaffold (
        containerColor = Color.White
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = null,
                modifier = Modifier.height(100.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text("SmartTasks", color = BlueUTH, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(32.dp))
            Text("Welcome to get started", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(40.dp))

            Button(
                onClick = { launcher.launch(googleSignInClient.signInIntent) },
                colors = ButtonDefaults.buttonColors(containerColor = BlueUTH),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("SIGN IN WITH GOOGLE")
            }

            Spacer(Modifier.height(16.dp))

            OutlinedButton(
                onClick = { navController.navigate("email_login") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("SIGN IN WITH EMAIL")
            }
        }
    }
}
