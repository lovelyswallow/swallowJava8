package com.lovelyswallow.likejava8.util;

public class StringJoiner {

  private StringBuilder sb;
  private final String delimiter;
  private final int prefixLength;
  private final String suffix;
  private String emptyValue;
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
    if (prefix == null) {
      throw new NullPointerException("The prefix must not be null");
    }
    if (delimiter == null) {
      throw new NullPointerException("The delimiter must not be null");
    }
    if (suffix == null) {
      throw new NullPointerException("The suffix must not be null");
    }
    this.sb = new StringBuilder(capacity);
    this.prefixLength = prefix.length();
    this.sb.append(prefix.toString());
    this.delimiter = delimiter.toString();
    this.suffix = suffix.toString();
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

  public StringJoiner merge(StringJoiner other) {
    this.add(other.sb.substring(other.prefixLength, other.sb.length()));
    return this;
  }

  public StringJoiner setEmptyValue(CharSequence emptyValue) {
    if (emptyValue == null) {
      throw new NullPointerException("The empty value must not be null");
    }
    this.emptyValue = emptyValue.toString();
    return this;
  }

  public int length() {
    return isFirst && this.emptyValue != null ? this.emptyValue.length()
        : this.sb.length() + this.suffix.length();
  }

  @Override
  public String toString() {
    if (isFirst && this.emptyValue != null) {
      return this.emptyValue;
    }
    if (BLANK.equals(this.suffix)) {
      return this.sb.toString();
    }
    StringBuilder toString = new StringBuilder(this.length());
    toString.append(this.sb.toString());
    toString.append(this.suffix);
    return toString.toString();
  }
}
