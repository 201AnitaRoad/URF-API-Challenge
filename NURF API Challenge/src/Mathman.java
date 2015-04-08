import java.util.Date;
import java.util.List;


public class Mathman {
	public static void main(String[] args) {
		Archiver archive = new Archiver();
		Date end = new Date();
		Date start = new Date(((long)1428265800) * 1000);
		Long bucketLength = new Long(10800000); //three hours (3.6m * 3)
		List<ChampSelectUnitVector> buckets = Processor.getBuckets(start, end, archive, bucketLength);
		buckets.remove(buckets.size()-1);
		System.out.println("huzzah\n");
		System.out.println(buckets.get(0).toString());
	}
	
}
