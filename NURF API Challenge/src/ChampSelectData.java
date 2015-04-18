import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChampSelectData implements Serializable{
	public Integer[] selections;
	public ChampSelectData(){
		selections = new Integer[Constants.champions.size()];
		for(int i = 0; i < selections.length; i++){
			selections[i] = 0;
		}
	}
	
	public ChampSelectData(List<Integer> champKeys){
		selections = new Integer[Constants.champions.size()];
		for(int i = 0; i < selections.length; i++){
			selections[i] = 0;
		}
		
		for(Integer champKey: champKeys){
			selections[Constants.keyToId.get(champKey)]++;
		}
	}
	
	public void add(ChampSelectData b){
		if(b == null){return;}
		for(int i = 0; i < selections.length; i++){
			selections[i] += b.selections[i];
		}
	}
	
	public void add(List<Integer> champKeys){
		
		for(Integer champKey: champKeys){
			if(Constants.keyToId.get(champKey) == null){
				System.out.println("We found a Bard! "+champKey+"\n");
			}
			else{
				selections[Constants.keyToId.get(champKey)]++;
			}
		}
	}
	
	public String toString(){
		StringBuffer toReturn = new StringBuffer();
		for(int i = 0; i < selections.length; i++){
			toReturn.append(Constants.champions.get(i).getName().substring(0, Math.min(4, Constants.champions.get(i).getName().length())) + ": \t"+selections[i]+"\n");
		}
		return toReturn.toString();
	}
	
	
}
