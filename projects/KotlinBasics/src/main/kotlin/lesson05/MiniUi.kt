package lesson05

// =====================================================================
// MiniUi: Compose の「書き方」と「仕組み」を体験するための、ごく小さな UI ライブラリ
//
// 本物の Compose との違い
//   ・本物は画面に描くが、これは「どんな UI になるか」を文字で出力する。
//   ・本物は @Composable という印と専用のコンパイラ機能を使う。ここは普通の関数だけ。
//   ・本物は「どの状態が読まれたか」を自動で追跡して、必要な部分だけ再実行する。
//     ここは単純に、ボタンが押されるたびに、全体をもう一度実行する。
//
// それでも、「関数の中でほかの関数を呼ぶ」「{ } は関数」「状態が変わったら
// もう一度実行する」という Compose の核心は同じ。
// このファイルを読んで、Compose の { } の正体を確かめること。
// =====================================================================

// ---------------------------------------------------------------------
// Modifier: 見た目を指定する「設定の連鎖」
//
//   Modifier.padding(8).fillMaxWidth()
//
// 呼ぶたびに「新しい Modifier」を返す。だから . でつなげられる。
// （レッスン 3 の data class の copy と同じ考え方。元の Modifier は変わらない）
//
// 先頭の Modifier は、クラス名ではなく companion object（レッスン 3）。
// companion object 自身が「何も指定していない Modifier」になっている。
// ---------------------------------------------------------------------
open class Modifier(
    private val paddingDp: Int = 0,
    private val fillsWidth: Boolean = false,
) {
    fun padding(dp: Int): Modifier = Modifier(dp, fillsWidth)

    fun fillMaxWidth(): Modifier = Modifier(paddingDp, true)

    internal fun parts(): List<String> {
        val result = mutableListOf<String>()
        if (paddingDp != 0) result.add("padding=$paddingDp")
        if (fillsWidth) result.add("fillMaxWidth")
        return result
    }

    companion object : Modifier()
}

// ---------------------------------------------------------------------
// Ui: 画面を動かす「実行係」（本物の Compose ではフレームワークがやる部分）
//
//   setContent { ... }   画面を表す関数を登録して、1 回実行する
//   click("ラベル")       そのボタンの onClick を呼び、そのあと画面をもう一度実行する
//   lines                最新の実行結果（1 行 1 要素）
//   capture { ... }      渡した関数を 1 回だけ実行して、結果の行を返す（演習の採点用）
// ---------------------------------------------------------------------
object Ui {
    var echo = true                                   // true なら、結果を println でも出す

    private val outputLines = mutableListOf<String>()
    val lines: List<String> get() = outputLines

    private val handlers = mutableMapOf<String, () -> Unit>()   // ボタンのラベル → onClick
    private var depth = 0                                       // 入れ子の深さ（インデント用）
    private var root: (() -> Unit)? = null                      // 画面を表す関数（無いこともあるので ? 付き）

    fun setContent(content: () -> Unit) {
        root = content
        render("画面を登録")
    }

    fun click(label: String) {
        val handler = handlers[label] ?: error("ボタン \"$label\" は画面にありません")
        handler()                          // ① onClick が状態を書き換える
        render("\"$label\" を押したあとの再実行")   // ② 画面を表す関数を、もう一度実行する（これが「再コンポーズ」の考え方）
    }

    fun capture(content: () -> Unit): List<String> {
        val savedEcho = echo
        echo = false
        reset()
        try {
            content()
        } finally {
            echo = savedEcho
        }
        return outputLines.toList()
    }

    private fun render(reason: String) {
        if (echo) println("┄┄┄ $reason ┄┄┄")
        reset()
        root?.invoke()
    }

    private fun reset() {
        outputLines.clear()
        handlers.clear()
        depth = 0
    }

    internal fun line(text: String) {
        val indented = "  ".repeat(depth) + text
        outputLines.add(indented)
        if (echo) println(indented)
    }

    internal fun nested(content: () -> Unit) {
        depth++
        try {
            content()
        } finally {
            depth--
        }
    }

    internal fun register(label: String, onClick: () -> Unit) {
        handlers[label] = onClick
    }
}

// ---------------------------------------------------------------------
// UI 部品: どれも「ただの関数」
//
// column / row は、最後の引数 content が「関数」。
// だから column { ... } と、末尾ラムダで書ける（レッスン 4）。
// column の中で content() を呼ぶことで、{ } の中身が実行される。
// その前後で Ui.nested を呼ぶだけで、「子要素」の入れ子が表現できる。
// ---------------------------------------------------------------------
fun text(content: String, modifier: Modifier = Modifier) {
    val parts = modifier.parts()
    val extra = if (parts.isEmpty()) "" else ", " + parts.joinToString(", ")
    Ui.line("Text(\"$content\"$extra)")
}

fun button(label: String, onClick: () -> Unit) {
    Ui.line("Button(\"$label\")")
    Ui.register(label, onClick)
}

fun column(modifier: Modifier = Modifier, content: () -> Unit) {
    container("Column", modifier, content)
}

fun row(modifier: Modifier = Modifier, content: () -> Unit) {
    container("Row", modifier, content)
}

private fun container(name: String, modifier: Modifier, content: () -> Unit) {
    val parts = modifier.parts()
    val extra = if (parts.isEmpty()) "" else "(" + parts.joinToString(", ") + ")"
    Ui.line(name + extra)
    Ui.nested(content)     // 子要素は、1 段深くして実行する
}
