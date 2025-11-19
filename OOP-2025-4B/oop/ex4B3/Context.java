package oop.ex4B3;

import java.util.StringTokenizer;

public class Context {
  private String cur; // 次の要素を保存するためのバッファ
  final private StringTokenizer tokenizer;

  public Context(String str) {
    tokenizer = new StringTokenizer(str);
    cur = null;
  }

  public String nextToken() {
    if(cur != null) {
      String result = cur;
      cur = null;
      return result;
    }
    return tokenizer.nextToken();
  }

  public String currentToken() {
    if(cur == null) {
      cur = tokenizer.nextToken();
    }
    return cur;
  }
}
