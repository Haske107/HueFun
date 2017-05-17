import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.net.HttpURLConnection;
import java.net.URL;

public class LightControl {

    //DEV CREDENTIALS
    static String username = "bwkYDWP2HzCZzTVHE12mVVPspqwrFEkW6GYxoHvz";
    static String ip = "192.168.0.25";
    static String method = "";

  //CONSOLE
  public static void Console() throws IOException  {
    System.out.println("\n===============================\nLet's get Rickity-Rickity-REKT!\n===============================\n");
    System.out.println("Turn on the aggrivator? (Y/N)");
    Scanner keyboard = new Scanner(System.in);
    String Decision = keyboard.nextLine();
    if(Decision.equals("Y") || Decision.equals("y"))
    {
      Aggrivator();
    }
    else
    {
      System.out.println("What about a test? (Y/N)");
      keyboard = new Scanner(System.in);
      Decision = keyboard.nextLine();
      if(Decision.equals("Y") || Decision.equals("y"))
      {
        //System.out.println("He doesn't stand a chance...");
        Test();
      }
    }
  }

  //AGGRIVATOR
  public static void Aggrivator() throws IOException {
    while(true) {
      try{
        //LIGHTS TO RED
        for(int a = 0; a < 5; ++a) {
          for(int i = 1; i < 5; i++)  {
            System.out.println("Setting lights to red");
            String number = "4";//Integer.toString(i);
            String body = "{\"on\":false}";
            String urlEnd = "/lights/" + number + "/state";
            String url1 = "http://" + ip + "/api/" + username + urlEnd;
            byte[] bytes = put(url1,body.getBytes());
            String goodies = new String(bytes, StandardCharsets.UTF_8);
            //System.out.println(url1);
            System.out.println(goodies);
        }
      }
        //TURN OFF
        for(int j = 0; j < 5; ++j) {
          for(int i = 1; i < 5; i++)  {
            System.out.println("Toggling light switch");
            String number = "4";//Integer.toString(i);
            String body = "{\"on\":true}";
            String urlEnd = "/lights/" + number + "/state";
            String url1 = "http://" + ip + "/api/" + username + urlEnd;
            byte[] bytes = put(url1,body.getBytes());
            String goodies = new String(bytes, StandardCharsets.UTF_8);
            //System.out.println(url1);
            System.out.println(goodies);
          }
        }
      } catch(Exception e){}
    }
  }

  //TEST
  public static void Test() throws IOException {
    while(true) {
      try{
        for(int i = 1; i < 3; i++)  {
          String number = Integer.toString(i);
          String body = "{\"xy\":[0.675,0.322]}";
          String urlEnd = "/lights/4/state";
          String url1 = "http://" + ip + "/api/" + username + urlEnd;
          byte[] bytes = put(url1,body.getBytes());
          String goodies = new String(bytes, StandardCharsets.UTF_8);
          System.out.println(url1);
          System.out.println(goodies);
        }
      } catch(Exception e){}
    }
  }

  //HTTP FUNCTIONS
  static public byte[] put(String urlstr, byte[] content) throws IOException {

        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {

            final URL url = new URL(urlstr);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(content.length));

            OutputStream os = null;
            os = connection.getOutputStream();
            os.write(content);
            os.close();

            is = connection.getInputStream();
            final byte[] buffer = new byte[2 * 1024];
            baos = new ByteArrayOutputStream();

            int n;
            while ((n = is.read(buffer)) >= 0) {
                baos.write(buffer, 0, n);
            }
            data = baos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            is.close();
            baos.close();
        }
        return data;
    }

  //MAIN
  public static void main(String[] args) throws IOException {
    Console();
  }
}
