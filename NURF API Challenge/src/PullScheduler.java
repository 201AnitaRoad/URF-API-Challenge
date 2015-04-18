import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PullScheduler {
	public static void main(String[] args) throws InterruptedException {
		Archiver archiver = null;
		try {
			archiver = new Archiver();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Date> eightHours;
		while(true){
			try{
				eightHours = getEightHoursOfCleanDates();
				archiver.pruneEightHoursList(eightHours);
				ChampSelectData moment;
				for(Date d: eightHours){
					moment = doorToDoorMoment(d.getTime());
					if(moment != null){
						archiver.saveMoment(d, moment);
					}
					else{
						System.out.println("Too far back: "+d.toString()+". Current time: "+new Date().toString());
					}
				}
			}catch (Exception e){
				File errorFile = new File("Error.txt");
				try{
				BufferedWriter bw = new BufferedWriter(new FileWriter(errorFile));
				bw.write(e.toString());
				bw.close();
				}catch (Exception e2){}
			}
			Thread.sleep(300000); // sleep for 5 minutes
		}
	}
	
	private static ChampSelectData doorToDoorMoment(Long time) throws Exception{
		List<Long> matchIDs = Whisperer.getMatchIDsForMoment(time);
		if(matchIDs == null){return null;}
		List<Integer> champKeys;
		ChampSelectData toReturn = new ChampSelectData();
		for(Long matchID: matchIDs){
			champKeys = Whisperer.getChampIDsFromMatchID(matchID);
			toReturn.add(champKeys);
		}
		return toReturn;
	}
	
	private static List<Date> getEightHoursOfCleanDates(){
		List<Date> toReturn = new ArrayList<Date>();
		Date d0 = Utility.getCleanFiveMinuteTime();
		toReturn.add(d0);
		Date last = d0;
		Date current;
		for(int i = 0; i < 95; i++){
			current = Utility.getFiveMinutesEarlier(last);
			last = current;
			toReturn.add(current);
		}
		return toReturn;
	}
}
