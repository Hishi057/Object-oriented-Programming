# 2025年度オブジェクト指向プログラミング 最終課題

Conway のライフゲームを一般化したバージョンを以下の仕様に従って実装せよ。

追加機能を実装した場合は、`Report.txt` に **内容と使用方法** を明記すること。
仕様通りのみを実装した場合は、その旨を `Report.txt` に明記すること。

入出力仕様は厳密である。ヘッドレスモードでは記載された形式に従い、余計な出力をしてはならない。

## 提出期限

**2025年12月04日00:00JST**

## 概要

* **目的**: 複数の **ワールドタイプ**（2D, HEX, 3D）、**近傍の定義**、**設定可能な B/S ルール**、**パターン入出力**、および **ヘッドレス CLI** をサポートするシミュレーションを実装する。
* **パターン形式**、**ルールの意味**、**近傍** はここで定義された仕様に従わなければならない。
* 内部表現の形式は自由とする。ただし I/O の意味論は仕様と一致させること。

  * 動的グリッドや性能に関する設計を行った場合は、`Report.txt` に理由と内容を記録すること。

## シミュレーション機能

### ワールドタイプ

* `WORLD_2D` — 正方格子。
* `WORLD_HEX` — 六角格子（頂点が上を向く「ポイントトップ」型で、**奇数行が右にずれて配置**される）。
* `WORLD_3D` — 立方体格子（N×N×N）。

#### 六角格子（Hex-world）配置例

実装では以下のように格子を配置すること：

```
 / \ / \ / \ / \ / \
|0,0|1,0|2,0|3,0|4,0|
 \ / \ / \ / \ / \ / \
  |0,1|1,1|2,1|3,1|4,1|
 / \ / \ / \ / \ / \ /
|0,2|1,2|2,2|3,2|4,2|
 \ / \ / \ / \ / \ / \
  |0,3|1,3|2,3|3,3|4,3|
 / \ / \ / \ / \ / \ /
|0,4|1,4|2,4|3,4|4,4|
 \ / \ / \ / \ / \ /
```


### ルール (B/S)

* 書式: `B<数字列>/S<数字列>`
* 意味:

  * 生存セルは、隣接数が **S** に含まれるとき生き残る。
  * 死亡セルは、隣接数が **B** に含まれるとき誕生する。
* 例: `B3/S23` → 生存条件 {2,3}、誕生条件 {3}。つまりセルは 2 または 3 個の隣接セルで生存し、ちょうど 3 個で誕生する。
* ルールは任意でコマンドライン引数から指定可能。

### 近傍

* `MOORE8` (2D): 直交 + 斜めの 8 近傍。
* `VONN4` (2D): 直交のみの 4 近傍。
* `HEX6` (HEX): 六角格子の 6 近傍（辺で接する）。
* `FACES6` (3D): 立方体の面接触 6 近傍 (±x, ±y, ±z)。

---

## パターンファイル形式 (`.pattern`)

### ヘッダ

フィールドの順序は任意。`#` で始まる行はコメント。

```
WORLD: WORLD_2D | WORLD_HEX | WORLD_3D    // 必須
SIZE: <int>                                // 必須
RULE: B3/S23                               // 任意
NEIGHBORHOOD: MOORE8|VONN4|HEX6|FACES6     // 任意
WRAP: true|false                           // 任意
```

デフォルト値:

* `RULE=B3/S23`
* `NEIGHBORHOOD` = `MOORE8` (2D) / `HEX6` (HEX) / `FACES6` (3D)
* `WRAP=false`

### 本体

* 記号: `.` = 死、`O` = 生。
* 各行はちょうど `SIZE` 記号を含む。
* 2D/HEX: 行数 = `SIZE`。
* 3D: `SIZE` 層 × `SIZE` 行、層間は空行で区切る。

### パラメータ

* `5 <= SIZE <= 1000`。

  * 動的グリッドを検討する場合、`SIZE` は初期サイズを意味する。

### 例

```
WORLD: WORLD_2D
SIZE: 5
RULE: B3/S23
NEIGHBORHOOD: MOORE8
WRAP: true

.O...
..O..
OOO..
.....
.....
```

## 実行方法

```
javac Main5.java
java Main5 \
  --pattern <input.pattern> \
  --steps <N> \
  --dump-final <output.pattern> \
  [--wrap] \
  [--rule "B3/S23"] \
  [--neighborhood MOORE8|VONN4|HEX6|FACES6] \
  [--world WORLD_2D|WORLD_HEX|WORLD_3D] \
  [--size N]
```

IntelliJ を用いる場合は 1B の説明に従う。`[⋅]` は任意引数を表す。

* CLI 引数はパターンファイルのヘッダを上書きする（両方ある場合は CLI が優先）。
* `--dump-final -` は **最終パターン（ヘッダ + 本体）** を標準出力に書き出すこと。

## 入出力例

入力例は `oop/reference/` ディレクトリに `.pattern` ファイルとして用意されている。
各入力例に対応する出力例は `_next.pattern` というサフィックス付きファイルである。
出力の比較には、Linux/Unix 系であれば `diff` コマンド、Windows であれば `fc` コマンドを利用するとよい。

用意された例は以下の機能をカバーしている：

* **`2d_blinker.pattern` / `2d_blinker_next.pattern`**：2D の基本的な振動子（`B3/S23`、`MOORE8`）。
* **`2d_glider.pattern` / `2d_glider_next.pattern`**：2D のグライダー（`B3/S23`、`MOORE8`）で対角方向に移動。
* **`2d_lwss.pattern` / `2d_lwss_next.pattern`**：軽量宇宙船。ラップアラウンドや並進の挙動を確認できる。
* **`3d_cube_block.pattern` / `3d_cube_block_next.pattern`**：安定な3Dキューブ構造（`B2/S23`）。
* **`3d_double_ring_slabs.pattern` / `3d_double_ring_slabs_next.pattern`**：3D の多層リング構造。対称性と存続判定を確認。
* **`3d_twin_cubes.pattern` / `3d_twin_cubes_next.pattern`**：3D における分離した二つの立方体。近傍の独立性をテスト。
* **`hex_colony.pattern` / `hex_colony_next.pattern`**：HEX6 の近傍を使った短寿命コロニー。
* **`hex_hexagon.pattern` / `hex_hexagon_next.pattern`**：完全な六角リング。ラップや対称性を確認。
* **`hex_ring6.pattern` / `hex_ring6_next.pattern`**：6セルのリング。循環安定性を示す。
* **`hex_ring_with_tail.pattern` / `hex_ring_with_tail_next.pattern`**：リングに短い尾を加えたもの。成長や崩壊の挙動を確認。
* **`hex_two_rings.pattern` / `hex_two_rings_next.pattern`**：隣接した二つのリング。HEX6 近傍の重なりをテスト。

採点は大まかにこの分類に従って行う。ただし、3D や拡張的な HEX パターンは追加評価対象となる。

## 提出物

* `OOP-2025-5` ディレクトリを `.zip` 化し、ソースコード一式と `Report.txt` を含めること。

## 制約

* 不正入力（サイズ不一致、未知の記号、必須項目欠落）は拒否または明示的に処理すること。
* `--dump-final -` 実行時以外に余計なコンソール出力をしてはならない。
  * `--dump-final <file_path>` が指定された場合は、そのファイルに出力すること。
* 内部設計は自由だが、意味論とフォーマットは仕様に厳密に一致させること。
