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
			iter = Utility.getFiveMinutesLater(iter);
		}
		System.out.println("Retrieving bucket from "+start.toString()+" to "+end.toString()+"\n");
		return new ChampSelectUnitVector(toReturn);
	}
	
	//returns a list of ChampSelectData for use by math/analyisis functionality
	//this list will represent buckets of bucketLength length in milliseconds
	//over the time period [start, end), presuming that start is a sanitized, five-minute-clean
	//date and time
	public static List<ChampSelectUnitVector> getBuckets(Date start, Date end, Archiver archive, Long bucketLength){
		List<ChampSelectUnitVector> buckets = new ArrayList<ChampSelectUnitVector>();
		Date iter = new Date(start.getTime());
		while(iter.before(end)){
			ChampSelectUnitVector currentBucket = getBucket (iter, Utility.addLongToDate(iter, bucketLength), archive);
			buckets.add(currentBucket);
			iter = Utility.addLongToDate(iter, bucketLength);
		}
		return buckets;
	}
	
}
