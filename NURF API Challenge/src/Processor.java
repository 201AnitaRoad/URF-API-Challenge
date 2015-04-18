import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Processor {
	
	//combines all moments within time range, [start, end)
	//presuming that start is a sanitized, five-minute-clean date and time
	private static ChampSelectUnitVector getBucket(Date start, Date end, Archiver archive){
		ChampSelectData toReturn = new ChampSelectData();
		Date iter = new Date(start.getTime());
		while(iter.before(end)){
			ChampSelectData temp = archive.loadMoment(iter);
			if(temp != null){
				toReturn.add(temp);
			}
			else{
				return null;
			}
			iter = Utility.getFiveMinutesLater(iter);
		}
		ChampSelectUnitVector unitVector = new ChampSelectUnitVector(toReturn);
		try {
			archive.dump(start.toString()+" -- "+end.toString()+"\n");
			archive.dump(unitVector.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Retrieving bucket from "+start.toString()+" to "+end.toString()+"\n");
		return unitVector;
	}
	
	//returns a list of ChampSelectData for use by math/analyisis functionality
	//this list will represent buckets of bucketLength length in milliseconds
	//over the time period [start, end), presuming that start is a sanitized, five-minute-clean
	//date and time
	public static List<Pair<Date, ChampSelectUnitVector>> getBuckets(Date start, Date end, Archiver archive, Long bucketLength){
		List<Pair<Date, ChampSelectUnitVector>> buckets = new ArrayList<Pair<Date, ChampSelectUnitVector>>();
		Date iter = new Date(start.getTime());
		while(iter.before(end)){
			ChampSelectUnitVector currentBucket = getBucket (iter, Utility.addLongToDate(iter, bucketLength), archive);
			if(currentBucket != null){
				buckets.add(new Pair<Date, ChampSelectUnitVector>(iter, currentBucket));
			}
			iter = Utility.addLongToDate(iter, bucketLength);
		}
		return buckets;
	}
	
}
