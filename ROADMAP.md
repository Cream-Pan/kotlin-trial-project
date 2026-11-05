<div id="top"></div>

# 学習ロードマップ

Web 開発（HTML / CSS / JavaScript）の経験を土台に，Kotlin の基礎から Android XR 開発までを順に学ぶための計画である．進捗に合わせて随時更新する．

---

## 目次

- [全体像](#全体像)
- [フェーズ 1: Kotlin の基礎（自作教材）](#フェーズ-1-kotlin-の基礎自作教材)
- [フェーズ 2: 3 つのアプリの再学習（自作教材）](#フェーズ-2-3-つのアプリの再学習自作教材)
- [フェーズ 3: Android Basics with Compose（選択受講）](#フェーズ-3-android-basics-with-compose選択受講)
- [フェーズ 4: Android XR Codelab](#フェーズ-4-android-xr-codelab)
- [フェーズ 5: 残りのコースと発展](#フェーズ-5-残りのコースと発展)
- [教材の作り方と進め方のルール](#教材の作り方と進め方のルール)

---

## 全体像

```text
フェーズ1  Kotlin の基礎              自作教材 KotlinBasics（レッスン 1〜5）
   ↓
フェーズ2  3 アプリの再学習            HappyBirthday → DiceRoller → Lemonade を白紙から作り直す
   ↓
フェーズ3  Android Basics (選択受講)   Unit 2 P3 / Unit 3 / Unit 4 / Unit 5 のコルーチン部分
   ↓
フェーズ4  XR Codelab                  Part 1（スペースと空間パネル）→ Part 2（オービターと空間環境）
   ↓
フェーズ5  残りと発展                  ネットワーク・DB・WorkManager・XR の自作アプリ
```

XR の Codelab は，前提として「Compose を用いた Android アプリ開発の基礎」コース修了程度の Kotlin / Compose の経験を求めている．そのため，XR に入る前に，フェーズ 3 の必須項目（状態管理・ViewModel・コルーチンの基本）まで済ませる．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## フェーズ 1: Kotlin の基礎（自作教材）

3 つのアプリで使う Kotlin の文法を，アプリに入る前に固める．公式コースの「Unit 1 Pathway 1（Kotlin の概要）」と「Unit 2 Pathway 1（Kotlin の基礎）」に相当する内容を，JS との対比つきで学ぶ．

教材: [projects/KotlinBasics](projects/KotlinBasics)

| # | テーマ | 主な内容 | 状態 |
|---|---|---|---|
| 1 | 変数・型・null 安全 | `val` / `var`，Int と Double，文字列テンプレート，`?.` `?:` | 完了 |
| 2 | 関数・条件式 | 関数，デフォルト引数，名前付き引数，式としての `if` / `when`，範囲と `for` | 完了 |
| 3 | クラスとデータ | `class`，コンストラクタ，`data class`，`enum`，`interface`，`object` | 演習待ち |
| 4 | コレクションとラムダ | `List` / `Map`，`map` / `filter`，ラムダ式，関数型，末尾ラムダ | 未着手 |
| 5 | Compose への橋渡し | 名前付き引数・デフォルト引数・末尾ラムダを使い，`Column { Text("...") }` のような書き方を素の Kotlin で自作する | 未着手 |

**完了の目安**: 各レッスンの演習がすべて ✅ になること．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## フェーズ 2: 3 つのアプリの再学習（自作教材）

すでに作った 3 つのアプリを，「写経」ではなく「なぜそう書くか」を理解したうえで，白紙から作り直す．元のプロジェクトは残し，再学習用に新しいプロジェクトを `projects/` に追加する．

| 順 | 元のアプリ | 再学習用プロジェクト（予定） | 重点 |
|---|---|---|---|
| 1 | HappyBirthday | `HappyBirthdayRelearn` | `Composable`，`Modifier`，`Column` / `Box`，リソース参照，`Preview`．HTML / CSS との対応 |
| 2 | DiceRoller | `DiceRollerRelearn` | `remember` / `mutableStateOf`，クリック処理，再コンポーズ，`when` |
| 3 | Lemonade | `LemonadeRelearn` | 状態の持ち方，`enum` による段階管理，ファイル分割，`dimens.xml` の整理 |

各アプリの進め方:

1. 元のコードを 1 行ずつ読み，「HTML / CSS / JS ならこう」の対応表を作る．
2. 解説と演習つきの教材（`README` と `TODO` 入りの雛形）を私が用意する．
3. 白紙のプロジェクトで自分の手で作り直す．
4. 発展課題で 1 つ機能を足す．

**完了の目安**: 元のコードを見ずに，3 つとも作れること．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## フェーズ 3: Android Basics with Compose（選択受講）

[コース全体](https://developer.android.com/courses/android-basics-compose/course?hl=ja)は 8 ユニット・約 100 時間ある．XR に必要なところを優先して選ぶ．

| ユニット | パスウェイ | 方針 | 理由 |
|---|---|---|---|
| 1 | 1. Kotlin の概要 | フェーズ 1 で代替 | 自作教材で扱う |
| 1 | 2. Android Studio のセットアップ | 済 | 環境は構築済み．XR 用エミュレータはフェーズ 4 の前に別途準備 |
| 1 | 3. 基本的なレイアウト | フェーズ 2 で再学習 | HappyBirthday |
| 2 | 1. Kotlin の基礎 | フェーズ 1 で代替 | 自作教材で扱う |
| 2 | 2. ボタンの追加 | フェーズ 2 で再学習 | DiceRoller |
| 2 | 3. UI と状態 | **必須** | 状態の持ち上げ（state hoisting）と単体テスト．XR でも同じ考え方を使う |
| 3 | 1. Kotlin の基礎（その他） | 必要な部分だけ | 拡張関数・高階関数・ジェネリクス．自作教材のレッスン 6 に回す |
| 3 | 2. スクロール可能なリスト | **必須** | `LazyColumn` など．複数パネルの UI の土台 |
| 3 | 3. 美しいアプリ | 一部 | Material テーマ，アニメーションの基本 |
| 4 | 1. アーキテクチャ コンポーネント | **必須** | ライフサイクル，`ViewModel`，`StateFlow`．アプリ構成の中心 |
| 4 | 2. Navigation | 推奨 | 複数画面の構成．HTML の複数ページに相当 |
| 4 | 3. さまざまな画面サイズに適応する | 前半 | `WindowSizeClass`，アダプティブ レイアウト．XR は新しいフォームファクタ |
| 5 | 1. インターネットからデータを取得する | コルーチンの部分のみ | XR Codelab Part 2 が `scope.launch` を使う．HTTP / Retrofit は後で |
| 5 | 2. 画像の読み込み・リポジトリ | 後回し | フェーズ 5 |
| 6 | データの永続化（SQL / Room / DataStore） | 後回し | フェーズ 5 |
| 7 | WorkManager | 後回し | フェーズ 5 |
| 8 | Compose とビュー | 後回し | 既存の View アプリを扱う予定が出たら |

**進め方**: 公式の Codelab は，それぞれ `projects/` に新しいプロジェクトとして追加する．詰まった点は質問する．レッスン 6（拡張関数など）とレッスン 7（コルーチン）は，必要になる直前に私が自作教材として用意する．

**完了の目安**: 必須の項目を済ませ，`ViewModel` で状態を持つアプリを 1 つ自力で作れること．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## フェーズ 4: Android XR Codelab

| 順 | Codelab | 内容 |
|---|---|---|
| 1 | [Android XR の基礎 Part 1 - スペースと空間パネル](https://developer.android.com/codelabs/xr-fundamentals-part-1?hl=ja) | ホームスペースとフルスペースの切り替え，`SpatialPanel` による複数パネル，`resizable()` / `movable()` |
| 2 | [Android XR の基礎 Part 2 - オービターと空間環境](https://developer.android.com/codelabs/xr-fundamentals-part-2?hl=ja) | `Orbiter`，空間環境（スカイボックスなど）の切り替え |

**事前準備（フェーズ 3 の途中で済ませる）**: 環境の準備に時間がかかるため，早めに確認する．

- Android Studio のプレビュー版（Codelab は Quail Canary 1 以降を指定）を，現在の Android Studio とは別に入れる．
- XR 用の AVD（XR Device プロファイル，Android XR のシステム イメージ）を作り，お使いの PC のアーキテクチャで起動できることを確認する．
- Codelab は開発中のライブラリ（`alpha` 版）を使うため，本文の手順どおりに動かないことがある．その場合は，エラーを手がかりに最新のドキュメントと照らし合わせて直す．

**完了の目安**: 両方の Codelab を完了し，自分でパネルを 1 つ足せること．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## フェーズ 5: 残りのコースと発展

- Unit 5 の残り（Retrofit，リポジトリ，Coil）
- Unit 6（Room，DataStore）
- Unit 7（WorkManager）
- XR の自作アプリ（3D モデル，空間音声，`SceneCore` など）．既存の `XRtest` を発展させる．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 教材の作り方と進め方のルール

- 自作教材は，その都度 `projects/` 以下に新しいプロジェクトとして追加し，各プロジェクトに `README.md` を置く．ルートの `README.md` の一覧も更新する．
- 毎回「JS / HTML / CSS ならこう → Kotlin / Compose ならこう」の対比から入る．
- 解説の後に短い演習を出し，自分で書いてもらってからレビューする．答えは先に見せない．
- コミットは明示的な指示があるときだけ行う．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>
