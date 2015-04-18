import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Mathman {
	public static void main(String[] args) {
		Long start = new Long(((long)1428265800) * 1000);
		Long bucketLength = new Long(10800000); //three hours (3.6m * 3)
		List<Pair<Date, ChampSelectUnitVector>> buckets = chewBuckets(start, bucketLength);
		
		List<Triple<Date, Double, Integer>> metaDataStandardApproach = createMetadataStandardApproach(buckets);
		List<Triple<Date, Double, Integer>> metaDataInverseApproach = createMetadataInverseApproach(buckets);
		List<Triple<Date, Double, Integer>> metaDataInverseSquaredApproach = createMetadataInverseSquaredApproach(buckets);
		
//		System.out.println("huzzah\n");
//		System.out.println(metaData.get(0).getSecond().toString());
		
		try {
			writeToSpreadsheet(metaDataStandardApproach, "metaDataStandard.csv");
			writeToSpreadsheet(metaDataInverseApproach, "metaDataInverse.csv");
			writeToSpreadsheet(metaDataInverseSquaredApproach, "metaDataInverseSquared.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Pair<Date, ChampSelectUnitVector>> chewBuckets(Long start, Long bucketLength){
		Archiver archive = null;
		try {
			archive = new Archiver();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date end = new Date();
		Date startDate = new Date(start);
		List<Pair<Date, ChampSelectUnitVector>> buckets = Processor.getBuckets(startDate, end, archive, bucketLength);
		buckets.remove(buckets.size()-1);
		return buckets;
	}
	
	private static List<Triple<Date, Double, Integer>> createMetadataStandardApproach(List<Pair<Date, ChampSelectUnitVector>> buckets){
		List<Triple<Date, Double, Integer>> metaData = new ArrayList<Triple<Date, Double, Integer>>();
		List<ChampSelectUnitVector> metaVectors = getMetaVectorsStandardApproach(buckets);
		List<ChampSelectUnitVector> bucketVectors = getBucketVectors(buckets);
		
		for(int i = 0; i < buckets.size(); i++){
			metaData.add(new Triple<Date, Double, Integer>(buckets.get(i).getFirst(), getDistance(bucketVectors.get(i), metaVectors.get(i)), bucketVectors.get(i).gamesRepresented));
		}
		
		return metaData;
	}
	
	private static List<Triple<Date, Double, Integer>> createMetadataInverseApproach(List<Pair<Date, ChampSelectUnitVector>> buckets){
		List<Triple<Date, Double, Integer>> metaData = new ArrayList<Triple<Date, Double, Integer>>();
		List<ChampSelectUnitVector> metaVectors = getMetaVectorsInverseApproach(buckets);
		List<ChampSelectUnitVector> bucketVectors = getBucketVectors(buckets);
		
		for(int i = 0; i < buckets.size(); i++){
			metaData.add(new Triple<Date, Double, Integer>(buckets.get(i).getFirst(), getDistance(bucketVectors.get(i), metaVectors.get(i)), bucketVectors.get(i).gamesRepresented));
		}
		
		return metaData;
	}
	
	private static List<Triple<Date, Double, Integer>> createMetadataInverseSquaredApproach(List<Pair<Date, ChampSelectUnitVector>> buckets){
		List<Triple<Date, Double, Integer>> metaData = new ArrayList<Triple<Date, Double, Integer>>();
		List<ChampSelectUnitVector> metaVectors = getMetaVectorsInverseSquaredApproach(buckets);
		List<ChampSelectUnitVector> bucketVectors = getBucketVectors(buckets);
		
		for(int i = 0; i < buckets.size(); i++){
			metaData.add(new Triple<Date, Double, Integer>(buckets.get(i).getFirst(), getDistance(bucketVectors.get(i), metaVectors.get(i)), bucketVectors.get(i).gamesRepresented));
		}
		
		return metaData;
	}
	
	private static List<ChampSelectUnitVector> getBucketVectors(List<Pair<Date, ChampSelectUnitVector>> buckets){
		List<ChampSelectUnitVector> toReturn = new ArrayList<ChampSelectUnitVector>();
		for(Pair<Date, ChampSelectUnitVector> pair: buckets){
			toReturn.add(pair.getSecond());
		}
		return toReturn;
	}
	
	// exclusive, and first two meta vectors are the same. results in first distance point being 0
	private static List<ChampSelectUnitVector> getMetaVectorsStandardApproach(List<Pair<Date, ChampSelectUnitVector>> buckets){
		//base case
		ChampSelectUnitVector baseMetaVector = buckets.get(0).getSecond();
		List<ChampSelectUnitVector> toReturn = new ArrayList<ChampSelectUnitVector>();
		toReturn.add(baseMetaVector);
		toReturn.add(baseMetaVector);
		//inductive step
		for(int i = 2; i < buckets.size(); i++){
			ChampSelectUnitVector metaVector = new ChampSelectUnitVector(toReturn.get(toReturn.size()-1));
			metaVector.multiply(i-1);
			metaVector.add(buckets.get(i-1).getSecond());
			metaVector.divide(i);
			toReturn.add(metaVector);
		}
		
		return toReturn;
	}
	
	private static List<ChampSelectUnitVector> getMetaVectorsInverseApproach(List<Pair<Date, ChampSelectUnitVector>> buckets){
		//base case
		ChampSelectUnitVector baseMetaVector = buckets.get(0).getSecond();
		List<ChampSelectUnitVector> toReturn = new ArrayList<ChampSelectUnitVector>();
		toReturn.add(baseMetaVector);
		toReturn.add(baseMetaVector);
		
		for(int i = 2; i < buckets.size(); i++) {
			ChampSelectUnitVector current = new ChampSelectUnitVector(baseMetaVector);
			current.multiply(1/i);
			for (int j = 2; j <= i; j++) {
				ChampSelectUnitVector next = new ChampSelectUnitVector(buckets.get(j-1).getSecond());
				next.multiply(j/i);
				current.add(next);
			}
			current.normalize();
			toReturn.add(current);
		}
		
		return toReturn;
	}
	
	private static List<ChampSelectUnitVector> getMetaVectorsInverseSquaredApproach(List<Pair<Date, ChampSelectUnitVector>> buckets){
		//base case
		ChampSelectUnitVector baseMetaVector = buckets.get(0).getSecond();
		List<ChampSelectUnitVector> toReturn = new ArrayList<ChampSelectUnitVector>();
		toReturn.add(baseMetaVector);
		toReturn.add(baseMetaVector);
		
		for(int i = 2; i < buckets.size(); i++) {
			ChampSelectUnitVector current = new ChampSelectUnitVector(baseMetaVector);
			current.multiply(Math.pow((1/i), 2.0));
			for (int j = 2; j <= i; j++) {
				ChampSelectUnitVector next = new ChampSelectUnitVector(buckets.get(j-1).getSecond());
				next.multiply(Math.pow((j/i), 2.0));
				current.add(next);
			}
			current.normalize();
			toReturn.add(current);
		}
		
		return toReturn;
	}

	private static Double getDistance(ChampSelectUnitVector a, ChampSelectUnitVector b){
		Double squareTotal = 0.0;
		ChampSelectUnitVector difference = new ChampSelectUnitVector(a, b);
		for(int i = 0; i < Constants.champions.size(); i++){
			squareTotal += Math.pow(difference.ratios[i], 2.0);
		}
		return Math.sqrt(squareTotal);
	}
	
	private static void writeToSpreadsheet(List<Triple<Date, Double, Integer>> metaData, String fileName) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		for(Triple<Date, Double, Integer> triple: metaData){
			bw.write(triple.getFirst().toString()+","+triple.getSecond().toString()+","+triple.getThird()+"\n");
		}
		bw.close();
	}
}
