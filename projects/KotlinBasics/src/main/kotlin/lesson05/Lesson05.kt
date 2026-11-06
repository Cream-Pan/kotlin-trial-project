package lesson05

// =====================================================================
// Lesson 5: Compose への橋渡し
//
// 準備: 先に MiniUi.kt を開いて、上から下まで読む（100 行ほど）。
//       これは、Compose の「書き方」と「仕組み」を体験するための、小さな UI ライブラリ。
//
// 使い方: ▶ で実行し、出力を見ながら、コードの値や構造を書き換えて試す。
//
// ゴール: このレッスンを終えると、Lemonade のコードの、次のような部分が
//         「なぜこう書けるのか」を、自分の言葉で説明できるようになる。
//
//   Scaffold(topBar = { ... }) { innerPadding ->      ← 名前付き引数 + 末尾ラムダ
//       Surface(modifier = Modifier.fillMaxSize()) {  ← Modifier の連鎖
//           when (currentStep) { ... }                ← 状態で表示が変わる
//       }
//   }
//   var currentStep by remember { mutableStateOf(1) } ← 状態を覚えておく
// =====================================================================

fun main() {
    nestedCalls()
    lambdaWithParameter()
    modifierChain()
    ownComponents()
    stateAndRerender()
    whyRemember()
}

// ---------------------------------------------------------------------
// 1. { } の入れ子 = 関数呼び出しの入れ子
//
// column { ... } は、特別な構文ではない。
// 「column という関数に、最後の引数として、ラムダを渡している」だけ。
//
//   column {               ← column(content = { ... }) と同じ
//       text("Hello")
//   }
//
// column の中で content() が呼ばれたとき、{ } の中身（text(...) など）が実行される。
// ---------------------------------------------------------------------
private fun nestedCalls() {
    println("--- 1. nested calls ---")

    Ui.setContent {
        column {
            text("Hello")
            row {
                text("A")
                text("B")
            }
        }
    }

    // 上と同じ意味を、末尾ラムダを使わずに書くと、こうなる。
    println("(末尾ラムダを使わない書き方)")
    Ui.setContent(content = {
        column(content = {
            text(content = "Hello")
        })
    })
}

// ---------------------------------------------------------------------
// 2. 引数を受け取るラムダ
//
// Lemonade の Scaffold { innerPadding -> ... } の「innerPadding ->」は、
// 「この関数から、値を 1 つ渡してもらう」という意味。
// ---------------------------------------------------------------------
private fun withPadding(content: (Int) -> Unit) {     // content は「Int を受け取る関数」
    content(16)                                       // 16 を渡して呼び出す
}

private fun lambdaWithParameter() {
    println("--- 2. lambda with a parameter ---")

    withPadding { padding ->                          // padding には 16 が入る
        println("渡された padding は $padding")
    }
}

// ---------------------------------------------------------------------
// 3. Modifier の連鎖
//
// Modifier.padding(8).fillMaxWidth() は、
// 「padding(8) の結果の Modifier」に対して「fillMaxWidth()」を呼んでいる。
// （MiniUi.kt の Modifier を読むと、新しい Modifier を返しているだけと分かる）
//
// 引数 modifier に、デフォルト値 Modifier が付いている。
// だから、指定しないときは省略できる（レッスン 2 のデフォルト引数）。
// ---------------------------------------------------------------------
private fun modifierChain() {
    println("--- 3. modifier chain ---")

    Ui.setContent {
        text("Plain")
        text("Padded", modifier = Modifier.padding(8))
        text("Chained", modifier = Modifier.padding(8).fillMaxWidth())
        column(modifier = Modifier.padding(16)) {
            text("In a column")
        }
    }
}

// ---------------------------------------------------------------------
// 4. 自分で UI 部品を作る
//
// 「Composable 関数」の正体は、「UI 部品の関数を呼ぶだけの、普通の関数」。
// 引数でデータを受け取り、if や for で中身を変えられる。
// 最後の引数を関数にすれば、自分の column { } のような入れ物も作れる。
// ---------------------------------------------------------------------
private fun profileCard(name: String, age: Int) {
    column(modifier = Modifier.padding(16)) {
        text(name)
        text("$age 歳")
        if (age >= 20) {                              // if で、表示するかどうかを切り替えられる
            text("成人")
        }
    }
}

private fun card(title: String, content: () -> Unit) {   // 自作の入れ物。content は呼び出し側が書く
    column {
        text(title)
        content()
    }
}

private fun ownComponents() {
    println("--- 4. own components ---")

    Ui.setContent {
        profileCard("Taro", 20)
        card("Members") {                             // 自作の card を、末尾ラムダで使う
            profileCard("Hanako", 30)
            profileCard("Jiro", 15)
        }
    }
}

// ---------------------------------------------------------------------
// 5. 状態と再実行: UI は「状態の関数」
//
//   画面 = f(状態)
//
//   ① 画面を表す関数が、今の状態を見て、画面を作る。
//   ② ボタンが押されると、onClick が「状態」を書き換える。
//   ③ フレームワークが、画面を表す関数を「もう一度」実行する。
//   ④ 新しい状態で、画面が作り直される。
//
// Lemonade の currentStep も同じ。
//   currentStep が変わる → LemonadeApp() が再実行 → when が別の枝を選ぶ
// ---------------------------------------------------------------------
private class CounterApp {
    private var count = 0              // 状態は、画面を表す関数の「外」に持つ

    fun content() {
        column {
            text("Count: $count")
            button("+1") { count++ }   // onClick は、状態を書き換える関数
            button("Reset") { count = 0 }
        }
    }
}

private fun stateAndRerender() {
    println("--- 5. state and rerender ---")

    val app = CounterApp()
    Ui.setContent { app.content() }

    Ui.click("+1")                     // 押す → count++ → 画面を再実行
    Ui.click("+1")
    Ui.click("Reset")
}

// ---------------------------------------------------------------------
// 6. なぜ remember が必要か
//
// 画面を表す関数は、何度も実行される。
// 関数の中の var は、実行のたびに、最初の値に戻ってしまう。
// 状態を「覚えておく」場所が、別に要る。
//
//   Compose:   var count by remember { mutableStateOf(0) }
//              ↑ 「再実行されても、この値を覚えておく場所」
//   ここ:      CounterApp の private var count（関数の外に持つ）
// ---------------------------------------------------------------------
private fun brokenCounter() {
    var count = 0                      // ← 実行のたびに 0 に戻る
    column {
        text("Count: $count")
        button("+1") { count++ }       // count は 1 になるが……
    }
}

private fun whyRemember() {
    println("--- 6. why remember ---")

    Ui.setContent { brokenCounter() }
    Ui.click("+1")                     // 再実行で、var count = 0 に戻る → 画面はずっと 0
    Ui.click("+1")

    // ★ 試してみよう: CounterApp の count を、content() の中の var に移して実行すると、
    //   上と同じことが起きる。
}
