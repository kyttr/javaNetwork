/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testPaket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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
        /*
        params.put("q","java servlet get request");
        URL url=new URL("http://www.google.com.tr/search?q=java+servlet+get+request");
        */
        /*
        params.put("user_name", "<myusername>");
        params.put("user_pass", "<mypass>");
        URL url=new URL("https://registration.boun.edu.tr/scripts/stuinflogin.asp");
        */
        
        params.put("Email", "<myemail>");
        params.put("Passwd", "<mypass>");
        URL url=new URL("https://accounts.google.com/ServiceLoginAuth");
        
        URLConnection conn=url.openConnection();
        conn.setUseCaches(false);
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        conn.setRequestProperty("Accept",
		"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	conn.setRequestProperty("Connection", "keep-alive");
	//conn.setRequestProperty("Referer", "https://registration.boun.edu.tr/");
	conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       
                
        String outHTML=kaya.kayaClassPaket.kayaNetworkAbstractClass1.readWrittenURL2HTMLString(conn, params);
        System.out.println(outHTML);
    }
    
    	// HTTP GET request
    //@Test
    public void sendGet() throws Exception {

        String url = "http://www.google.com/search?q=mkyong";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}
