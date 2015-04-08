
public class ChampSelectUnitVector {
	public Double[] ratios;
	
	public ChampSelectUnitVector(){
		ratios = new Double[Constants.champions.size()];
		for(int i = 0; i < Constants.champions.size(); i++){
			ratios[i] =  1/((double)Constants.champions.size());
		}
	}
	
	public ChampSelectUnitVector(ChampSelectData vector){
		Double squareTotal = 0.0;
		ratios = new Double[Constants.champions.size()];
		for(int i = 0; i < Constants.champions.size(); i++){
			squareTotal += Math.pow(vector.selections[i], 2.0);
		}
		Double denominator = Math.sqrt(squareTotal);
		for(int j = 0; j < Constants.champions.size(); j++){
			ratios[j] = vector.selections[j]/denominator;
		}
		
		Double sum = 0.0;
		for(int k = 0; k < Constants.champions.size(); k++){
			sum += Math.pow(ratios[k], 2.0);
		}
		//System.out.println("Magnitude of unit vector (ideally v. close to 1.0) : "+sum+"\n");
	}
	
	public String toString(){
		StringBuffer toReturn = new StringBuffer();
		for(int i = 0; i < ratios.length; i++){
			toReturn.append(Constants.champions.get(i).getName().substring(0, Math.min(4, Constants.champions.get(i).getName().length())) + ": \t"+ratios[i]+"\n");
		}
		return toReturn.toString();
	}
}
