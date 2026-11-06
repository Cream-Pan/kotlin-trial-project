package lesson05.exercise

import lesson05.Ui
import lesson05.button
import lesson05.column
import lesson05.text

// =====================================================================
// Lesson 5 演習
//
// やること: TODO() になっている部分を実装する。
//           main を実行して、すべて ✅ になれば完了。
//
// 準備: MiniUi.kt の text / button / column と、Lesson05.kt の「4. 自分で UI 部品を作る」
//       「5. 状態と再実行」を読み返してから始める。
//
// 採点の見方: 各問は「画面がこうなる」という行のリストと比べる。
//   例: greeting("Taro") のとき
//       Column
//         Text("Taro")
//   （入れ子の深さは、半角スペース 2 つ）
//
// 今回も: main の末尾に「自分で考えたテストケース」を 2 つ以上足すこと。
//   ・問 2: 名前が空文字のとき、18 歳ちょうどのとき、など
//   ・問 3: 要素が 1 つだけのとき
//   ・問 5: + と - を混ぜて押したとき
// =====================================================================

// ---------------------------------------------------------------------
// 問 1: UI 部品を関数にする
// column の中に text を 1 つ置いて、"Hello, 名前!" を表示する。
// 例: greeting("Taro") →
//       Column
//         Text("Hello, Taro!")
// ヒント: column { ... } の中で、text("...") を呼ぶ。文字列テンプレートも使う。
// ---------------------------------------------------------------------
fun greeting(name: String) {
    TODO("column の中で text を呼ぶ")
}
// 試してみよう: column { text("x") } の { } を ( ) に書き換えて column(text("x")) にするとどうなる？

// ---------------------------------------------------------------------
// 問 2: if で表示を切り替える
// 名前と年齢を表示する。isAdmin が true のときだけ、最後に "★ Admin" を足す。
// 例: userCard("Taro", 20) →
//       Column
//         Text("Taro")
//         Text("20 歳")
//     userCard("Hanako", 17, isAdmin = true) →
//       Column
//         Text("Hanako")
//         Text("17 歳")
//         Text("★ Admin")
// ヒント: 年齢の text は "$age 歳"。isAdmin はデフォルト値が false。
// ---------------------------------------------------------------------
fun userCard(name: String, age: Int, isAdmin: Boolean = false) {
    TODO("column の中で text を 2 つ、if で 3 つ目")
}

// ---------------------------------------------------------------------
// 問 3: for で繰り返す
// 文字列のリストを、"• 項目" の形で縦に並べる。リストが空なら "(なし)" を 1 つ表示する。
// 例: itemList(listOf("a", "b")) →
//       Column
//         Text("• a")
//         Text("• b")
//     itemList(listOf()) →
//       Column
//         Text("(なし)")
// ヒント: for (item in items) か items.forEach { ... }。空かどうかは items.isEmpty()。
// ---------------------------------------------------------------------
fun itemList(items: List<String>) {
    TODO("column の中で、空なら (なし)、そうでなければ for で並べる")
}

// ---------------------------------------------------------------------
// 問 4: 自分の入れ物を作る（末尾ラムダ）
// タイトルを text で表示し、その下に、呼び出し側が書いた中身を並べる入れ物。
// 例: card("Profile") { text("Taro") } →
//       Column
//         Text("Profile")
//         Text("Taro")
// 入れ子にもできる: card("A") { card("B") { text("x") } } →
//       Column
//         Text("A")
//         Column
//           Text("B")
//           Text("x")
// ヒント: 引数 content は「関数」。呼ぶには content()。
//         column の中で、先に text(title)、そのあとで content() を呼ぶ。
// ---------------------------------------------------------------------
fun card(title: String, content: () -> Unit) {
    TODO("column の中で text(title) と content()")
}

// ---------------------------------------------------------------------
// 問 5（発展）: 状態の持ち上げ（state hoisting）
// 画面を、「見た目だけ（状態を持たない）」と「状態を持つ側」に分ける。
// これは、公式コース Unit 2「UI と状態」で学ぶ、Compose の重要な設計。
//
// (a) counterScreen: 状態を持たない。count と、2 つの「押されたとき」の関数を、引数でもらう。
//       counterScreen(3, onIncrement, onDecrement) →
//         Column
//           Text("Count: 3")
//           Button("+")
//           Button("-")
//     "+" を押したら onIncrement、"-" を押したら onDecrement を呼ぶ。
//     ヒント: button(ラベル, 関数) の 2 つ目は、関数そのもの。引数のまま渡してよい。
//
// (b) CounterApp: 状態（count）を持つ。content() で counterScreen を呼ぶ。
//     "+" で 1 増え、"-" で 1 減る。ただし、0 未満にはならない。
//     ヒント: counterScreen(count, onIncrement = { ... }, onDecrement = { ... })
// ---------------------------------------------------------------------
fun counterScreen(count: Int, onIncrement: () -> Unit, onDecrement: () -> Unit) {
    TODO("column の中で text と button を 2 つ")
}

