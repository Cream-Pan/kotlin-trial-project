package lesson02

// =====================================================================
// Lesson 2: 関数・条件式・範囲とループ
//
// 使い方: Lesson 1 と同じ。▶ で実行し、値を書き換えて結果の変化を試す。
// =====================================================================

fun main() {
    functions()
    defaultAndNamedArguments()
    ifExpression()
    whenExpression()
    rangesAndLoops()
    equality()
}

// ---------------------------------------------------------------------
// 1. 関数
//
// JS:     function add(a, b) { return a + b }
// Kotlin: fun add(a: Int, b: Int): Int { return a + b }
//
// ・引数には必ず型を書く。戻り値の型は「: 型」で書く。
// ・値を返さない関数の戻り値は Unit（JS の undefined に近い。普通は省略する）。
// ---------------------------------------------------------------------
private fun add(a: Int, b: Int): Int {
    return a + b
}

// 本体が式 1 つなら「= 式」と書ける（式本体）。戻り値の型は推論されるので省略できる。
// JS のアロー関数 (a, b) => a * b に近い書き心地。
private fun multiply(a: Int, b: Int) = a * b

// 戻り値なし。型を省略した Unit が返る。
private fun sayHello(name: String) {
    println("Hello, $name")
}

private fun functions() {
    println("--- 1. functions ---")
    println(add(2, 3))
    println(multiply(2, 3))
    sayHello("Taro")
}

// ---------------------------------------------------------------------
// 2. デフォルト引数と名前付き引数
//
// JS にもデフォルト引数はあるが、「名前を指定して呼ぶ」ことはできない
// （オブジェクトを渡して分割代入するのが普通）。Kotlin は言語として持っている。
//
// Lemonade の Text(text = "...", fontWeight = FontWeight.Bold) の
// 「text =」「fontWeight =」がまさに名前付き引数。
// Compose は、この 2 つの機能を前提に作られている。
// ---------------------------------------------------------------------
private fun buildMessage(
    name: String,
    greeting: String = "Hello",
    punctuation: String = "!",
): String {
    return "$greeting, $name$punctuation"
}

private fun defaultAndNamedArguments() {
    println("--- 2. default / named arguments ---")
    println(buildMessage("Taro"))                      // Hello, Taro!
    println(buildMessage("Taro", "Hi"))                // Hi, Taro!
    println(buildMessage("Taro", punctuation = "?"))   // 途中の引数を飛ばせる
    println(buildMessage(punctuation = "...", name = "Hanako")) // 順番も自由
}

// ---------------------------------------------------------------------
// 3. if は「式」: 値を返せる
//
// Kotlin には三項演算子 (cond ? a : b) がない。if 自体が値を返すので不要。
// ---------------------------------------------------------------------
private fun biggerOf(a: Int, b: Int) = if (a > b) a else b

private fun ifExpression() {
    println("--- 3. if expression ---")
    val score = 72

    // JS: const result = score >= 60 ? "合格" : "不合格"
    val result = if (score >= 60) "合格" else "不合格"

    val rank = if (score >= 90) {
        "A"
    } else if (score >= 70) {
        "B"            // ブロックの最後の式が、そのブロックの値になる
    } else {
        "C"
    }

    println("$result / $rank")
    println(biggerOf(3, 8))
}

// ---------------------------------------------------------------------
// 4. when: JS の switch の上位互換
//
// ・break が要らない（1 つ当たったら終わり。フォールスルーしない）。
// ・値だけでなく、複数の値・範囲・条件式も書ける。
// ・式として使うときは else が必須（全ケースを網羅するため）。
//
// Lemonade の when (currentStep) { 1 -> ...; 2 -> ... } と同じ仕組み。
// ---------------------------------------------------------------------
private fun dayType(day: Int): String =
    when (day) {
        1, 7 -> "休日"          // 複数の値は , でつなぐ
        in 2..6 -> "平日"       // 範囲は in
        else -> "不正な値"
    }

// 対象を取らない when: if-else if の連鎖をすっきり書ける
private fun classify(n: Int): String = when {
    n < 0 -> "負"
    n == 0 -> "ゼロ"
    else -> "正"
}

private fun whenExpression() {
    println("--- 4. when ---")
    println(dayType(1))     // 休日
    println(dayType(3))     // 平日
    println(dayType(9))     // 不正な値
    println(classify(-5))
    println(classify(0))
    println(classify(7))
}

// ---------------------------------------------------------------------
// 5. 範囲（range）とループ
//
// JS の for (let i = 0; i < n; i++) に相当する書き方は Kotlin にはない。
// 代わりに「範囲」を使う。
//
// Dice Roller の (1..6).random() の 1..6 も、この範囲。
// ---------------------------------------------------------------------
private fun rangesAndLoops() {
    println("--- 5. ranges and loops ---")

    for (i in 1..3) print("$i ")             // 1 2 3   （1..3 は 3 を含む）
    println()

    for (i in 1 until 3) print("$i ")        // 1 2     （until は終端を含まない）
    println()

    for (i in 5 downTo 1 step 2) print("$i ") // 5 3 1  （downTo は降順，step は間隔）
    println()

    println(5 in 1..10)                       // true    （範囲に含まれるか）

    // 回数が決まっているだけなら repeat。{ } の中身は「ラムダ」。レッスン 4 で詳しく扱う。
    repeat(2) { println("repeat: $it") }

    var n = 3
    while (n > 0) {
        print("$n ")
        n--
    }
    println()
}

// ---------------------------------------------------------------------
// 6. 等価の比較
//
// Kotlin の == は「中身が等しいか」を比べる（JS の === に近い。型の自動変換はしない）。
// 文字列でも同じ。null かどうかは == null で調べる。
//
// ★ よくある勘違い: 文字列の "null" と、値の null は別物。
//    text == "null" は「"null" という 4 文字の文字列か」を調べている。
// ---------------------------------------------------------------------
private fun isNullValue(text: String?) = text == null
private fun isNullText(text: String?) = text == "null"

private fun equality() {
    println("--- 6. equality ---")

    val a = "kotlin"
    val b = "kot" + "lin"
    println(a == b)                 // true（中身が同じ）

    println(isNullValue(null))      // true
    println(isNullValue("null"))    // false（文字列 "null" は null ではない）
    println(isNullText(null))       // false
    println(isNullText("null"))     // true
}
