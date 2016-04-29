import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.*;

public class Clima {
	public static String getHTML(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
		   result.append(line);
		}
		rd.close();
		return result.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
                    System.out.println("Ingrese la ciudad");
                    Scanner lea = new Scanner(System.in);
                    String s = lea.next();
                    
			String respuesta = getHTML("http://api.openweathermap.org/data/2.5/weather?q="+s+"&appid=d66e142c718e4453f6c474ad09a5d191");
			System.out.println(respuesta);
			JSONObject obj = new JSONObject(respuesta);
                        double latitud = obj.getJSONObject("coord").getDouble("lat") ;
			double temp = obj.getJSONObject("main").getDouble("temp")- 273.15;
                        double longitud = obj.getJSONObject("coord").getDouble("lon") ;
			double presion = obj.getJSONObject("main").getDouble("pressure");
                        double humedad = obj.getJSONObject("main").getDouble("humidity");
                        double TM = obj.getJSONObject("main").getDouble("temp_max");
                        double Tm = obj.getJSONObject("main").getDouble("temp_min");
                        System.out.println("La temperatura en " + s + " es: "+temp+" Celsius");
                        System.out.println("La latitud en " + s + " es: "+latitud);
			System.out.println("La longitud en " + s + " es: "+longitud);
			System.out.println("La presion en " + s + " es: " +presion);
                        System.out.println("La humedad en " + s + " es: " +humedad);
                        System.out.println("La Temperatura Maxima en" + s + " es: " +TM);
                        System.out.println("La Temperatura Minima en" + s + " es: " +Tm);
                     
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
        