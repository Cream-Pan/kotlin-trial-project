package lesson01

// =====================================================================
// Lesson 1: 変数・型・文字列テンプレート・null 安全
//
// 使い方: 左の行番号の横にある ▶ を押すと main が実行される。
//         各関数の中身を読み、値を書き換えて、結果がどう変わるか試すこと。
//         （JS の「ブラウザのコンソールで試す」感覚で使う）
// =====================================================================

fun main() {
    variables()
    types()
    stringTemplates()
    nullSafety()
}

// ---------------------------------------------------------------------
// 1. 変数: val と var
// ---------------------------------------------------------------------
private fun variables() {
    println("--- 1. variables ---")

    val siteName = "Trial"   // JS: const siteName = "Trial"
    var count = 0            // JS: let count = 0
    val subName = "Name"

    count += 1               // var は再代入できる
    // siteName = "Other"    // ← コンパイルエラー。val は再代入できない

    println("$siteName: $count")

    count += 1

    println("$subName: $count")

    // 規約: まず val で書く。再代入が必要になったときだけ var にする。
    // （Compose の状態も「val で受け取り、変えたいときだけ State を使う」設計になる）
}

// ---------------------------------------------------------------------
// 2. 型: JS は number 1 種類だが、Kotlin は整数・小数を区別する
// ---------------------------------------------------------------------
private fun types() {
    println("--- 2. types ---")

    val age = 20                // Int      （型推論。val age: Int = 20 と同じ）
    val price = 9.99            // Double
    val isOpen = true           // Boolean
    val initial = 'A'           // Char     （1 文字。"A" は String）
    val population = 3_000_000_000L // Long （L を付ける。_ は桁区切り）

    println("$age / $price / $isOpen / $initial / $population")

    // ★ JS との大きな違い①: 整数どうしの割り算は整数になる
    println(7 / 2)       // 3    （JS だと 3.5）
    println(7 / 2.0)     // 3.5  （片方が Double なら Double）

    // ★ JS との大きな違い②: 型は自動で変換されない
    // val d: Double = age        // ← コンパイルエラー
    val d: Double = age.toDouble() // 明示的に変換する
    println(d)                      // 20.0

    // Dice Roller の (1..6).random() の 1..6 は「Int の範囲」を表す式
    println((1..6).random())

    val random = (10..20).random()

    val isClose = !isOpen

    println("${price.toInt()} is less than $random is ${isClose.toString()}")
}

// ---------------------------------------------------------------------
// 3. 文字列テンプレート: JS のテンプレートリテラルに相当
// ---------------------------------------------------------------------
private fun stringTemplates() {
    println("--- 3. string templates ---")

    val name = "Taro"
    val a = 3
    val b = 4

    // JS: `Hello, ${name}!`
    println("Hello, $name!")           // 変数だけなら { } は不要
    println("a + b = ${a + b}")        // 式を入れるときは ${ } を使う
    println("長さ: ${name.length}")     // プロパティ参照も ${ } で

    // 複数行文字列: JS のバッククォートに相当するのは """ """
    val message = """
        1 行目
        2 行目（$name さん）
    """.trimIndent()                   // 先頭の余分なインデントを除く

    val message2 = """
        1 行目
        2 行目（$name さん）
    """

    println(message)
    println(message2)
}

// ---------------------------------------------------------------------
// 4. null 安全: Kotlin の最大の特徴のひとつ
//
// JS: どの変数にも null / undefined が入りうる
//     → 実行して初めて "Cannot read properties of undefined" で落ちる
// Kotlin: 「null を入れてよい型」と「ダメな型」を型で区別する
//     → 落ちる前に、コンパイルの時点でエラーになる
// ---------------------------------------------------------------------
private fun nullSafety() {
    println("--- 4. null safety ---")

    val name: String = "Taro"      // String は null 禁止
    // val bad: String = null      // ← コンパイルエラー
    val nickname: String? = null   // String? は null を入れてよい（? を付ける）

    println(name.length)
    // println(nickname.length)    // ← コンパイルエラー。null かもしれないので呼べない

    println(lengthOrNull(null))    // null
    println(lengthOrNull("Taro"))  // 4
    println(lengthOrZero(null))    // 0
    println(describe(null))        // ニックネームなし
    println(describe("Tarō"))      // ニックネーム: Tarō
    println(nickname)              // null
    println(described(null, 5))
    println(described("taka", 5))
}

// ?.  セーフコール: null なら呼ばずに null を返す（JS のオプショナルチェーン ?. と同じ）
private fun lengthOrNull(text: String?): Int? = text?.length

// ?:  エルビス演算子: 左が null なら右を使う（JS の ?? と同じ）
private fun lengthOrZero(text: String?): Int = text?.length ?: 0

// null チェックのあとは、そのブロック内で自動的に非 null 型として扱える（スマートキャスト）
private fun describe(nickname: String?): String {
    if (nickname != null) {
        return "ニックネーム: $nickname"   // ここでは nickname は String 型
    }
    return "ニックネームなし"
}

private fun described(nickname: String?, num: Int?): String{
    if(nickname != null){
        return "$nickname is No.$num"
    }
    return "No.$num"
}

// !! （非 null アサーション）は「絶対 null ではない」と宣言して null 安全を捨てる書き方。
// null だったら実行時に例外で落ちる。使わなくて済む場面ではほぼ使わない。
//   val len = nickname!!.length   // nickname が null なら NullPointerException
