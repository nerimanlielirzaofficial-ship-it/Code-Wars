package com.emir.codewars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF080B18)
private val Purple = Color(0xFF9B5CFF)
private val Cyan = Color(0xFF00E5FF)
private val Panel = Color(0xFF151B30)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Purple,
                    secondary = Cyan,
                    background = Background,
                    surface = Panel
                )
            ) {
                CodeWarsHome()
            }
        }
    }
}

@Composable
fun CodeWarsHome() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF17102F),
                        Background,
                        Color(0xFF071A28)
                    )
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            "CODE WARS",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 3.sp
        )

        Text(
            "KODLA. DÖYÜŞ. QALİB GƏL.",
            color = Cyan,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(Modifier.height(28.dp))

        Text(
            "PROQRAMÇI, XOŞ GƏLMİSƏN!",
            color = Color.White,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            "Öyrənməyə başla və öz kod gücünü göstər.",
            color = Color.LightGray,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            StatCard("⭐", "0", "XP", Modifier.weight(1f))
            StatCard("🏆", "0", "Kubok", Modifier.weight(1f))
            StatCard("⚔️", "0", "Qələbə", Modifier.weight(1f))
        }

        Spacer(Modifier.height(28.dp))

        Text(
            "PROQRAMLAŞDIRMA DİLLƏRİ",
            color = Cyan,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        val languages = listOf(
            "🐍  Python",
            "🟨  JavaScript",
            "🌙  Lua",
            "☕  Java",
            "⚙️  C++",
            "🔷  C#",
            "🌐  HTML / CSS",
            "💎  PHP"
        )

        languages.forEach { language ->
            LanguageCard(language)
            Spacer(Modifier.height(9.dp))
        }

        Spacer(Modifier.height(18.dp))

        ActionCard("⚔️", "DOSTLA DÖYÜŞ", "1-ə-1 kod yarışı")
        Spacer(Modifier.height(10.dp))
        ActionCard("📚", "AKADEMİYA", "Sıfırdan proqramlaşdırma öyrən")
        Spacer(Modifier.height(10.dp))
        ActionCard("🏆", "LİDERLƏR", "Ən güclü proqramçılar")

        Spacer(Modifier.height(28.dp))

        Text(
            "CODE WARS • CYBERPUNK EDITION",
            color = Color.Gray,
            fontSize = 10.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun StatCard(
    emoji: String,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(emoji, fontSize = 22.sp)
            Text(value, color = Color.White, fontWeight = FontWeight.Bold)
            Text(label, color = Cyan, fontSize = 11.sp)
        }
    }
}

@Composable
fun LanguageCard(name: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(13.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(name, color = Color.White, fontWeight = FontWeight.SemiBold)
            Text("›", color = Cyan, fontSize = 25.sp)
        }
    }
}

@Composable
fun ActionCard(emoji: String, title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, fontSize = 27.sp)
            Spacer(Modifier.width(14.dp))
            Column {
                Text(title, color = Color.White, fontWeight = FontWeight.Bold)
                Text(description, color = Color.LightGray, fontSize = 12.sp)
            }
        }
    }
}
