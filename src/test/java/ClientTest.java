import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ClientTest{
  Client myClient;
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    myClient = new Client("Vanessa","Ilena", "2016-09-29", "Y", "Y");
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql_clients = "DELETE FROM clients *;";
      String sql_stylists= "DELETE FROM stylists *;";
      con.createQuery(sql_clients).executeUpdate();
      con.createQuery(sql_stylists).executeUpdate();
    }
  }

  @Test
  public void client_instantiatesCorrectly() {
   assertTrue(myClient instanceof Client);
 }

  @Test
  public void getName_ClientInstantiatesWithName_Vanessa() {
    Client myClient = new Client("Vanessa", "Ilena", "2016-09-29", "Y", "Y");
    assertEquals("Vanessa", myClient.getName());
 }

 @Test
 public void getStylist_returnsStylist_String(){
   assertEquals("Ilena", myClient.getStylist());
 }

  @Test
  public void getAppointment_returnsAppointment_Date(){
     DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
     Date date=null;
     try{
       date = formatter.parse("2016-09-29");
     } catch(Exception e){
       System.out.print("Please choose an appointment date");
     }
     assertEquals(date, myClient.getAppointment());
   }

  @Test
  public void getHaircut_returnsHaircut_String(){
    assertEquals("Y", myClient.getHaircut());
  }

  @Test
  public void getColor_returnsColor_String(){
    assertEquals("Y", myClient.getColor());
  }

  @Test
   public void getStylistId_instantiatesToZero_Zero(){
     assertEquals(0, myClient.getStylistId());
   }

}
