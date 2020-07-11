package com.lovelyswallow.likejava8.util;

public class StringJoiner {

  private StringBuilder sb;
  private final String delimiter;
  private final String suffix;
  private boolean isFirst = true;
  private static final String BLANK = "";

  public StringJoiner(CharSequence delimiter) {
    this(delimiter, BLANK, BLANK, 16);
  }

  public StringJoiner(CharSequence delimiter, int capacity) {
    this(delimiter, BLANK, BLANK, capacity);
  }

  public StringJoiner(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
    this(delimiter, prefix, suffix, 16);
  }

  public StringJoiner(CharSequence delimiter, CharSequence prefix, CharSequence suffix, int capacity) {
    this.sb = new StringBuilder(capacity);
    this.sb.append(prefix.toString());
    this.delimiter = delimiter.toString();
    this.suffix = suffix == null ? BLANK : suffix.toString();
  }

  public StringJoiner add(CharSequence newElement) {
    if (this.isFirst) {
      this.isFirst = false;
    } else {
      this.sb.append(this.delimiter);
    }
    this.sb.append(newElement);
    return this;
  }

  @Override
  public String toString() {
    if (BLANK.equals(this.suffix)) {
      return this.sb.toString();
    }
    // return this.sb.toString();だとtoStringする度にsuffixが付いてしまうことに気づいたので、新しいsbを作って末尾にsuffixを付け加えてる。パフォマン悪いかもだけど。
    StringBuilder toString = new StringBuilder(this.sb.length());
    toString.append(this.sb.toString());
    if (!BLANK.equals(this.suffix)) {
      toString.append(this.suffix);
    }
    return toString.toString();
  }
}
