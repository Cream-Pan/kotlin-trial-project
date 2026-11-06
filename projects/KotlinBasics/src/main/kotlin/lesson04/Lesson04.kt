package lesson04

// =====================================================================
// Lesson 4: コレクションとラムダ
//
// 使い方: これまでと同じ。▶ で実行し、値を書き換えて結果の変化を試す。
//
// このレッスンの後半（ラムダ）は、Compose のコードを読むうえで最も大事な部分。
//   Column { Text("...") }
//   Button(onClick = { ... }) { Text("OK") }
// この { } は、すべてラムダ。レッスン 5 で、これを自分で作る。
// =====================================================================

fun main() {
    lists()
    setsAndMaps()
    lambdas()
    trailingLambdas()
    collectionOperations()
}

// ---------------------------------------------------------------------
// 1. List
//
// JS:     const fruits = ["apple", "banana"]   ← 配列。後から push できる
// Kotlin: val fruits = listOf("apple", "banana")   ← 読み取り専用。追加・変更できない
//         val fruits = mutableListOf("apple")      ← 追加・変更できる
//
// 「変えられるか」を型で区別するのは、レッスン 1 の val / var、
// レッスン 3 の private set と同じ考え方。
// ---------------------------------------------------------------------
private fun lists() {
    println("--- 1. list ---")

    val fruits = listOf("apple", "banana", "cherry")
    println(fruits[0])                    // apple
    println(fruits.size)                  // 3
    println("banana" in fruits)           // true
    println(fruits.first())               // apple
    println(fruits.last())                // cherry

    // 範囲外のとき: JS は undefined だが、Kotlin は例外で落ちる。
    // fruits[10]                         // ← IndexOutOfBoundsException
    println(fruits.getOrNull(10))         // null（落とさず null を返す。レッスン 1 の null 安全とつながる）

    // fruits.add("date")                 // ← エラー。listOf は追加できない
    val more = fruits + "date"            // + は「新しいリスト」を作る（元は変わらない）
    println(more)
    println(fruits)                       // 元のまま

    val items = mutableListOf("a", "b")
    items.add("c")                        // [a, b, c]
    items[0] = "z"                        // [z, b, c]
    items.removeAt(1)                     // [z, c]
    println(items)

    for (fruit in fruits) println(fruit)
    for ((index, fruit) in fruits.withIndex()) println("$index: $fruit")   // 番号つき
}

// ---------------------------------------------------------------------
// 2. Set と Map
//
// Set: 重複を持たない。
// Map: キーと値の組。JS のオブジェクト { a: 1 } や Map に近い。
//
// ★ 重要: map["キー"] は、存在しないキーだと null を返す。型は Int?（null 許容）。
//    レッスン 1 の ?: が、ここで出番になる。
// ---------------------------------------------------------------------
private fun setsAndMaps() {
    println("--- 2. set / map ---")

    val numbers = setOf(1, 2, 2, 3, 3, 3)
    println(numbers)                      // [1, 2, 3]

    val scores = mapOf("Taro" to 80, "Hanako" to 95)   // "キー" to 値 で組を作る
    println(scores["Taro"])               // 80
    println(scores["Jiro"])               // null
    println(scores["Jiro"] ?: 0)          // 0（無ければ 0 として扱う）
    // println(scores["Jiro"] + 1)        // ← エラー。Int? に Int は足せない

    val mutableScores = mutableMapOf("Taro" to 80)
    mutableScores["Jiro"] = 70            // 追加
    mutableScores["Taro"] = 85            // 更新
    for ((name, score) in mutableScores) {      // レッスン 3 の分割代入と同じ
        println("$name: $score")
    }

    println(scores.keys)                  // [Taro, Hanako]
    println(scores.values)                // [80, 95]
}

// ---------------------------------------------------------------------
// 3. ラムダ式と関数型
//
// ラムダ = 名前のない関数。JS のアロー関数に近い。
//
// JS:     const double = (x) => x * 2
// Kotlin: val double = { x: Int -> x * 2 }
//
//   { 引数 -> 本体 }       最後の式が、そのまま戻り値になる（return は書かない）
//
// 関数にも「型」がある。 (引数の型) -> 戻り値の型
//   (Int) -> Int           Int を受け取って Int を返す
//   (Int, Int) -> Int      Int を 2 つ受け取って Int を返す
//   () -> Unit             何も受け取らず、何も返さない
//
// Lemonade の onImageClick: () -> Unit は、この「関数の型」。
// ---------------------------------------------------------------------
private fun lambdas() {
    println("--- 3. lambda ---")

    val double = { x: Int -> x * 2 }
    println(double(5))                    // 10

    val triple: (Int) -> Int = { it * 3 } // 型を書けば、引数名を省略できる。引数が 1 つなら it が使える
    println(triple(5))                    // 15

    val add: (Int, Int) -> Int = { a, b -> a + b }
    println(add(2, 3))                    // 5

    val greet: () -> Unit = { println("hello") }
    greet()                               // 関数と同じように ( ) で呼ぶ
}

