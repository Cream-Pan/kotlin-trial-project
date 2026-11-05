package lesson03

// =====================================================================
// Lesson 3: クラスとデータ
//
// 使い方: これまでと同じ。▶ で実行し、値を書き換えて結果の変化を試す。
// Compose では「状態を持つ入れ物（data class）」「段階を表す種類（enum）」
// 「他のクラスを継承した画面（class MainActivity : ComponentActivity()）」が
// 当たり前に出てくる。このレッスンはその下準備。
// =====================================================================

fun main() {
    classBasics()
    propertiesAndState()
    dataClasses()
    enums()
    inheritanceAndInterfaces()
    objects()
}

// ---------------------------------------------------------------------
// 1. クラスの基本
//
// JS:     class Dice { constructor(sides) { this.sides = sides } roll() {...} }
//         const dice = new Dice(6)
// Kotlin: class Dice(val sides: Int) { fun roll() = ... }
//         val dice = Dice(6)         ← new は要らない
//
// ・クラス名の直後の ( ) が「主コンストラクタ」。
// ・引数に val / var を付けると、そのままプロパティになる（this.sides = sides が不要）。
// ・val / var を付けない引数は、コンストラクタの中でだけ使える。
//
// DiceRoller の「サイコロ」をクラスにするなら、こうなる。
// ---------------------------------------------------------------------
private class Dice(val sides: Int) {
    fun roll(): Int = (1..sides).random()
}

private class Greeter(name: String) {      // val が無い → 外からは name を読めない
    private val message = "Hello, $name!"  // private: このクラスの中でだけ使える

    init {                                  // 生成されるときに 1 回だけ実行される
        println("Greeter created for $name")
    }

    fun greet() = println(message)
}

private fun classBasics() {
    println("--- 1. class basics ---")

    val dice = Dice(6)                       // new は不要
    println("sides = ${dice.sides}")
    println("roll は 1..6 に収まる？ ${dice.roll() in 1..6}")
    // dice.sides = 20                       // ← エラー。val なので再代入できない

    val greeter = Greeter("Taro")
    greeter.greet()
    // println(greeter.message)              // ← エラー。private は外から見えない
}

// ---------------------------------------------------------------------
// 2. プロパティと「勝手に書き換えさせない」工夫
// ---------------------------------------------------------------------
private class Temperature(var celsius: Double) {
    // 計算で求まるプロパティ。値は保存せず、読むたびに計算される。
    val fahrenheit: Double
        get() = celsius * 9 / 5 + 32
}

private class Counter {
    var count = 0
        private set                          // 読むのは誰でもできる。書き換えはこのクラスの中だけ

    fun increment() {
        count++
    }
}

private fun propertiesAndState() {
    println("--- 2. properties ---")

    val temperature = Temperature(100.0)
    println(temperature.fahrenheit)          // 212.0
    temperature.celsius = 0.0
    println(temperature.fahrenheit)          // 32.0（celsius を変えると、自動で変わる）

    val counter = Counter()
    counter.increment()
    counter.increment()
    println(counter.count)                   // 2
    // counter.count = 100                   // ← エラー。private set なので外から書き換えられない

    // 「値は読めるが、変更は決まった関数を通してしかできない」という設計は、
    // Unit 4 で学ぶ ViewModel の基本形になる。
}

// ---------------------------------------------------------------------
// 3. data class: データを入れるための専用クラス
//
// 主コンストラクタの引数から、次のものを自動で作ってくれる。
//   ・toString()   中身が読める形で表示できる
//   ・equals()     中身が同じなら == が true になる
//   ・copy()       一部だけ変えた「新しいコピー」を作る
//
// JS:     const next = { ...user, age: 21 }
// Kotlin: val next = user.copy(age = 21)
//
// Compose では、画面の状態（UiState）を data class で表すのが定番。
// ---------------------------------------------------------------------
private data class User(val name: String, val age: Int = 20)

private class Plain(val name: String)         // 比較用の、普通のクラス

