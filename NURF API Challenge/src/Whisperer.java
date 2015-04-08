import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class Whisperer {
	private final static String charset = "UTF-8";
	
	public static List<Long> getMatchIDsForMoment(Long time) throws Exception{
		List<Long> toReturn;
		time = Utility.sanitizeForRiot(time);
		int status;
		int sleepCount = 0;
		HttpURLConnection connection;
		do{
			System.out.println("https://na.api.pvp.net/api/lol/na/v4.1/game/ids?beginDate="+time+"&api_key=45ebd9b3-5788-4cc8-bc9e-9d6e549fdabd");
			connection = (HttpURLConnection) new URL("https://na.api.pvp.net/api/lol/na/v4.1/game/ids?beginDate="+time+"&api_key=45ebd9b3-5788-4cc8-bc9e-9d6e549fdabd").openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
	
			status = connection.getResponseCode();
			if(status != 200 && status != 400 && status != 429){
				throw new Exception("Error connecting to api-challenge-v4.1 endpoint.\nStatus: "+status);
			}
			else if(status == 400){
				return null; //terminate the backwards-through-time iteration
			}
			if(status == 429){
				sleepCount++;
				System.out.println("Sleeping.");
				Thread.sleep(10000);
			}
			if (sleepCount == 100) {
				throw new Exception("Sleepcount exceeded 100.");
			}
		}while(status == 429);
		InputStream response = connection.getInputStream();
		String responseString = Utility.convertStreamToString(response);
		
		Type collectionType = new TypeToken<List<Long>>(){}.getType();
		Gson gson = new Gson();
		toReturn = gson.fromJson(responseString, collectionType);
		System.out.println(toReturn.size());
		connection.disconnect();
		return toReturn;
	}
	
	public static List<Integer> getChampIDsFromMatchID(Long matchID) throws Exception{
		List<Integer> toReturn = new ArrayList<Integer>();
		HttpURLConnection connection;
		int status;
		int sleepCount = 0;
		do{
			System.out.println("https://na.api.pvp.net/api/lol/na/v2.2/match/"+matchID+"?includeTimeline=false&api_key=45ebd9b3-5788-4cc8-bc9e-9d6e549fdabd");
			connection = (HttpURLConnection) new URL("https://na.api.pvp.net/api/lol/na/v2.2/match/"+matchID+"?includeTimeline=false&api_key=45ebd9b3-5788-4cc8-bc9e-9d6e549fdabd").openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			
			status = connection.getResponseCode();
	
			if(status != 200 && status != 429){
				throw new Exception("Error connecting to match-v2.2 endpoint.\nStatus: "+status+"\n");
			}
			
			if(status == 429){
				sleepCount++;
				System.out.println("Sleeping.");
				Thread.sleep(10000);
			}
			
			if (sleepCount == 100) {
				throw new Exception("Sleepcount exceeded 100.");
			}
			
		}while(status == 429);
		
		InputStream response = connection.getInputStream();
		String responseString = Utility.convertStreamToString(response);
		
		//Gson gson = new Gson();
		//System.out.println(responseString);
		
		responseString = responseString.substring(0, responseString.indexOf("bans"));
		
		int champsFound = 0;
		int pivot = responseString.indexOf("championId");
		String toParse;
		while(pivot != -1){
			toParse = responseString.substring(responseString.indexOf(':', pivot) + 1, responseString.indexOf(',', pivot));
			toReturn.add(Integer.valueOf(toParse));
			champsFound++;
			pivot = responseString.indexOf("championId", pivot + 1);
		}
		
		if(champsFound != 10){
			throw new Exception("Found "+champsFound+" champion IDs; expected 10.\n");
		}
		connection.disconnect();
		return toReturn;
	}
	
}
