<div id="top"></div>

# Trial Project

Trial Project は，Kotlin / Jetpack Compose を用いた Android 開発の学習用リポジトリである．Google の Android Basics with Compose コースや Codelab で作成したアプリを，それぞれ独立した Android Studio プロジェクトとして `projects/` 以下にまとめている．

---

## 使用技術一覧

<p style="display: inline">
  <img src="https://img.shields.io/badge/-Kotlin-7F52FF.svg?logo=kotlin&style=for-the-badge&logoColor=white">
  <img src="https://img.shields.io/badge/-Jetpack%20Compose-4285F4.svg?logo=jetpackcompose&style=for-the-badge&logoColor=white">
  <img src="https://img.shields.io/badge/-Android-3DDC84.svg?logo=android&style=for-the-badge&logoColor=white">
  <img src="https://img.shields.io/badge/-Material%20Design%203-757575.svg?logo=materialdesign&style=for-the-badge&logoColor=white">
  <img src="https://img.shields.io/badge/-Gradle-02303A.svg?logo=gradle&style=for-the-badge&logoColor=white">
  <img src="https://img.shields.io/badge/-Android%20Studio-3DDC84.svg?logo=androidstudio&style=for-the-badge&logoColor=white">
  <img src="https://img.shields.io/badge/-GitHub-181717.svg?logo=github&style=for-the-badge">
</p>

---

## 目次

- [プロジェクトについて](#プロジェクトについて)
- [構成](#構成)
- [プロジェクト一覧](#プロジェクト一覧)
- [開き方](#開き方)
- [今後の予定](#今後の予定)

---

## プロジェクトについて

各プロジェクトは自前の `gradlew` / `settings.gradle.kts` を持つ，完全に独立した Android Studio プロジェクトである．単一のマルチモジュール構成ではないため，Android Studio で開く際はリポジトリのルートではなく，`projects/` 配下の各フォルダを直接開く．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 構成

```text
projects/
├── HappyBirthday/   Unit 1 Pathway 3
├── DiceRoller/       Unit 2 Pathway 2
├── Lemonade/         Lemonade Codelab
└── XRtest/           Canvas を使った3D表現の単発検証（コース外）
```

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## プロジェクト一覧

| プロジェクト | 内容 | コース |
|---|---|---|
| [HappyBirthday](projects/HappyBirthday) | 誕生日カード表示アプリ | [Android Basics Compose Unit 1 Pathway 3](https://developer.android.com/courses/pathways/android-basics-compose-unit-1-pathway-3?hl=ja) |
| [DiceRoller](projects/DiceRoller) | サイコロを振るアプリ | [Android Basics Compose Unit 2 Pathway 2](https://developer.android.com/courses/pathways/android-basics-compose-unit-2-pathway-2?authuser=2) |
| [Lemonade](projects/Lemonade) | レモネードを作るミニゲーム | [Lemonade Codelab](https://developer.android.com/codelabs/basic-android-kotlin-training-project-lemonade?hl=ja&authuser=2#0) |
| [XRtest](projects/XRtest) | Canvasによる3D表現の単発検証 | - |

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 開き方

Android Studio の `File > Open` から，`projects/` 配下の対象フォルダ（例: `projects/DiceRoller`）を選択する．リポジトリのルートフォルダはビルド対象ではないため直接開かない．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>

---

## 今後の予定

[Android Basics with Compose](https://developer.android.com/courses/android-basics-compose/course?authuser=2&hl=ja) コースを引き続き受講し，ユニットごとに新しいプロジェクトを `projects/` 以下に追加していく．

<p align="right">(<a href="#top">トップへ戻る</a>)</p>