// ---------------------------------------------------------------------
// 4. 関数を引数にする関数（高階関数）と、末尾ラムダ
//
// 最後の引数が関数なら、ラムダを ( ) の外に出して書ける（末尾ラムダ）。
// 引数がそれだけなら、( ) も省ける。
//
//   applyTwice(3, { it + 1 })     ← 普通の書き方
//   applyTwice(3) { it + 1 }      ← 末尾ラムダ（こう書くのが普通）
//
// Compose の Column { ... } や Button(...) { ... } は、この書き方そのもの。
// ---------------------------------------------------------------------
private fun applyTwice(x: Int, f: (Int) -> Int): Int = f(f(x))

private fun runWithLog(title: String, action: () -> Unit) {
    println("start: $title")
    action()
    println("end: $title")
}

// Compose の Button に似せた関数。最後の引数 onClick が関数。
private fun button(label: String, onClick: () -> Unit) {
    println("[ $label ]")
    onClick()
}

private fun trailingLambdas() {
    println("--- 4. trailing lambda ---")

    println(applyTwice(3, { it + 1 }))    // 5
    println(applyTwice(3) { it * 10 })    // 300（末尾ラムダ）

    runWithLog("demo") {                  // action に { } の中身が渡る
        println("working...")
    }

    repeat(2) { println("repeat: $it") }  // レッスン 2 で出てきた repeat も、末尾ラムダだった

    button("OK") { println("clicked OK") }
    button(label = "Cancel", onClick = { println("clicked Cancel") })   // 名前付き引数でも書ける
}

// ---------------------------------------------------------------------
// 5. コレクションの操作: map / filter など
//
//   JS                          Kotlin
//   arr.map(x => x * 2)         list.map { it * 2 }
//   arr.filter(x => x > 1)      list.filter { it > 1 }
//   arr.forEach(x => ...)       list.forEach { ... }
//   arr.find(x => ...)          list.firstOrNull { ... }     ← 無ければ null
//   arr.some(x => ...)          list.any { ... }
//   arr.every(x => ...)         list.all { ... }
//   arr.reduce((a, b) => a+b)   list.sum() / list.sumOf { ... }
//   arr.join(", ")              list.joinToString(", ")
//
// ★ 名前の注意: list.map { } は「変換する操作」で、Map（キーと値の入れ物）とは別物。
// ---------------------------------------------------------------------
private data class Product(val name: String, val price: Int, val category: String)

private fun collectionOperations() {
    println("--- 5. collection operations ---")

    val prices = listOf(120, 80, 200, 50)
    println(prices.map { it * 2 })                 // [240, 160, 400, 100]
    println(prices.filter { it >= 100 })           // [120, 200]
    prices.forEach { println("price: $it") }
    println(prices.sum())                          // 450
    println(prices.maxOrNull())                    // 200（空のリストなら null）
    println(emptyList<Int>().maxOrNull())          // null
    println(prices.sorted())                       // [50, 80, 120, 200]
    println(prices.count { it > 100 })             // 2
    println(prices.any { it > 150 })               // true
    println(prices.all { it > 60 })                // false
    println(prices.firstOrNull { it < 60 })        // 50
    println(prices.firstOrNull { it > 1000 })      // null
    println(prices.take(2))                        // [120, 80]
    println(prices.joinToString(", "))      // 120, 80, 200, 50

    val products = listOf(
        Product("pen", 120, "stationery"),
        Product("notebook", 200, "stationery"),
        Product("apple", 80, "food"),
        Product("bread", 150, "food"),
    )

    // 操作はつなげて書ける（JS のメソッドチェーンと同じ）
    val names = products
        .filter { it.price >= 100 }                // 100 円以上に絞る
        .sortedBy { it.price }                     // 値段の安い順に並べる
        .map { it.name }                           // 名前だけ取り出す
    println(names)                                 // [pen, bread, notebook]

    println(products.sumOf { it.price })           // 550
    println(products.groupBy { it.category }.keys) // [stationery, food]

    // 引数が 1 つのラムダの it は、慣れると読みやすい。
    // 迷うときは名前を付ける: products.map { product -> product.name }
    println(products.map { product -> product.name })
}
