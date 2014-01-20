/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testPaket;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author kaya
 */
public class JUnitTest1 {
    
    public JUnitTest1() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void test1() throws IOException
    {
        Map<String, String> params=new HashMap<String, String>();
        /*
        params.put("username", "kaya");
        params.put("password", "11112222");
        URL url=new URL("http://titan.cmpe.boun.edu.tr:8088/BetterTogether/user/login");
        */
        
        params.put("q","java servlet get request");
        URL url=new URL("https://www.google.com.tr/search");
        
        /*
        params.put("user_name", "2008400057");
        params.put("user_pass", "<mypass>");
        URL url=new URL("https://registration.boun.edu.tr/scripts/stuinflogin.asp");
        */
        String outHTML=kaya.kayaClassPaket.kayaNetworkAbstractClass1.readWrittenURL2HTMLString(url, params);
        System.out.println(outHTML);
    }
}
