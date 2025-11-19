# 問題4B-2

以下に示す5種類の命令からなる簡易プログラミング言語とその処理系を考える。

* `acc n` : アキュムレータに n を加算する
* `jmp n` : プログラムカウンタを n に変更する（絶対位置，0始まり）
* `jnq n` : アキュムレータが n でない場合，プログラムカウンタを n に変更する
* `inc` : `acc 1` と同義
* `dec` : `acc -1` と同義

`Main4B2`は簡易プログラミング言語のプログラムを入力ファイルから読み込み、`Parser`が`parse`メソッドで`Program`を生成し、`Program`の`execute`メソッドでプログラムを実行してその結果を出力することを想定している。`oop.ex4B2`パッケージの2つのクラスに以下を実装し処理系を完成させよ。ただし`Main4B2`の`main`メソッドは修正してはならない。

1. `Parser` : `Program parse(List<String> lines)` を提供し，命令列を解析する。
2. `Program` : 命令列を保持し，`int execute()` を提供して実行する。

なお，プログラムは命令列（1行1命令、空行なし）で記述され、各命令の構文規則は以下の通りとする。

* 命令名は小文字。
* `acc/jmp/jnq` は整数引数を取る。
* `inc/dec` は引数なし。

処理系は全命令の実行終了時のアキュムレータの値を出力するものとする。不正な形式や未知の命令，整数に解釈できない引数が記述されている場合，`IllegalArgumentException` を送出して停止する。
また，初期状態はアキュムレータとプログラムカウンタはともに 0 とする。`acc/inc/dec` は実行後に プログラムカウンタを 1 増やす。`jmp/jnq` は分岐先に遷移する。プログラムカウンタが命令列の範囲外になった時点で実行を終了する。

---

### 実行例

```
> cat << EOF > input.txt
acc 3
dec
inc
EOF
> java Main4B2 input.txt
3

> cat << EOF > input.txt
acc 1
jmp 4
acc 7
acc 7
acc 2
EOF
> java Main4B2 input.txt
3

> cat << EOF > input.txt
acc 2
jnq 3
acc 99
acc -2
EOF
> java Main4B2 input.txt
0

> cat << EOF > input.txt
acc 5
jmp 5
EOF
> java Main4B2 input.txt
5
```
