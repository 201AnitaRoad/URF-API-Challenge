import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;


public class Archiver {
	private String dirPath = "momentdata";
	private String dumpPath = "consoledump.txt";
	private BufferedWriter dumpStream;
	private File dirFile, dumpFile;
	public Archiver() throws IOException{
		dirFile = new File(dirPath);
		dirFile.mkdir();
		
		dumpFile = new File(dumpPath);
		if(dumpFile.exists()){
			dumpFile.delete();
		}
		dumpFile.createNewFile();
		dumpStream = new BufferedWriter(new FileWriter(dumpFile.getAbsolutePath()));
	}
	
	public void dump(String s) throws IOException{
		dumpStream.write(s);
		dumpStream.flush();
	}
	
	public void saveMoment(Date date, ChampSelectData champSelectData){
		String dateName = Utility.convertDateToEpoch(date).toString();
		File momentFile = new File(dirFile, dateName);
		System.out.println(momentFile.getAbsolutePath());
		ObjectOutputStream objectOutputStream;
		try {
			momentFile.createNewFile();
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(momentFile));
			objectOutputStream.writeObject(champSelectData);
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{}
	}
	
	public ChampSelectData loadMoment(Date date){
		String dateName = Utility.convertDateToEpoch(date).toString();
		File momentFile = new File(dirFile, dateName);
		if(!momentFile.exists()){
			System.out.println("Missing moment, Date: "+date.toString());
			System.out.println("Moving on to next bucket-kun!~");
			return null;
		}
		ObjectInputStream oIS;
		ChampSelectData toReturn = null;
		try{
			oIS = new ObjectInputStream(new FileInputStream(momentFile));
			toReturn = (ChampSelectData)oIS.readObject();
			oIS.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		if(Utility.isEmpty(toReturn)){return null;}
		return toReturn;
	}
	
	public void pruneEightHoursList(List<Date> dates){
		File dateFile;
		for(int i = dates.size()-1; i >= 0; i--){
			dateFile = new File(dirFile, String.valueOf(dates.get(i).getTime()));
			if(dateFile.exists()){
				dates.remove(i);
			}
		}
	}
}
