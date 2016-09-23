import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class StylistTest{
  Stylist myStylist;
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    myStylist = new Stylist("Vanessa","F", "Y", "Y");
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql_stylists = "DELETE FROM stylists *;";
      String sql_clients= "DELETE FROM clients *;";
      con.createQuery(sql_stylists).executeUpdate();
      con.createQuery(sql_clients).executeUpdate();
    }
  }

  @Test
  public void stylist_instantiatesCorrectly() {
   assertTrue(myStylist instanceof Stylist);
 }

  @Test
  public void getName_StylistInstantiatesWithName_Vanessa() {
    Stylist myStylist = new Stylist("Vanessa","F", "Y", "Y");
    assertEquals("Vanessa", myStylist.getName());
 }

 @Test
 public void getGender_returnsGender_String(){
   assertEquals("F", myStylist.getGender());
 }

  @Test
  public void getHaircut_returnsHaircut_String(){
    assertEquals("Y", myStylist.getHaircut());
  }

  @Test
  public void getColor_returnsColor_String(){
    assertEquals("Y", myStylist.getColor());
  }

  @Test
   public void getStylistId_instantiatesToZero_Zero(){
     assertEquals(0, myStylist.getClientId());
   }

  @Test
  public void equals_returnsTrueIfFieldsAreTheSame(){
  Stylist myOtherStylist = new Stylist("Vanessa", "Ilena","Y", "Y");
  assertTrue(myStylist.equals(myOtherStylist));
}

@Test
public void save_savesStylistToDatabase(){
  myStylist.save();
  String sql = "SELECT * FROM stylists WHERE name = 'Vanessa'";
  Stylist fetchedStylist;
  try(Connection con = DB.sql2o.open()) {
    fetchedStylist = con.createQuery(sql).executeAndFetchFirst(Stylist.class);
  }
  assertTrue(myStylist.equals(fetchedStylist));
}

  @Test
  public void all_returnsAllInstancesOfStylists_true(){
    myStylist.save();
    Stylist myOtherStylist = new Stylist("Ilena","F", "Y", "Y");
    myOtherStylist.save();
    assertTrue(Stylist.all().contains(myStylist));
    assertTrue(Stylist.all().contains(myOtherStylist));
  }
  @Test
  public void sort_sortsByGivenColumns_true(){
    myStylist.save();
    Stylist myOtherStylist = new Stylist("Ilena","F", "Y", "Y");
    myOtherStylist.save();
    assertEquals(myStylist, Stylist.all().get(0));
    assertEquals(myOtherStylist, Stylist.sort("name").get(0));
   }

     @Test
     public void getId_stylistsInstantiateWithAnID(){
       myStylist.save();
       assertTrue(myStylist.getId() > 0);
     }

     @Test
     public void find_returnsStylistWithSameId_otherStylist(){
       myStylist.save();
       Stylist myOtherStylist = new Stylist("Ilena","F", "Y", "Y");
       myOtherStylist.save();
       assertEquals(Stylist.find(myOtherStylist.getId()), myOtherStylist);
     }

}
