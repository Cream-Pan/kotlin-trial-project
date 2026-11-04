package lesson01.exercise

// =====================================================================
// Lesson 1 演習
//
// やること: TODO() になっている 5 つの関数を実装する。
//           main を実行すると ✅ / ❌ / ⏳(未実装) が表示される。
//           すべて ✅ になれば完了。
//
// 注意: Lesson01.kt を見返してよい。ただしコピペせず、自分で書くこと。
// =====================================================================

// 問 1: 文字列テンプレート
// 例: greet("Taro", 20) → "Hello, Taro! You are 20 years old."
fun greet(name: String, age: Int): String {
    val sentence = "Hello, $name! You are $age years old."

    return sentence
}

// 問 2: 型と割り算
// 2 つの Int の平均を Double で返す。
// 例: average(3, 4) → 3.5   （3 ではない点に注意。ヒント: Lesson01 の「大きな違い①②」）
fun average(a: Int, b: Int): Double {
    val sum: Int = a + b
    val ans = sum / 2.0

    return ans
}

// 問 3: エルビス演算子 ?:
// nickname が null なら "名無し"、そうでなければ nickname をそのまま返す。
fun displayName(nickname: String?): String {
    return nickname?: "名無し"
}

// 問 4: セーフコール ?.
// text の 1 文字目を返す。text が null または空文字なら null を返す。
// 例: firstChar("Kotlin") → 'K' / firstChar("") → null / firstChar(null) → null
// ヒント: String には firstOrNull() というメソッドがある。
fun firstChar(text: String?): Char? {
    return text?.firstOrNull()
}

// 問 5（発展）: null チェックと比較
// age が null なら false、18 以上なら true、それ以外は false。
// 例: isAdult(20) → true / isAdult(17) → false / isAdult(null) → false
// ヒント: age >= 18 は age が Int? のままだとコンパイルエラー。どう解決する？
fun isAdult(age: Int?): Boolean {
    return (age ?: 0) >= 18
}

// ---------------------------------------------------------------------
// ここから下は採点用。編集しない。
// ---------------------------------------------------------------------
fun main() {
    check("問1 greet", "Hello, Taro! You are 20 years old.") { greet("Taro", 20) }
    check("問2 average(3, 4)", 3.5) { average(3, 4) }
    check("問2 average(10, 20)", 15.0) { average(10, 20) }
    check("問3 displayName(null)", "名無し") { displayName(null) }
    check("問3 displayName(\"Hanako\")", "Hanako") { displayName("Hanako") }
    check("問4 firstChar(\"Kotlin\")", 'K') { firstChar("Kotlin") }
    check("問4 firstChar(\"\")", null) { firstChar("") }
    check("問4 firstChar(null)", null) { firstChar(null) }
    check("問5 isAdult(20)", true) { isAdult(20) }
    check("問5 isAdult(17)", false) { isAdult(17) }
    check("問5 isAdult(null)", false) { isAdult(null) }
}

private fun check(label: String, expected: Any?, actual: () -> Any?) {
    val result = try {
        actual()
    } catch (e: NotImplementedError) {
        println("⏳ $label: 未実装")
        return
    }
    if (result == expected) {
        println("✅ $label")
    } else {
        println("❌ $label: 期待値=$expected / 実際=$result")
    }
}
