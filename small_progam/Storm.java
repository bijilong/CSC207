package stormPath;

import java.util.ArrayList;

public class Storm {

  private String name;
  private ArrayList cities;
  private int year;

  public Storm(String name, int year) {
    this.name = name;
    this.year = year;
    this.cities = new ArrayList();
  }

  public String getName() {
    return name;
  }

  public int getYear() {
    return year;
  }

  public ArrayList getCities() {
    return cities;
  }

  public void addCity(City c) {
    cities.add(c);
    c.addStorm(this);
  }

  public boolean equals(Object other) {
    boolean answer = false;
    if (other instanceof Storm) {
      Storm s = (Storm) other;
      answer = Storm.this.getName().equals(s.getName()) && Storm.this.getYear() == s.getYear();
    }
    return answer;
  }

  public String toString() {
    if (cities.size() == 0) {
      return this.name + ", " + this.getYear();
    } else {
      String result = this.name + ", " + this.getYear();
      for (int i = 0; i < this.getCities().size(); i++) {
        String cityname = ((City) this.getCities().get(i)).getName();
        result = result + System.lineSeparator() + cityname;
      }
      return result;
    }
  }
}