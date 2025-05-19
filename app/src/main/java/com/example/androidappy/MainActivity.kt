package com.example.androidappy

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.material3.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidappy.ui.theme.AndroidAppyTheme
import kotlinx.coroutines.launch
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginBody()

        }
    }
}



@Composable
fun LoginBody () {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = context as Activity
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    val localEmail : String = sharedPreferences.getString("email", "").toString()
    val localPassword : String = sharedPreferences.getString("password", "").toString()
    email = localEmail
    password = localPassword

    var showDialog by remember {
        mutableStateOf(false)
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {padding ->
        Column(
            modifier = Modifier
                .fillMaxSize().padding(padding)
                .background(
                    color = Color.White
                )
                .padding(vertical = 30.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                placeholder = {
                    Text(text = "Enter email")
                },
                //            minLines = 4,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Gray.copy(alpha = 0.2f),
                    focusedIndicatorColor = Color.Green,
                    unfocusedContainerColor = Color.Gray.copy(alpha = 0.2f),
                    unfocusedIndicatorColor = Color.Blue
                ),
                shape = RoundedCornerShape(12.dp),
                prefix = {
                    Icon(Icons.Default.Email, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )





            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                placeholder = {
                    Text(text = "Enter password")
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Gray.copy(alpha = 0.2f),
                    focusedIndicatorColor = Color.Green,
                    unfocusedContainerColor = Color.Gray.copy(alpha = 0.2f),
                    unfocusedIndicatorColor = Color.Blue
                ),
                shape = RoundedCornerShape(12.dp),
                prefix = {
                    Icon(Icons.Default.Lock, contentDescription = null)
                },
                suffix = {
                    Icon(
                        painterResource(
                            if (visibility) R.drawable.baseline_visibility_24 else
                                R.drawable.baseline_visibility_off_24
                        ),
                        contentDescription = null,
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {

                            visibility = !visibility

                        })
                },

                visualTransformation = if (visibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )


            //checbox

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    Checkbox(
                        colors = CheckboxDefaults.colors
                            (
                            checkedColor = Color.Green,
                            checkmarkColor = Color.White
                        ),
                        checked = rememberMe,
                        onCheckedChange = {
                            rememberMe = it
                        }
                    )

                    Text(
                        text = "Remember me ",
                        modifier = Modifier.padding(vertical = 15.dp)
                    )

                }

                Row(
                    modifier = Modifier.padding(vertical = 15.dp)
                ) {
                    Text(text = "forgot password? ")

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {









                    if (email == "saugat@gmail.com" && password == "12345678") {
                        if(rememberMe) {
                            editor.putString("email", email)
                            editor.putString("password", password)
                            editor.apply()
                        }
                            val intent = Intent(context, DashboardActivity::class.java)
                        intent.putExtra("email", email)
                        intent.putExtra("password", password)
                        //Toast
                        Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()

                        context.startActivity(intent)


                    } else {
                        //                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()

                        //SnackBar
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Invalid login")
                        }

                    }
                },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Sign In")
            }



            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = "New User? Register Now ",
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.clickable {
                        val intent = Intent(context, RegistrationActivity:: class.java)
                        context.startActivity(intent)
                        //to destroy activity
//                        activity.finish()
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {


                Row {
                    Text("--------------------")
                }
                Row {

                    Text(
                        text = "Sign In With Other  Options: ",
                        color = Color.Black,
                        textDecoration = TextDecoration.Underline,
                        fontStyle = FontStyle.Normal
                    )
                }


                Row {
                    Text("--------------------")
                }


            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {


                Image(
                    painterResource(R.drawable.gacebook), contentDescription = null,
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp)

                )

                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painterResource(R.drawable.google), contentDescription = null,
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp)

                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row {
                // Trigger to show the dialog
                Button(onClick = { showDialog = true }) {
                    Text("Alert")
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        }, // dismiss when clicked outside
                        confirmButton = {
                            Button(onClick = {
                                // Confirm action
                                showDialog = false
                            }) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            Button(onClick = {
                                // Cancel action
                                showDialog = false
                            }) {
                                Text("Cancel")
                            }
                        },
                        title = { Text(text = "Alert Title") },
                        text = { Text("This is an alert dialog message.") }
                    )
                }
            }

        }



























    }


}

@Preview
@Composable
fun PreviewLogin () {
    LoginBody()

}




