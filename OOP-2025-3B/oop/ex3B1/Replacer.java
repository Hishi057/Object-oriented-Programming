package oop.ex3B1;

public class Replacer {
  int year;
  int month;
  int day;

  public Replacer(int year, int month, int day) {
    this.year = year;
    this.month = month;
    this.day = day;
  }

  public String replace(String template) {
    String year = String.format("%04d", this.year);
    String month = String.format("%02d", this.month);
    String day = String.format("%02d", this.day);
    return template.replace("YYYY", year)
                   .replace("MM", month)
                   .replace("DD", day);
  }
}