class CounterApp {
    private var count = 0

    fun content() {
        TODO("counterScreen に count と、count を変える関数を渡す")
    }
}
// 試してみよう: private var count = 0 を content() の中に移すとどうなる？（Lesson05 の「6」と同じ）

// ---------------------------------------------------------------------
// ここから下は採点用。
// 上の「今回も」のとおり、check を自分で足してよい（既存の行は変えない）。
// ---------------------------------------------------------------------
private fun lines(vararg rows: String): List<String> = rows.toList()

private fun counterLines(count: Int): List<String> =
    lines("Column", "  Text(\"Count: $count\")", "  Button(\"+\")", "  Button(\"-\")")

fun main() {
    Ui.echo = false     // 採点中は、画面の出力を表示しない

    check("問1 greeting(Taro)", lines("Column", "  Text(\"Hello, Taro!\")")) {
        Ui.capture { greeting("Taro") }
    }
    check("問1 greeting(空文字)", lines("Column", "  Text(\"Hello, !\")")) {
        Ui.capture { greeting("") }
    }

    check("問2 userCard(Taro, 20)", lines("Column", "  Text(\"Taro\")", "  Text(\"20 歳\")")) {
        Ui.capture { userCard("Taro", 20) }
    }
    check(
        "問2 userCard(Hanako, 17, isAdmin)",
        lines("Column", "  Text(\"Hanako\")", "  Text(\"17 歳\")", "  Text(\"★ Admin\")"),
    ) {
        Ui.capture { userCard("Hanako", 17, isAdmin = true) }
    }

    check("問3 itemList(a, b)", lines("Column", "  Text(\"• a\")", "  Text(\"• b\")")) {
        Ui.capture { itemList(listOf("a", "b")) }
    }
    check("問3 itemList(空)", lines("Column", "  Text(\"(なし)\")")) {
        Ui.capture { itemList(emptyList()) }
    }

    check("問4 card", lines("Column", "  Text(\"Profile\")", "  Text(\"Taro\")")) {
        Ui.capture { card("Profile") { text("Taro") } }
    }
    check(
        "問4 card の入れ子",
        lines("Column", "  Text(\"A\")", "  Column", "    Text(\"B\")", "    Text(\"x\")"),
    ) {
        Ui.capture { card("A") { card("B") { text("x") } } }
    }

    check("問5 counterScreen の見た目", counterLines(3)) {
        Ui.capture { counterScreen(3, {}, {}) }
    }
    check("問5 + で onIncrement が呼ばれる", 1) {
        var called = 0
        Ui.setContent { counterScreen(0, { called++ }, {}) }
        Ui.click("+")
        called
    }
    check("問5 - で onDecrement が呼ばれる", 1) {
        var called = 0
        Ui.setContent { counterScreen(0, {}, { called++ }) }
        Ui.click("-")
        called
    }
    check("問5 CounterApp: + を 2 回", counterLines(2)) {
        val app = CounterApp()
        Ui.setContent { app.content() }
        Ui.click("+")
        Ui.click("+")
        Ui.lines.toList()
    }
    check("問5 CounterApp: 0 のとき -", counterLines(0)) {
        val app = CounterApp()
        Ui.setContent { app.content() }
        Ui.click("-")
        Ui.lines.toList()
    }
    check("問5 CounterApp: + のあと -", counterLines(0)) {
        val app = CounterApp()
        Ui.setContent { app.content() }
        Ui.click("+")
        Ui.click("-")
        Ui.lines.toList()
    }

    // ↓ ここに、自分で考えたテストケースを 2 つ以上足す


}

private fun check(label: String, expected: Any?, actual: () -> Any?) {
    val result = try {
        actual()
    } catch (e: NotImplementedError) {
        println("⏳ $label: 未実装")
        return
    } catch (e: Exception) {
        println("💥 $label: 例外 ${e::class.simpleName}: ${e.message}")
        return
    }
    if (result == expected) {
        println("✅ $label")
    } else {
        println("❌ $label")
        println("   期待値=$expected")
        println("   実際  =$result")
    }
}
