package lesson04.exercise

// =====================================================================
// Lesson 4 演習
//
// やること: TODO() になっている 6 つの関数を実装する。
//           main を実行して、すべて ✅ になれば完了。
//
// 今回も: main の末尾に「自分で考えたテストケース」を 2 つ以上足すこと。
//   コレクションの問題では、次の入力を必ず試す。
//     ・空のリスト
//     ・要素が 1 つだけのリスト
//     ・条件に当てはまるものが 1 つも無い場合
//
// 「試してみよう」も、コメントを外してコンパイルエラーを 1 度見てから戻すこと。
// =====================================================================

// ---------------------------------------------------------------------
// 問 1: filter と map
// 偶数だけを取り出して、それぞれを 2 倍にしたリストを返す。
// 例: doubleEvens(listOf(1, 2, 3, 4)) → [4, 8] / doubleEvens(listOf(1, 3)) → []
// ヒント: filter { ... } のあとに map { ... } をつなげる。
// ---------------------------------------------------------------------
fun doubleEvens(numbers: List<Int>): List<Int> {
    TODO("filter と map をつなげる")
}
// 試してみよう: listOf(1, 2, 3).add(4) と書くとどうなる？

// ---------------------------------------------------------------------
// 問 2: Map と null 安全
// 単語ごとの出現回数を数えて返す。
// 例: wordCount(listOf("a", "b", "a")) → {a=2, b=1} / wordCount(listOf()) → {}
// ヒント: mutableMapOf<String, Int>() を用意して、for で回す。
//         map[word] は、まだ無いと null。レッスン 1 の ?: が使える。
// ---------------------------------------------------------------------
fun wordCount(words: List<String>): Map<String, Int> {
    TODO("mutableMapOf と ?: を使う")
}
// 試してみよう: val m = mapOf("a" to 1); println(m["b"] + 1) と書くとどうなる？

// ---------------------------------------------------------------------
// 問 3: データのリストを集計する
// 商品のリストから、指定したカテゴリの値段の合計を返す。
// 例: totalPrice(products, "food") → food カテゴリの値段の合計
//     該当する商品が 1 つも無ければ 0
// ヒント: filter で絞ってから sumOf { ... } で合計する。
// ---------------------------------------------------------------------
data class Product(val name: String, val price: Int, val category: String)

fun totalPrice(products: List<Product>, category: String): Int {
    TODO("filter と sumOf をつなげる")
}

// ---------------------------------------------------------------------
// 問 4: 関数を引数にする関数（高階関数）
// x に対して、関数 f を times 回くり返し適用した結果を返す。
// 例: applyN(1, 3) { it * 2 } → 8   （1 → 2 → 4 → 8）
//     applyN(5, 0) { it + 100 } → 5 （0 回なら x のまま）
// ヒント: var で結果を持ち、repeat(times) { ... } の中で更新する。
//         呼び出し側は、すでに main に末尾ラムダの形で書いてある。
// ---------------------------------------------------------------------
fun applyN(x: Int, times: Int, f: (Int) -> Int): Int {
    TODO("repeat を使って f を times 回適用する")
}
// 試してみよう: main の applyN(1, 3) { it * 2 } を applyN(1, 3, it * 2) に書き換えるとどうなる？

// ---------------------------------------------------------------------
// 問 5（発展）: 並べ替えと絞り込みをつなげる
// 値段の安い順に n 個の商品を選び、その名前のリストを返す。
// 例: 値段が pen=120, notebook=200, apple=80, bread=150 のとき、
//     cheapestNames(products, 2) → [apple, pen]
//     n が商品数より大きければ、全部を安い順に返す。n が 0 なら空のリスト。
// ヒント: sortedBy { ... } → take(n) → map { ... }
// ---------------------------------------------------------------------
fun cheapestNames(products: List<Product>, n: Int): List<String> {
    TODO("sortedBy, take, map をつなげる")
}

// ---------------------------------------------------------------------
// 問 6（発展）: 関数を Map に入れる
// 演算子の文字列に応じて、計算した結果を返す。
//   "+" "-" "*" "/"  → a と b を計算した結果（"/" は整数の割り算）
//   上記以外の文字列    → null
//   "/" で b が 0     → null（0 で割ると例外で落ちてしまうため）
// 例: calc("+", 2, 3) → 5 / calc("/", 6, 3) → 2 / calc("%", 6, 3) → null / calc("/", 1, 0) → null
// ヒント: 関数を値にして Map に入れられる。
//   val operations = mapOf<String, (Int, Int) -> Int>(
//       "+" to { a, b -> a + b },
//       ...
//   )
//   operations[op] は無ければ null。関数が見つかったら operations[op]?.invoke(a, b) で呼べる。
// ---------------------------------------------------------------------
fun calc(op: String, a: Int, b: Int): Int? {
    TODO("関数を入れた Map を使う")
}

// ---------------------------------------------------------------------
// ここから下は採点用。
// 上の「今回も」のとおり、check を自分で足してよい（既存の行は変えない）。
// ---------------------------------------------------------------------
private val products = listOf(
    Product("pen", 120, "stationery"),
    Product("notebook", 200, "stationery"),
    Product("apple", 80, "food"),
    Product("bread", 150, "food"),
)

fun main() {
    check("問1 doubleEvens([1,2,3,4])", listOf(4, 8)) { doubleEvens(listOf(1, 2, 3, 4)) }
    check("問1 doubleEvens([1,3])", emptyList<Int>()) { doubleEvens(listOf(1, 3)) }
    check("問1 doubleEvens([])", emptyList<Int>()) { doubleEvens(emptyList()) }

    check("問2 wordCount([a,b,a])", mapOf("a" to 2, "b" to 1)) { wordCount(listOf("a", "b", "a")) }
    check("問2 wordCount([])", emptyMap<String, Int>()) { wordCount(emptyList()) }

    check("問3 totalPrice(food)", 230) { totalPrice(products, "food") }
    check("問3 totalPrice(stationery)", 320) { totalPrice(products, "stationery") }
    check("問3 totalPrice(toys)", 0) { totalPrice(products, "toys") }

    check("問4 applyN(1, 3) { it * 2 }", 8) { applyN(1, 3) { it * 2 } }
    check("問4 applyN(5, 0) { it + 100 }", 5) { applyN(5, 0) { it + 100 } }
    check("問4 applyN(10, 2) { it - 3 }", 4) { applyN(10, 2) { it - 3 } }

    check("問5 cheapestNames(2)", listOf("apple", "pen")) { cheapestNames(products, 2) }
    check("問5 cheapestNames(10)", listOf("apple", "pen", "bread", "notebook")) { cheapestNames(products, 10) }
    check("問5 cheapestNames(0)", emptyList<String>()) { cheapestNames(products, 0) }

    check("問6 calc(+)", 5) { calc("+", 2, 3) }
    check("問6 calc(-)", -1) { calc("-", 2, 3) }
    check("問6 calc(*)", 6) { calc("*", 2, 3) }
    check("問6 calc(/)", 2) { calc("/", 6, 3) }
    check("問6 calc(% は未対応)", null) { calc("%", 6, 3) }
    check("問6 calc(/ を 0 で)", null) { calc("/", 1, 0) }

    // ↓ ここに、自分で考えたテストケースを 2 つ以上足す


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
