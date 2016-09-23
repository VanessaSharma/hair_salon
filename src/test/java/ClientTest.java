import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class ClientTest {
  private Client client;
  private Client otherClient;
  private Stylist stylist;

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    stylist = new Stylist("Vanessa", "Funky haircuts your mother will hate!");
    client = new Client("Lori","Y", "Y", 1);
    otherClient = new Client("Ilena", "Y", "Y", 2);
  }

  @Test
  public void Client_instantiates_true() {
    assertTrue(client instanceof Client);
  }

  @Test
  public void Client_getId_true() {
    assertTrue(client.getId() > 0);
  }

  @Test
  public void Client_getName_string() {
    assertEquals("Lori", client.getName());
  }

  @Test
  public void Client_getHaircut_string() {
    assertEquals("Y", client.getHaircut());
  }
  @Test
  public void Client_getColor_string() {
    assertEquals("Y", client.getColor());
  }

  @Test
  public void Client_getName_int() {
    assertEquals(1, client.getStylistId());
  }

  @Test
  public void Client_all_ArrayList() {
    assertTrue(Client.all().size() > 0);
  }

  @Test
  public void Client_allByStylist_ArrayList() {
    assertTrue(Client.allByStylist(1).size() > 0);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql).executeUpdate();
    }
  }

}

