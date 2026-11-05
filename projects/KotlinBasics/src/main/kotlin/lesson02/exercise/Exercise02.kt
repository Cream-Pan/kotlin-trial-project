package lesson02.exercise

// =====================================================================
// Lesson 2 演習
//
// やること: TODO() になっている 5 つの関数を実装する。
//           main を実行して、すべて ✅ になれば完了。
//
// 今回のお願い: 動いたら、すぐ終わりにしない。
//   「自分で足したテストケース」を main に 2 つ以上追加して、
//   境界の値（ちょうどの値・0・負の数など）でも正しいか確かめること。
//   （レッスン 1 の firstChar のように、用意されたテストだけでは見つからない不具合がある）
// =====================================================================

// 問 1: when（対象を取らない形）
// 1 から数えて、次のルールで文字列を返す。
//   15 の倍数 → "FizzBuzz" / 3 の倍数 → "Fizz" / 5 の倍数 → "Buzz" / それ以外 → 数字そのもの
// 例: fizzBuzz(15) → "FizzBuzz" / fizzBuzz(9) → "Fizz" / fizzBuzz(7) → "7"
// ヒント: 倍数の判定は n % 3 == 0。when の条件は上から順に調べられる。
fun fizzBuzz(n: Int): String =
    when{
        n % 15 == 0 -> "FizzBuzz"
        n % 3 == 0 -> "Fizz"
        n % 5 == 0 -> "Buzz"
        else -> "$n"
    }

// 問 2: when（範囲）
// 点数から評価を返す。
//   90〜100 → "A" / 80〜89 → "B" / 60〜79 → "C" / 0〜59 → "D" / それ以外 → "不正"
// ヒント: in 90..100 -> のように範囲で書ける。
fun grade(score: Int): String =
    when(score){
        in 90..100 -> "A"
        in 80..89 -> "B"
        in 60..79 -> "C"
        in 0..59 -> "D"
        else -> "不正"
    }

// 問 3: デフォルト引数・名前付き引数・if 式
// 金額を文字列にする。
//   ・currency は省略すると "円"。
//   ・withTax が true のときは 10% 上乗せした金額にする（小数点以下は切り捨て）。
// 例: formatPrice(1000) → "1000円"
//     formatPrice(1000, withTax = true) → "1100円"
//     formatPrice(10, currency = "USD") → "10USD"
// ヒント: 整数どうしの割り算は切り捨てになる（レッスン 1 の「大きな違い①」）。
//         amount * 110 / 100 と書けば、小数を使わずに済む。
fun formatPrice(amount: Int, currency: String = "円", withTax: Boolean = false): String {
    val ans = if(withTax){
        amount * 110L / 100
    }
    else{
        amount
    }
    return "$ans$currency"
}

// 問 4: 範囲とループ（downTo）
// from から 1 まで数えて、最後に "Go!" を付けた文字列を返す。
// 例: countdown(3) → "3, 2, 1, Go!" / countdown(1) → "1, Go!" / countdown(0) → "Go!"
// ヒント: for (i in from downTo 1) { ... }。文字列は var で用意して += で足していける。
fun countdown(from: Int): String {
    var str = ""
    for(i in from downTo 1){
        str += "${i.toString()}, "
    }
    str += "Go!"
    return str
}

// 問 5（発展）: 範囲とループ（until）と条件
// 1 以上 limit 未満の整数のうち、3 または 5 の倍数の合計を返す。
// 例: sumOfMultiples(10) → 23（3 + 5 + 6 + 9）/ sumOfMultiples(1) → 0
// ヒント: 1 until limit は limit を含まない。3 の倍数かつ 5 の倍数の数を二重に足さないこと。
fun sumOfMultiples(limit: Int): Int {
    var sum = 0
    for(i in 1 until limit){
        when{
            i % 5 == 0 -> sum += i
            i % 3 == 0 -> sum += i
        }
    }
    return sum
}

// ---------------------------------------------------------------------
// ここから下は採点用。
// 上の「今回のお願い」のとおり、check を自分で足してよい（既存の行は変えない）。
// ---------------------------------------------------------------------
fun main() {
    check("問1 fizzBuzz(15)", "FizzBuzz") { fizzBuzz(15) }
    check("問1 fizzBuzz(9)", "Fizz") { fizzBuzz(9) }
    check("問1 fizzBuzz(10)", "Buzz") { fizzBuzz(10) }
    check("問1 fizzBuzz(7)", "7") { fizzBuzz(7) }

    check("問2 grade(95)", "A") { grade(95) }
    check("問2 grade(85)", "B") { grade(85) }
    check("問2 grade(60)", "C") { grade(60) }
    check("問2 grade(10)", "D") { grade(10) }
    check("問2 grade(101)", "不正") { grade(101) }

    check("問3 formatPrice(1000)", "1000円") { formatPrice(1000) }
    check("問3 formatPrice(1000, withTax = true)", "1100円") { formatPrice(1000, withTax = true) }
    check("問3 formatPrice(10, currency = USD)", "10USD") { formatPrice(10, currency = "USD") }

    check("問4 countdown(3)", "3, 2, 1, Go!") { countdown(3) }
    check("問4 countdown(1)", "1, Go!") { countdown(1) }

    check("問5 sumOfMultiples(10)", 23) { sumOfMultiples(10) }
    check("問5 sumOfMultiples(1)", 0) { sumOfMultiples(1) }

    // ↓ ここに、自分で考えたテストケースを 2 つ以上足す
    check("問6 grade(-10)", "不正") { grade(-10) }
    check("問6 sumOfMultiples(-10)", 0) { sumOfMultiples(-10) }
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
