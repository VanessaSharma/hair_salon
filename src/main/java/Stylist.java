import org.sql2o.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;

public class Stylist{
  private String name;
  private String gender;
  private String haircut;
  private String color;
  private int id;


  public Stylist(String name, String gender, String haircut, String color){
    this.name = name;
    this.gender = gender;
    this.haircut = haircut;
    this.color= color;

  }
  public String getName(){
    return name;
  }
  public String getGender(){
    return gender;
  }

  public String getHaircut(){
    return haircut;
  }

  public String getColor(){
    return color;
  }

  public int getId(){
    return id;
  }

  @Override
  public boolean equals(Object otherStylist) {
    if(!(otherStylist instanceof Stylist)){
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.name.equals(newStylist.name) && this.gender.equals(newStylist.gender) &&
        this.haircut.equals(newStylist.haircut) && this.color.equals(newStylist.color) && this.id == newStylist.id;
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(name, gender, haircut, color, client_id) VALUES (:name, :gender, :haircut, :color, :client_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("gender", this.gender)
        .addParameter("haircut", this.haircut)
        .addParameter("color", this.color)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Stylist> all(){
  String sql="SELECT * FROM stylists";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Stylist.class);
  }
}

  public static List<Stylist> sort(String sortby){
    String sql="SELECT * FROM stylists ORDER BY :sortby";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).addParameter("sortby", sortby).executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }
}
