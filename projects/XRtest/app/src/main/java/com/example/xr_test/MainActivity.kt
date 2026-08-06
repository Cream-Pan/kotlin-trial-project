package com.example.xr_test

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

enum class AppScreen {
    HOME,
    DICE,
    STARFIELD
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf(AppScreen.HOME) }

    when (currentScreen) {
        AppScreen.HOME -> HomeScreen(
            onNavigateToDice = { currentScreen = AppScreen.DICE },
            onNavigateToStarfield = { currentScreen = AppScreen.STARFIELD }
        )
        AppScreen.DICE -> Interactive3DDiceScreen(
            onBackToHome = { currentScreen = AppScreen.HOME }
        )
        AppScreen.STARFIELD -> StarfieldScreen(
            onBackToHome = { currentScreen = AppScreen.HOME }
        )
    }
}

// -----------------------------------------------------------------------------
// 1. ホーム画面
// -----------------------------------------------------------------------------
@Composable
fun HomeScreen(
    onNavigateToDice: () -> Unit,
    onNavigateToStarfield: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "👓 XR Kotlin Lab",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onNavigateToDice,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp)
        ) {
            Text(text = "🎲 1. 3Dサイコロ", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToStarfield,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp)
        ) {
            Text(text = "🚀 2. 3Dワープ空間", fontSize = 18.sp)
        }
    }
}

// -----------------------------------------------------------------------------
// 2. 3Dサイコロ画面
// -----------------------------------------------------------------------------
data class Point3D(val x: Float, val y: Float, val z: Float) {
    fun rotateX(angleRad: Float): Point3D {
        val c = cos(angleRad)
        val s = sin(angleRad)
        return Point3D(x, y * c - z * s, y * s + z * c)
    }
    fun rotateY(angleRad: Float): Point3D {
        val c = cos(angleRad)
        val s = sin(angleRad)
        return Point3D(x * c + z * s, y, -x * s + z * c)
    }
}

data class Face3D(
    val vertexIndices: List<Int>,
    val color: Color,
    val text: String
)

@Composable
fun Interactive3DDiceScreen(onBackToHome: () -> Unit) {
    var angleX by remember { mutableFloatStateOf(0.5f) }
    var angleY by remember { mutableFloatStateOf(0.6f) }
    var isRolling by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val cubeSize = 100f
    val baseVertices = remember {
        listOf(
            Point3D(-cubeSize, -cubeSize, -cubeSize),
            Point3D( cubeSize, -cubeSize, -cubeSize),
            Point3D( cubeSize,  cubeSize, -cubeSize),
            Point3D(-cubeSize,  cubeSize, -cubeSize),
            Point3D(-cubeSize, -cubeSize,  cubeSize),
            Point3D( cubeSize, -cubeSize,  cubeSize),
            Point3D( cubeSize,  cubeSize,  cubeSize),
            Point3D(-cubeSize,  cubeSize,  cubeSize)
        )
    }

    val faces = remember {
        listOf(
            Face3D(listOf(4, 5, 6, 7), Color(0xFFE57373), "1"),
            Face3D(listOf(1, 0, 3, 2), Color(0xFF81C784), "6"),
            Face3D(listOf(0, 4, 7, 3), Color(0xFF64B5F6), "2"),
            Face3D(listOf(5, 1, 2, 6), Color(0xFFFFB74D), "5"),
            Face3D(listOf(0, 1, 5, 4), Color(0xFFBA68C8), "3"),
            Face3D(listOf(7, 6, 2, 3), Color(0xFFFFD54F), "4")
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        OutlinedButton(
            onClick = onBackToHome,
            modifier = Modifier
                .padding(top = 40.dp, start = 16.dp)
                .align(Alignment.TopStart)
        ) {
            Text("🏠 ホーム")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "🎲 3Dサイコロ (ドラッグで回転)",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Canvas(
                modifier = Modifier
                    .size(320.dp)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            if (!isRolling) {
                                angleY += dragAmount.x * 0.01f
                                angleX -= dragAmount.y * 0.01f
                            }
                        }
                    }
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height

                val rotatedVertices = baseVertices.map {
                    it.rotateX(angleX).rotateY(angleY)
                }

                val cameraDist = 500f
                val projectedPoints = rotatedVertices.map { pt ->
                    val scale = cameraDist / (cameraDist - pt.z)
                    Offset(
                        x = canvasWidth / 2f + pt.x * scale,
                        y = canvasHeight / 2f + pt.y * scale
                    )
                }

                val sortedFaces = faces.map { face ->
                    val avgZ = face.vertexIndices.sumOf { rotatedVertices[it].z.toDouble() } / 4.0
                    val centerPt = Offset(
                        x = face.vertexIndices.sumOf { projectedPoints[it].x.toDouble() }.toFloat() / 4f,
                        y = face.vertexIndices.sumOf { projectedPoints[it].y.toDouble() }.toFloat() / 4f
                    )
                    Triple(face, avgZ, centerPt)
                }.sortedBy { it.second }

                sortedFaces.forEach { (face, _, centerPt) ->
                    val path = Path().apply {
                        val p0 = projectedPoints[face.vertexIndices[0]]
                        moveTo(p0.x, p0.y)
                        for (i in 1..3) {
                            val p = projectedPoints[face.vertexIndices[i]]
                            lineTo(p.x, p.y)
                        }
                        close()
                    }

                    drawPath(path, face.color)

                    drawContext.canvas.nativeCanvas.drawText(
                        face.text,
                        centerPt.x,
                        centerPt.y + 15f,
                        Paint().apply {
                            color = android.graphics.Color.WHITE
                            textSize = 55f
                            textAlign = Paint.Align.CENTER
                            typeface = Typeface.DEFAULT_BOLD
                        }
                    )
                }
            }

            Button(
                enabled = !isRolling,
                onClick = {
                    coroutineScope.launch {
                        isRolling = true
                        val targetX = angleX + (3..6).random() * 6.28f + (0..628).random() / 100f
                        val targetY = angleY + (3..6).random() * 6.28f + (0..628).random() / 100f

                        val jobX = launch {
                            Animatable(angleX).animateTo(targetX, tween(1000)) {
                                angleX = value
                            }
                        }
                        val jobY = launch {
                            Animatable(angleY).animateTo(targetY, tween(1000)) {
                                angleY = value
                            }
                        }
                        jobX.join()
                        jobY.join()
                        isRolling = false
                    }
                },
                modifier = Modifier.padding(bottom = 48.dp)
            ) {
                Text(text = if (isRolling) "回転中..." else "サイコロを振る 🎲", fontSize = 18.sp)
            }
        }
    }
}