private fun dataClasses() {
    println("--- 3. data class ---")

    val taro = User("Taro")                   // age はデフォルト値の 20
    println(taro)                             // User(name=Taro, age=20)

    val older = taro.copy(age = 21)           // 元の taro は変わらない
    println("$taro / $older")

    println(taro == User("Taro"))             // true  （中身が同じ）
    println(Plain("Taro") == Plain("Taro"))   // false （普通のクラスは「同じ物か」を比べる）

    val (name, age) = older                   // 分割代入（JS の const {name, age} = older）
    println("$name は $age 歳")
}

// ---------------------------------------------------------------------
// 4. enum class: 「決まった選択肢」を表す
//
// Lemonade の currentStep は Int で 1〜4 を持っていた。
// 「5 や 0 が入ってしまう」「1 が何を意味するか分からない」という弱点がある。
// enum なら、選択肢そのものを型にできる。
// ---------------------------------------------------------------------
private enum class Step(val label: String) {
    TREE("レモンの木をタップ"),
    SQUEEZE("レモンをしぼる"),
    DRINK("レモネードを飲む"),
    RESTART("もう一度はじめる"),
}

// enum の when は、全部の選択肢を書けば else が要らない。
// 選択肢を後から増やすと、書き忘れた when がコンパイルエラーで見つかる。
private fun next(step: Step): Step = when (step) {
    Step.TREE -> Step.SQUEEZE
    Step.SQUEEZE -> Step.DRINK
    Step.DRINK -> Step.RESTART
    Step.RESTART -> Step.TREE
}

private fun enums() {
    println("--- 4. enum ---")

    var step = Step.TREE
    for (i in 1..5) {
        println("${step.name}(${step.ordinal}): ${step.label}")   // name は名前，ordinal は 0 始まりの番号
        step = next(step)
    }

    println(Step.entries)                     // すべての選択肢の一覧
}

// ---------------------------------------------------------------------
// 5. 継承とインターフェース
//
// ・Kotlin のクラスは、デフォルトで「継承できない」（JS と逆）。
//   継承されるクラスには open、上書きされる関数にも open が必要。
// ・継承元の呼び出しは「: 親クラス(引数)」。上書きは override を付ける。
//
// Lemonade の MainActivity がまさにこれ。
//   class MainActivity : ComponentActivity() {
//       override fun onCreate(savedInstanceState: Bundle?) { ... }
//   }
// ---------------------------------------------------------------------
private open class Animal(val name: String) {
    open fun sound() = "..."
    fun intro() = "$name: ${sound()}"
}

private class Dog(name: String) : Animal(name) {
    override fun sound() = "Woof"
}

private class Cat(name: String) : Animal(name) {
    override fun sound() = "Meow"
}

// interface: 「この関数を持つ」という約束だけを決める。実装は持たない。
private interface Describable {
    fun describe(): String
}

private class Book(val title: String) : Describable {
    override fun describe() = "本: $title"
}

private class Movie(val title: String, val minutes: Int) : Describable {
    override fun describe() = "映画: $title（${minutes}分）"
}

// Describable であれば、Book でも Movie でも受け取れる（ポリモーフィズム）
private fun show(item: Describable) = println(item.describe())

private fun inheritanceAndInterfaces() {
    println("--- 5. inheritance / interface ---")

    println(Dog("Pochi").intro())             // Pochi: Woof
    println(Cat("Tama").intro())              // Tama: Meow

    show(Book("Kotlin 入門"))
    show(Movie("Matrix", 136))
}

// ---------------------------------------------------------------------
// 6. object と companion object
//
// ・object: インスタンスが 1 つだけ（シングルトン）。new も ( ) も要らない。
// ・companion object: クラスに属する「静的な」メンバー（JS の static に近い）。
// ---------------------------------------------------------------------
private object Logger {
    private var count = 0

    fun log(message: String) {
        count++
        println("[LOG #$count] $message")
    }
}

private class AppInfo {
    companion object {
        const val NAME = "KotlinBasics"       // const: コンパイル時に決まる定数
        fun create() = AppInfo()
    }
}

private fun objects() {
    println("--- 6. object ---")

    Logger.log("start")
    Logger.log("running")                     // 同じ Logger なので、番号が増える

    println(AppInfo.NAME)                     // インスタンスを作らずに参照できる
    AppInfo.create()
}
