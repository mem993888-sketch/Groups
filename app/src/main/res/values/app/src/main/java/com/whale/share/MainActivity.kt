package com.whale.share

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShareAppScreen()
                }
            }
        }
    }
}

@Composable
fun ShareAppScreen() {
    val context = LocalContext.current
    var postLink by remember { mutableStateOf("") }
    var groupsText by remember { mutableStateOf("") }
    val groupsList = remember(groupsText) {
        groupsText.split("\n")
            .map { it.trim() }
            .filter { it.isNotBlank() }
    }
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Share App 🚀",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = postLink,
            onValueChange = { postLink = it },
            label = { Text("رابط المنشور") },
            placeholder = { Text("https://facebook.com/...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = groupsText,
            onValueChange = { groupsText = it },
            label = { Text("روابط المجموعات (رابط في كل سطر)") },
            modifier = Modifier.fillMaxWidth().height(180.dp)
        )
        if (groupsList.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "المجموعات: ${groupsList.size} | الحالية: ${currentIndex + 1}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(12.dp),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                if (postLink.isBlank()) {
                    Toast.makeText(context, "أدخل رابط المنشور!", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (groupsList.isEmpty()) {
                    Toast.makeText(context, "أدخل روابط المجموعات!", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                val currentGroupUrl = groupsList[currentIndex]
                try {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    clipboard.setPrimaryClip(ClipData.newPlainText("Post Link", postLink))
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(currentGroupUrl)))
                    if (currentIndex < groupsList.size - 1) {
                        currentIndex++
                    } else {
                        currentIndex = 0
                        Toast.makeText(context, "تم الانتهاء من جميع المجموعات 🎉", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "فشل: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text(
                text = if (groupsList.isEmpty()) "ابدأ" else "افتح المجموعة التالية ➡️",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