// -----------------------------------------------------------------------------
// 3. 3Dワープ空間画面（アニメーションバグ修正版）
// -----------------------------------------------------------------------------
data class Star(
    var x: Float,
    var y: Float,
    var z: Float
)

@Composable
fun StarfieldScreen(onBackToHome: () -> Unit) {
    val starCount = 500
    val stars = remember {
        List(starCount) {
            Star(
                x = Random.nextFloat() * 2000f - 1000f,
                y = Random.nextFloat() * 2000f - 1000f,
                z = Random.nextFloat() * 1000f + 1f
            )
        }
    }

    var isWarping by remember { mutableStateOf(false) }
    // ★ 毎フレームの再描画を呼び出すためのトリガー State
    var frameTick by remember { mutableLongStateOf(0L) }

    LaunchedEffect(Unit) {
        while (true) {
            withFrameNanos { nanos ->
                val speed = if (isWarping) 40f else 8f
                stars.forEach { star ->
                    star.z -= speed
                    if (star.z <= 0) {
                        star.x = Random.nextFloat() * 2000f - 1000f
                        star.y = Random.nextFloat() * 2000f - 1000f
                        star.z = 1000f
                    }
                }
                // ★毎フレームナノ秒タイムスタンプを更新して Compose に再描画を指示
                frameTick = nanos
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isWarping = true
                        tryAwaitRelease()
                        isWarping = false
                    }
                )
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // ★ frameTick を参照することで、フレームごとに Canvas が再描画される
            @Suppress("UNUSED_VARIABLE")
            val tick = frameTick

            val cx = size.width / 2f
            val cy = size.height / 2f
            val fov = 400f

            stars.forEach { star ->
                val k = fov / star.z
                val px = star.x * k + cx
                val py = star.y * k + cy

                if (px in 0f..size.width && py in 0f..size.height) {
                    val radius = ((1000f - star.z) / 1000f * 8f).coerceAtLeast(1f)
                    val alpha = ((1000f - star.z) / 1000f).coerceIn(0f, 1f)

                    drawCircle(
                        color = Color.White.copy(alpha = alpha),
                        radius = radius,
                        center = Offset(px, py)
                    )
                }
            }
        }

        Text(
            text = if (isWarping) "⚡ WARP SPEED ⚡" else "画面長押しでワープ加速！",
            color = if (isWarping) Color.Cyan else Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp)
        )

        OutlinedButton(
            onClick = onBackToHome,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
            modifier = Modifier
                .padding(top = 40.dp, start = 16.dp)
                .align(Alignment.TopStart)
        ) {
            Text("🏠 ホーム")
        }
    }
}