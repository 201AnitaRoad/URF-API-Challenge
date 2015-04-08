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
	private File dirFile;
	public Archiver(){
		dirFile = new File(dirPath);
		dirFile.mkdir();
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
			System.out.println("We fucked up and are fuckboys. \n");
			e.printStackTrace();
		} finally{}
	}
	
	public ChampSelectData loadMoment(Date date){
		String dateName = Utility.convertDateToEpoch(date).toString();
		File momentFile = new File(dirFile, dateName);
		if(!momentFile.exists()){return null;}
		ObjectInputStream oIS;
		ChampSelectData toReturn = null;
		try{
			oIS = new ObjectInputStream(new FileInputStream(momentFile));
			toReturn = (ChampSelectData)oIS.readObject();
			oIS.close();
		} catch (Exception e){
			System.out.println("Our load is jabronied.\n");
			e.printStackTrace();
		}
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
