package lesson03.exercise

// =====================================================================
// Lesson 3 演習
//
// やること: TODO() になっている部分を実装する。
//           main を実行して、すべて ✅ になれば完了。
//
// 今回も: 動いたら、すぐ終わりにしない。
//   main の末尾に「自分で考えたテストケース」を 2 つ以上足すこと。
//   各問の「境界」を考える。例えば、
//     ・問 2: 0 のときに decrement したらどうなるか
//     ・問 4: 4 回回すと元に戻るか
//
// 最後のおまけ: 各問の下の「試してみよう」を、実際にコメントを外して
//   コンパイルエラーを 1 度見てから、またコメントに戻すこと。
//   エラーの文面を読むのも、Kotlin に慣れる練習になる。
// =====================================================================

// ---------------------------------------------------------------------
// 問 1: クラスとプロパティ
// 長方形クラス。
//   area()     → 面積（幅 × 高さ）
//   isSquare   → 幅と高さが等しければ true
// 例: Rectangle(3, 4).area() → 12 / Rectangle(5, 5).isSquare → true
// ヒント: isSquare は「関数」ではなく「プロパティ」。呼ぶときに ( ) が付かない。
// ---------------------------------------------------------------------
class Rectangle(val width: Int, val height: Int) {
    fun area(): Int = TODO("幅 × 高さ")

    val isSquare: Boolean
        get() = TODO("幅と高さが等しいか")
}
// 試してみよう: Rectangle(3, 4).width = 10 と書くとどうなる？（val の理由を確かめる）

// ---------------------------------------------------------------------
// 問 2: private set と「0 未満にしない」
// カウンター。count は外から読めるが、書き換えはクラスの中だけ。
//   increment() → 1 増やす
//   decrement() → 1 減らす。ただし 0 より小さくはしない
//   reset()     → 0 に戻す
// 例: increment を 2 回 → count は 2 / 0 のとき decrement → count は 0 のまま
// ---------------------------------------------------------------------
class Counter {
    var count = 0
        private set

    fun increment() {
        TODO("count を 1 増やす")
    }

    fun decrement() {
        TODO("count を 1 減らす。ただし 0 未満にしない")
    }

    fun reset() {
        TODO("count を 0 に戻す")
    }
}
// 試してみよう: Counter().count = 5 と書くとどうなる？（private set の理由を確かめる）

// ---------------------------------------------------------------------
// 問 3: data class と copy
// 買い物かごの 1 品目。（クラスの宣言はすでにある）
//   total(item)   → 値段 × 個数
//   doubled(item) → 個数を 2 倍にした「新しい Item」を返す。元の item は変えない
// 例: total(Item("apple", 100, 3)) → 300
//     doubled(Item("apple", 100, 3)) → Item("apple", 100, 6)
// ヒント: copy を使う。data class なので、== は中身を比べてくれる。
// ---------------------------------------------------------------------
data class Item(val name: String, val price: Int, val quantity: Int = 1)

fun total(item: Item): Int {
    TODO("値段 × 個数")
}

fun doubled(item: Item): Item {
    TODO("copy で個数だけを変える")
}
// 試してみよう: val item = Item("pen", 50); item.quantity = 3 と書くとどうなる？

// ---------------------------------------------------------------------
// 問 4: enum と when（else なし）
// 方向を表す enum と、座標を表す data class。（宣言はすでにある）
//   turnRight(d)   → 右に 90 度回した方向（UP → RIGHT → DOWN → LEFT → UP）
//   move(p, d)     → p を方向 d に 1 マス動かした、新しい Point
// 例: turnRight(Direction.UP) → Direction.RIGHT
//     move(Point(1, 1), Direction.UP) → Point(1, 0)
// ヒント: turnRight は when (d) で、else を書かずに 4 つの場合を全部書く。
//         move は d.dx と d.dy を使う。
// ---------------------------------------------------------------------
enum class Direction(val dx: Int, val dy: Int) {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
}

data class Point(val x: Int, val y: Int)

fun turnRight(d: Direction): Direction {
    TODO("when (d) で 4 つの場合を全部書く")
}

fun move(p: Point, d: Direction): Point {
    TODO("d.dx と d.dy を足した新しい Point")
}
// 試してみよう: turnRight の when から DOWN の行を 1 つ消すとどうなる？

// ---------------------------------------------------------------------
// 問 5（発展）: interface とポリモーフィズム
// 図形。円周率は 3.14 とする。（interface の宣言はすでにある）
//   Square(side).area() → side × side
//   Circle(r).area()    → 3.14 × r × r
//   totalArea(a, b)     → 2 つの図形の面積の合計（Square でも Circle でも受け取れる）
// 例: Square(3.0).area() → 9.0 / Circle(2.0).area() → 12.56
//     totalArea(Square(2.0), Square(3.0)) → 13.0
// ヒント: totalArea の引数の型は、Square でも Circle でもなく Shape にする。
// ---------------------------------------------------------------------
interface Shape {
    fun area(): Double
}

class Square(val side: Double) : Shape {
    override fun area(): Double = TODO("side × side")
}

class Circle(val radius: Double) : Shape {
    override fun area(): Double = TODO("3.14 × radius × radius")
}

fun totalArea(a: Shape, b: Shape): Double {
    TODO("2 つの面積を足す")
}

// ---------------------------------------------------------------------
// ここから下は採点用。
// 上の「今回も」のとおり、check を自分で足してよい（既存の行は変えない）。
// ---------------------------------------------------------------------
fun main() {
    check("問1 Rectangle(3, 4).area()", 12) { Rectangle(3, 4).area() }
    check("問1 Rectangle(5, 5).isSquare", true) { Rectangle(5, 5).isSquare }
    check("問1 Rectangle(3, 4).isSquare", false) { Rectangle(3, 4).isSquare }

    check("問2 increment を 2 回", 2) {
        val c = Counter()
        c.increment()
        c.increment()
        c.count
    }
    check("問2 0 のとき decrement", 0) {
        val c = Counter()
        c.decrement()
        c.count
    }
    check("問2 increment → decrement", 0) {
        val c = Counter()
        c.increment()
        c.decrement()
        c.count
    }
    check("問2 reset", 0) {
        val c = Counter()
        c.increment()
        c.increment()
        c.reset()
        c.count
    }

    check("問3 total(apple, 100, 3)", 300) { total(Item("apple", 100, 3)) }
    check("問3 total(pen, 50)", 50) { total(Item("pen", 50)) }
    check("問3 doubled", Item("apple", 100, 6)) { doubled(Item("apple", 100, 3)) }
    check("問3 doubled は元を変えない", 3) {
        val original = Item("apple", 100, 3)
        doubled(original)
        original.quantity
    }

    check("問4 turnRight(UP)", Direction.RIGHT) { turnRight(Direction.UP) }
    check("問4 turnRight(RIGHT)", Direction.DOWN) { turnRight(Direction.RIGHT) }
    check("問4 turnRight(DOWN)", Direction.LEFT) { turnRight(Direction.DOWN) }
    check("問4 turnRight(LEFT)", Direction.UP) { turnRight(Direction.LEFT) }
    check("問4 move(Point(1, 1), UP)", Point(1, 0)) { move(Point(1, 1), Direction.UP) }
    check("問4 move(Point(0, 0), LEFT)", Point(-1, 0)) { move(Point(0, 0), Direction.LEFT) }

    check("問5 Square(3.0).area()", 9.0) { Square(3.0).area() }
    check("問5 Circle(2.0).area()", 12.56) { Circle(2.0).area() }
    check("問5 totalArea", 13.0) { totalArea(Square(2.0), Square(3.0)) }

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
