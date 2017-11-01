package stormPath;

import java.util.ArrayList;

public class City {

  private String name;
  private ArrayList storms;

  public City(String name) {
    this.name = name;
    this.storms = new ArrayList();
  }

  public String getName() {
    return name;
  }

  public boolean wasHit(Storm s) {
    return s.getCities().contains(this);
  }

  public void addStorm(Storm s) {
    storms.add(s);
    if (!s.getCities().contains(this)) {
      s.getCities().add(this);
    }

  }

  public boolean onSamePath(City c) {
    for (int i = 0; i < storms.size(); i++) {
      Object s = storms.get(i);
      for (int j = 0; j < c.storms.size(); j++) {
        if (s.equals(c.storms.get(j))) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean equals(Object other) {
    boolean result = false;  //
    boolean result2 = true;  //

    // if type is city
    if (other instanceof City) {
      City c = (City) other;
      result = this.getName().equals(c.getName());
      // loop over all storms in this city
      for (int i = 0; i < this.storms.size(); i++) {
        Object h = this.storms.get(i);

        // if c doesn't contain ...
        if (!c.storms.contains(h)) {
          result2 = false;
        }

        // fail fast if they dont have same set of cities
        if (!result2) {
          break;
        }
      }

      // check size.
      if (this.storms.size() != c.storms.size()) {
        result2 = false;
      }
    }

    return result && result2;
  }

  public String toString() {
    if (storms.size() == 0) {
      return name + " " + "()";
    } else {
      StringBuffer result = new StringBuffer();
      String finalresult = "";
      for (int i = 0; i < storms.size(); i++) {
        String stormname = ((Storm) this.storms.get(i)).getName();
        result.append(stormname);
        if (!(i == storms.size() - 1)) {
          result.append(", ");
        }
        finalresult = name + " " + "(" + result + ")";
      }
      return finalresult;
    }
  }
}

