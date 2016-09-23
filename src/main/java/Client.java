import org.sql2o.*;
import java.util.List;

public class Client{
  private String name;
  private String haircut;
  private String color;
  private int id;
  private int stylistid;

  public Client(String name, String haircut, String color, int stylistid){
    this.name = name;
    this.haircut = haircut;
    this.color = color;
    this.stylistid = stylistid;

  try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, haircut, color, stylistid) VALUES (:name, :haircut, :color, :stylistid)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("haircut", this.haircut)
        .addParameter("color", this.color)
        .addParameter("stylistid", this.stylistid)
        .executeUpdate()
        .getKey();
    }
  }

  public String getName(){
    return name;
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

  public int getStylistId(){
   return stylistid;
 }

  public static Client find(int id) {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT * FROM clients WHERE id = :id";
     return con.createQuery(sql)
     .addParameter("id", id)
     .executeAndFetchFirst(Client.class);
   }
 }

  public static List<Client> all() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT * FROM clients ORDER by name";
     return con.createQuery(sql)
       .executeAndFetch(Client.class);
   }
 }
  public static List<Client> allByStylist(int stylistid) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylistid = :id ORDER by name";
      return con.createQuery(sql)
        .addParameter("id", stylistid)
        .executeAndFetch(Client.class);
    }
  }

  public static void update(String name, String haircut, String color, int stylistid, int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, haircut = :haircut, color = :color, stylistid = :stylistid WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .addParameter("haircut", haircut)
        .addParameter("color", color)
        .addParameter("stylistid", stylistid)
        .executeUpdate();
    }
  }

   public static void delete(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}

