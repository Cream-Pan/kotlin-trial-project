<div id="top"></div>

# Kotlin Basics

Kotlin Basics は，Web 開発（HTML / CSS / JavaScript）の経験を土台に，Kotlin の言語仕様を学ぶための教材プロジェクトである．Android ではなく素の Kotlin（JVM）で作っているため，エミュレータなしで `main` 関数をその場で実行できる．

---

## 目次

- [進め方](#進め方)
- [レッスン一覧](#レッスン一覧)
- [ファイル構成](#ファイル構成)
- [開き方と実行方法](#開き方と実行方法)
- [JavaScript との対応早見表](#javascript-との対応早見表)

---

## 進め方

1. レッスンのファイル（`LessonXX.kt`）を読み，▶ で実行して結果を確認する．値を書き換えて挙動を試す．
2. 演習のファイル（`exercise/ExerciseXX.kt`）の `TODO()` を自分で実装する．
3. 演習の `main` を実行し，すべて ✅ になれば完了．詰まったらヒントを読み，それでも分からなければ質問する．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## レッスン一覧

| # | テーマ | 内容 | 状態 |
|---|---|---|---|
| 1 | 変数・型・null 安全 | `val` / `var`，Int と Double の違い，文字列テンプレート，`?.` `?:` | 完了 |
| 2 | 関数・条件式 | 関数，デフォルト引数，名前付き引数，式としての `if` / `when`，範囲と `for` | 演習待ち |
| 3 | クラスとデータ | `class`，コンストラクタ，`data class`，`enum`，`interface`，`object` | 未着手 |
| 4 | コレクションとラムダ | `List` / `Map`，`map` / `filter`，ラムダ式，関数型，末尾ラムダ | 未着手 |
| 5 | Compose への橋渡し | 名前付き引数・デフォルト引数・末尾ラムダで，`Column { Text("...") }` 風の書き方を素の Kotlin で自作する | 未着手 |
| 6 | 拡張関数・スコープ関数・ジェネリクス | 拡張関数，`let` / `apply`，ジェネリクス（公式コース Unit 3 の Kotlin 部分に相当．必要になる直前に追加） | 後日 |
| 7 | コルーチン入門 | `suspend`，`launch`，`async`（JS の `async/await` との対比．XR Codelab Part 2 の前に追加） | 後日 |

全体の学習計画は，リポジトリルートの [ROADMAP.md](../../ROADMAP.md) を参照．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## ファイル構成

```text
KotlinBasics/
├── build.gradle.kts
├── settings.gradle.kts
└── src/main/kotlin/
    ├── lesson01/
    │   ├── Lesson01.kt            解説つきのサンプル（実行して読む）
    │   └── exercise/
    │       └── Exercise01.kt      演習（TODO を実装する）
    └── lesson02/
        ├── Lesson02.kt
        └── exercise/
            └── Exercise02.kt
```

レッスンごとにパッケージ（`lesson01`，`lesson02`，…）を分けている．同じパッケージに `main` を 2 つ置くことはできないためである．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 開き方と実行方法

1. Android Studio の `File > Open` で，このフォルダ（`projects/KotlinBasics`）を選ぶ．
2. Gradle の同期が終わるまで待つ．
3. `Lesson01.kt` を開き，`fun main()` の行の左にある ▶ を押して実行する．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## JavaScript との対応早見表

| JavaScript | Kotlin |
|---|---|
| `const x = 1` | `val x = 1` |
| `let x = 1` | `var x = 1` |
| `` `Hello, ${name}` `` | `"Hello, $name"` / `"a+b=${a + b}"` |
| `a?.b` | `a?.b` |
| `a ?? b` | `a ?: b` |
| `switch` | `when` |
| `number`（1 種類） | `Int` / `Long` / `Double` など（区別あり，自動変換なし） |

<p align="right">(<a href="#top">トップへ戻る</a>)</p>
