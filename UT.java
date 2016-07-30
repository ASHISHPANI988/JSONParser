import java.net.*;
import java.io.*;

class urllib {
	public static StringBuffer urlopen(String url) {
		try {
			URL u = new URL(url);
			StringBuffer sb = new StringBuffer();
			BufferedReader in = new BufferedReader(
				new InputStreamReader(u.openStream()));
			String str = null;
			while((str = in.readLine())!=null) {
				sb.append(str);
			}
			in.close();
			return sb;
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

public class UT {
	public static void main(String[] args) {
		StringBuffer sb = urllib.urlopen("http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=Hosur");
		String content=sb.toString().replaceAll("\\s+","");
		System.out.println(content);
	}
}