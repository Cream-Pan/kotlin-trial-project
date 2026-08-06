<div id="top"></div>

# Dice Roller

Dice Roller は，ボタンをタップするたびにサイコロの目をランダムに切り替えて表示する，Jetpack Compose の状態管理・イベント処理の基礎を学ぶための Android アプリである．

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

画面中央にサイコロの画像と「Roll」ボタンを表示し，ボタンを押すたびに 1〜6 のランダムな整数を生成して対応する画像に切り替える．[Google の Android Basics with Compose コース](https://developer.android.com/courses/pathways/android-basics-compose-unit-2-pathway-2?authuser=2) に沿って実装した．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 画面構成

| 要素 | 内容 |
|---|---|
| サイコロ画像 | 現在の出目（1〜6）に対応する `drawable` を表示 |
| Roll ボタン | タップすると出目を再抽選する |

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## ファイル構成

```text
app/src/main/java/com/example/diceroller/
├── MainActivity.kt      # DiceRollerApp / DiceWithButtonAndImage
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

- `remember { mutableStateOf(...) }` によるコンポーザブル内の状態保持
- `Button` の `onClick` からの状態更新と再描画（リコンポジション）
- `when` 式による出目 → 画像リソースのマッピング
- `stringResource` / `painterResource` を使ったリソース参照

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 開き方

Android Studio で `File > Open` からこの `DiceRoller` フォルダを選択する．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 参考コース

[Android Basics with Compose - Unit 2 Pathway 2](https://developer.android.com/courses/pathways/android-basics-compose-unit-2-pathway-2?authuser=2)

<p align="right">(<a href="#top">トップへ戻る</a>)</p>
