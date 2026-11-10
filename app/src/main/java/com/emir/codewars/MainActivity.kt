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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BG = Color(0xFF080B18)
private val PURPLE = Color(0xFF9B5CFF)
private val CYAN = Color(0xFF00E5FF)
private val PANEL = Color(0xFF151B30)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = PURPLE,
                    secondary = CYAN,
                    background = BG,
                    surface = PANEL
                )
            ) {
                CodeWarsApp()
            }
        }
    }
}

@Composable
fun CodeWarsApp() {
    val page = remember { mutableStateOf("home") }
    val xp = remember { mutableIntStateOf(0) }
    val completed = remember { mutableStateOf(false) }
    val answer = remember { mutableStateOf("") }
    val code = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF17102F), BG, Color(0xFF071A28))
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "CODE WARS",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Black
            )
            Text("⭐ ${xp.intValue} XP", color = CYAN, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(20.dp))

        if (page.value != "home") {
            OutlinedButton(onClick = {
                page.value = "home"
                message.value = ""
            }) {
                Text("← ƏSAS MENYU")
            }
            Spacer(Modifier.height(14.dp))
        }

        when (page.value) {
            "home" -> {
                Text(
                    "Xoş gəlmisən, proqramçı!",
                    color = Color.White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Öyrən, kod yaz və öz gücünü göstər.",
                    color = Color.LightGray
                )

                Spacer(Modifier.height(20.dp))

                InfoCard("⭐", "Təcrübə xalı", "${xp.intValue} XP")
                InfoCard("📚", "Tamamlanan dərs", if (completed.value) "1" else "0")

                Spacer(Modifier.height(20.dp))

                MenuButton("📚", "AKADEMİYA", "Proqramlaşdırmanı öyrən") {
                    page.value = "academy"
                }
                MenuButton("💻", "KOD LABORATORİYASI", "Kodunu yaz və yoxla") {
                    page.value = "editor"
                }
                MenuButton("⚔️", "DÖYÜŞ ARENASI", "Onlayn yarış sistemi") {
                    page.value = "battle"
                }
                MenuButton("🏆", "LİDERLƏR", "Liderlər cədvəli") {
                    page.value = "leaders"
                }
            }

            "academy" -> {
                Heading("AKADEMİYA", "İlk dərsini seç.")

                MenuButton("🐍", "PYTHON — 1-Cİ DƏRS", "print əmri nədir?") {
                    page.value = "lesson"
                }
                MenuButton("🟨", "JAVASCRIPT", "Hazırlanır") {
                    message.value = "JavaScript dərsləri növbəti mərhələdə əlavə ediləcək."
                }
                MenuButton("☕", "JAVA", "Hazırlanır") {
                    message.value = "Java dərsləri növbəti mərhələdə əlavə ediləcək."
                }
                if (message.value.isNotEmpty()) {
                    Text(message.value, color = CYAN)
                }
            }

            "lesson" -> {
                Heading("PYTHON — 1-Cİ DƏRS", "Ekrana yazı çıxarmaq")

                InfoCard("📖", "print()", "Python-da ekrana məlumat çıxarmaq üçün istifadə olunur.")

                Spacer(Modifier.height(15.dp))
                Text("Nümunə:", color = CYAN, fontWeight = FontWeight.Bold)
                CodePanel("print(\"Salam, dünya!\")")

                Spacer(Modifier.height(12.dp))
                Text(
                    "Bu kod işlədikdə ekranda Salam, dünya! yazısı görünür.",
                    color = Color.White
                )

                Spacer(Modifier.height(20.dp))
                ActionButton("DƏRSİ ÖYRƏNDİM — TESTƏ KEÇ") {
                    page.value = "quiz"
                }
            }

            "quiz" -> {
                Heading("BİLİK TESTİ", "Python-da ekrana yazı çıxarmaq üçün hansı əmr istifadə olunur?")

                listOf("input()", "print()", "if()", "loop()").forEach { option ->
                    OutlinedButton(
                        onClick = { answer.value = option },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = if (answer.value == option) CYAN else Color.White
                        )
                    ) {
                        Text(option)
                    }
                    Spacer(Modifier.height(7.dp))
                }

                ActionButton("CAVABI YOXL A") {
                    if (answer.value == "print()") {
                        if (!completed.value) {
                            xp.intValue += 50
                            completed.value = true
                        }
                        message.value = "Əla! Düzgün cavab. 50 XP qazandın!"
                    } else {
                        message.value = "Bu cavab düzgün deyil. Yenidən yoxla."
                    }
                }

                if (message.value.isNotEmpty()) {
                    Spacer(Modifier.height(15.dp))
                    Text(message.value, color = CYAN, fontWeight = FontWeight.Bold)
                }
            }

            "editor" -> {
                Heading("KOD LABORATORİYASI", "Tapşırıq: Salam yazısını ekrana çıxar.")

                CodePanel("print(\"Salam\")")

                Spacer(Modifier.height(14.dp))
                Text("Python kodunu buraya yaz:", color = CYAN)

                OutlinedTextField(
                    value = code.value,
                    onValueChange = { code.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    placeholder = { Text("print(\"Salam\")") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = CYAN,
                        unfocusedBorderColor = PURPLE
                    )
                )

                Spacer(Modifier.height(12.dp))

                ActionButton("KODU YOXL A") {
                    val normalized = code.value
                        .replace(" ", "")
                        .replace("\n", "")
                        .lowercase()

                    if (
                        normalized.contains("print(\"salam\")") ||
                        normalized.contains("print('salam')")
                    ) {
                        message.value = "Düzgündür! Tapşırığı tamamladın. +25 XP"
                        xp.intValue += 25
                    } else {
                        message.value = "Kod tapşırığa uyğun deyil. Nümunəyə bax."
                    }
                }

                if (message.value.isNotEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Text(message.value, color = CYAN)
                }

                Text(
                    "Qeyd: Bu ilk versiyada kod yoxlanışı nümunə əsasında aparılır. Hələ real Python mühərriki deyil.",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }

            "battle" -> {
                Heading("DÖYÜŞ ARENASI", "Rəqiblərlə yarış sistemi")

                InfoCard("⚔️", "1-ə-1 döyüş", "Onlayn server hələ qoşulmayıb.")
                InfoCard("👥", "Komanda döyüşü", "Növbəti mərhələdə hazırlanacaq.")
                InfoCard("🏆", "Turnirlər", "Növbəti mərhələdə hazırlanacaq.")

                Text(
                    "Bu funksiyaları real oyunçularla işlətmək üçün server və verilənlər bazası quracağıq.",
                    color = CYAN
                )
            }

            "leaders" -> {
                Heading("LİDERLƏR", "Sənin hazırkı nəticən")

                InfoCard("⭐", "Sənin XP xalın", "${xp.intValue} XP")
                InfoCard("🥉", "Başlanğıc rütbə", "Bronze")

                Text(
                    "Ümumi onlayn liderlər cədvəli server qoşulduqdan sonra işləyəcək.",
                    color = CYAN
                )
            }
        }

        Spacer(Modifier.height(30.dp))
        Text(
            "CODE WARS • CYBERPUNK EDITION",
            color = Color.Gray,
            fontSize = 10.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun Heading(title: String, subtitle: String) {
    Text(title, color = CYAN, fontSize = 21.sp, fontWeight = FontWeight.Black)
    Spacer(Modifier.height(8.dp))
    Text(subtitle, color = Color.White, fontSize = 15.sp)
    Spacer(Modifier.height(18.dp))
}

@Composable
fun InfoCard(emoji: String, title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        colors = CardDefaults.cardColors(containerColor = PANEL),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("$emoji  $title", color = CYAN, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(5.dp))
            Text(description, color = Color.White)
        }
    }
}

@Composable
fun MenuButton(
    emoji: String,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        colors = CardDefaults.cardColors(containerColor = PANEL),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(Modifier.padding(14.dp)) {
            Text("$emoji  $title", color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(5.dp))
            Text(subtitle, color = Color.LightGray, fontSize = 12.sp)
            Spacer(Modifier.height(8.dp))
            Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
                Text("AÇ")
            }
        }
    }
}

@Composable
fun ActionButton(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = PURPLE)
    ) {
        Text(title, color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CodePanel(code: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF050812)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            code,
            modifier = Modifier.padding(16.dp),
            color = CYAN,
            fontSize = 15.sp
        )
    }
}
