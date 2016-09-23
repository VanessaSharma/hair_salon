import org.sql2o.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Client{
  private String name;
  private String stylist;
  private Date appointment;
  private String haircut;
  private String color;
  private int id;
  private int stylist_id;

  public Client(String name, String stylist, String appointment, String haircut, String color){
    this.name = name;
    this.stylist = stylist;
    this.haircut = haircut;
    this.color= color;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    try {
    this.appointment = formatter.parse(appointment);
  } catch (Exception e){
      System.out.print("Please add a valid appointment date.");
    }
    this.stylist_id = 0;
  }

  public String getName(){
    return name;
  }

  public String getStylist(){
    return stylist;
  }

  public Date getAppointment(){
    return appointment;
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

  public void setStylistId(int stylist_id){
    this.stylist_id = stylist_id;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET stylist_id = :stylists_id WHERE id = :id";
      con.createQuery(sql)
        .addParameter("stylist_id", stylist_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  //
  // @Override
  // public boolean equals(Object otherAnimal) {
  //   if(!(otherAnimal instanceof Animal)){
  //     return false;
  //   } else {
  //     Animal newAnimal = (Animal) otherAnimal;
  //     return this.name.equals(newAnimal.name) && this.admittance.equals(newAnimal.admittance) &&
  //       this.gender.equals(newAnimal.gender) && this.type.equals(newAnimal.type) && this.getBreed().equals(newAnimal.getBreed()) && this.id == newAnimal.id;
  //   }
  // }
  //
  // public void save(){
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO animals(name, admittance, gender, type, breed, owner_id) VALUES (:name, :admittance, :gender, :type, :breed, :owner_id)";
  //     this.id = (int) con.createQuery(sql, true)
  //       .addParameter("name", this.name)
  //       .addParameter("admittance", this.admittance)
  //       .addParameter("gender", this.gender)
  //       .addParameter("type", this.type)
  //       .addParameter("breed", this.breed)
  //       .addParameter("owner_id", this.owner_id)
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }
  //
  // public static List<Animal> all(){
  //   String sql="SELECT * FROM animals";
  //   try(Connection con = DB.sql2o.open()) {
  //     return con.createQuery(sql).executeAndFetch(Animal.class);
  //   }
  // }
  //
  // public static List<Animal> sort(String sortby){
  //   String sql="SELECT * FROM animals ORDER BY :sortby";
  //   try(Connection con = DB.sql2o.open()) {
  //     return con.createQuery(sql).addParameter("sortby", sortby).executeAndFetch(Animal.class);
  //   }
  // }
  //
  // public static Set<String> getTypes(){
  //   Set<String> typeSet = new TreeSet<String>();
  //   for(Animal animal: Animal.all()){
  //     typeSet.add(animal.type);
  //   }
  //   return typeSet;
  // }
  //
  // public static Set<String> getBreeds(){
  //   Set<String> breedSet = new TreeSet<String>();
  //   for(Animal animal: Animal.all()){
  //     breedSet.add(animal.breed);
  //   }
  //   return breedSet;
  // }
  //
  // public static Animal find(int id){
  //   try(Connection con = DB.sql2o.open()){
  //     String sql = "SELECT * FROM animals WHERE id=:id";
  //     Animal animal = con.createQuery(sql)
  //     .addParameter("id", id)
  //     .executeAndFetchFirst(Animal.class);
  //     return animal;
  //   }
  // }
}
