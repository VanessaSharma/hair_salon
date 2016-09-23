import org.sql2o.*;
import java.util.List;

public class Stylist{
  private String name;
  private String specialty;
  private int id;


  public Stylist(String name, String specialty){
    this.name = name;
    this.specialty = specialty;

 try(Connection con = DB.sql2o.open()) {
     String sql = "INSERT INTO stylists (name, specialty) VALUES (:name, :specialty)";
     this.id = (int) con.createQuery(sql, true)
       .addParameter("name", this.name)
       .addParameter("specialty", this.specialty)
       .executeUpdate()
       .getKey();
   }
 }


  public String getName(){
    return name;
  }
  public String getSpecialty(){
    return specialty;
 }
  public int getId(){
    return id;
  }

  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists ORDER by name";
      return con.createQuery(sql)
      .executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id = :id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
    }
  }

  public static void update(String name, String specialty, int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :name, specialty = :specialty WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .addParameter("specialty", specialty)
        .executeUpdate();
    }
  }

  public static void delete(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE stylistid = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}

