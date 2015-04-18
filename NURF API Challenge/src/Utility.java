import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utility {
	public static String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
	
	public static Long sanitizeForRiot(Long unsanitized){
		//convert from milliseconds precision to seconds
		//convert from GMT-7 (local time) to GMT
		return (unsanitized/1000) - 25200;
	}
	
	public static Date getAsDate(String str) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return df.parse(str);
	}
	
	public static Long convertDateToEpoch(Date date) {
        long epoch = date.getTime();
		return epoch;
	}
	
	public static Date getCleanFiveMinuteTime(){
		Date date = new Date();
		Long unroundedEpoch = convertDateToEpoch(date);
		Long roundedEpoch = unroundedEpoch - (unroundedEpoch % 300000);
		return new Date(roundedEpoch);
	}
	
	public static Date addLongToDate (Date date, Long x){
		Long baseEpoch = convertDateToEpoch(date);
		return new Date(baseEpoch + x);
	}
	
	public static Date getFiveMinutesEarlier(Date date){
		Long baseEpoch = convertDateToEpoch(date);
		return new Date(baseEpoch - 300000);
	}
	
	public static Date getFiveMinutesLater(Date date){
		Long baseEpoch = convertDateToEpoch(date);
		return new Date(baseEpoch + 300000);
	}
	
	public static boolean isEmpty(ChampSelectData moment){
		for(int i = 0; i < moment.selections.length; i++){
			if(moment.selections[i] != 0){return false;}
		}
		return true;
	}
}
