import org.sql2o.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Client{
  private String name;
  private String stylist;
  private String haircut;
  private String color;
  private int id;
  private int stylist_id;


  public Client(String name, String stylist, String haircut, String color){
    this.name = name;
    this.stylist = stylist;
    this.haircut = haircut;
    this.color = color;
    this.stylist_id = stylist_id;
  }

  public String getName(){
    return name;
  }

  public String getStylist(){
    return stylist;
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
    return stylist_id;
    }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) && this.getStylist().equals(newClient.getStylist()) && this.getHaircut().equals(newClient.getHaircut()) && this.getColor().equals(newClient.getColor()) && this.getId() == newClient.getId() && this.getStylistId() == newClient.getStylistId();
    }
  }
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients (name, stylist, haircut, color) VALUES (:name, :stylist, :haircut, :color)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("stylist", this.stylist)
        .addParameter("haircut", this.haircut)
        .addParameter("color", this.color)
        .executeUpdate()
        .getKey();
    }
  }
  public List<Client> getCustomers(){
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM stylists WHERE client_id =:id";
    return con.createQuery(sql)
    .addParameter("id", this.id)
    .executeAndFetch(Client.class);
  }
}

public static List<Client> all() {
 String sql = "SELECT id, name, stylist, haircut, color FROM clients";
 try(Connection con = DB.sql2o.open()) {
   return con.createQuery(sql).executeAndFetch(Client.class);
 }
}

  public static Client find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM clients WHERE id = :id";
    Client  client= con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
    return client;
  }
}

 }
