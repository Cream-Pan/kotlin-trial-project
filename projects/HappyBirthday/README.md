<div id="top"></div>

# Happy Birthday

Happy Birthday は，誕生日メッセージと送り主のテキストを背景画像に重ねて表示する，Jetpack Compose のレイアウト・修飾子（Modifier）の基礎を学ぶための Android アプリである．

---

## 使用技術一覧

<p style="display: inline">
  <img src="https://img.shields.io/badge/-Kotlin-7F52FF.svg?logo=kotlin&style=for-the-badge&logoColor=white">
  <img src="https://img.shields.io/badge/-Jetpack%20Compose-4285F4.svg?logo=jetpackcompose&style=for-the-badge&logoColor=white">
  <img src="https://img.shields.io/badge/-Android-3DDC84.svg?logo=android&style=for-the-badge&logoColor=white">
  <img src="https://img.shields.io/badge/-Material%20Design%203-757575.svg?logo=materialdesign&style=for-the-badge&logoColor=white">
  <img src="https://img.shields.io/badge/-Gradle-02303A.svg?logo=gradle&style=for-the-badge&logoColor=white">
  <img src="https://img.shields.io/badge/-Android%20Studio-3DDC84.svg?logo=androidstudio&style=for-the-badge&logoColor=white">
</p>

---

## 目次

- [プロジェクトについて](#プロジェクトについて)
- [画面構成](#画面構成)
- [ファイル構成](#ファイル構成)
- [開発環境](#開発環境)
- [学んだこと](#学んだこと)
- [開き方](#開き方)
- [参考コース](#参考コース)

---

## プロジェクトについて

`Box` の中に背景画像（`Image`）とメッセージ・送り主のテキスト（`Text`）を重ねて配置し，1枚の誕生日カードを組み立てる．[Google の Android Basics with Compose コース](https://developer.android.com/courses/pathways/android-basics-compose-unit-1-pathway-3?hl=ja) に沿って実装した．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 画面構成

| 要素 | 内容 |
|---|---|
| 背景画像 | `androidparty.png` を半透明（`alpha = 0.5f`）でクロップ表示 |
| メッセージ | 誕生日メッセージ（大きめのフォントサイズで中央揃え） |
| 送り主 | メッセージ下部に小さめのフォントで表示 |

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## ファイル構成

```text
app/src/main/java/com/example/happybirthday/
├── MainActivity.kt      # GreetingImage / GreetingText
└── ui/theme/            # Color / Theme / Type
```

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 開発環境

| 項目 | バージョン |
|---|---|
| Kotlin | 2.2.10 |
| Android Gradle Plugin | 9.1.1 |
| Compose BOM | 2024.09.00 |
| compileSdk / targetSdk / minSdk | 37 / 36 / 24 |

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 学んだこと

- `Box` を使ったレイアウトの重ね合わせ
- `Column` の `verticalArrangement` / `Alignment` による配置調整
- `Modifier.padding` / `align` によるコンポーザブルの微調整
- `ContentScale.Crop` を使った画像の表示範囲調整

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 開き方

Android Studio で `File > Open` からこの `HappyBirthday` フォルダを選択する．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 参考コース

[Android Basics with Compose - Unit 1 Pathway 3](https://developer.android.com/courses/pathways/android-basics-compose-unit-1-pathway-3?hl=ja)

<p align="right">(<a href="#top">トップへ戻る</a>)</p>
