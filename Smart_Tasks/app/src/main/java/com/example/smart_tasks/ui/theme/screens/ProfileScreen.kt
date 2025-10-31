package com.example.smart_tasks.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(account: GoogleSignInAccount?, navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = { TopAppBar(title = { Text("Thông tin người dùng") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (account != null) {
                Image(
                    painter = rememberAsyncImagePainter(account.photoUrl),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = account.displayName ?: "Không rõ tên",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Email: ${account.email ?: "Không có"}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Ngày sinh: 08/02/2005")

                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    FirebaseAuth.getInstance().signOut()
                    val gsc = GoogleSignIn.getClient(
                        context,
                        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                    )
                    gsc.signOut()
                    navController.navigate("login") {
                        popUpTo("profile") { inclusive = true }
                    }
                }) {
                    Text("Đăng xuất")
                }
            } else {
                Text("Không có thông tin người dùng")
            }
        }
    }
}
