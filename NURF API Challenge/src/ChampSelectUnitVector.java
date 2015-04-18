
public class ChampSelectUnitVector {
	
	public Double[] ratios;
	public Integer gamesRepresented;
	
	public ChampSelectUnitVector(ChampSelectData vector){
		Double squareTotal = 0.0;
		Integer componentSum = 0;
		ratios = new Double[Constants.champions.size()];
		for(int i = 0; i < Constants.champions.size(); i++){
			squareTotal += Math.pow(vector.selections[i], 2.0);
			componentSum += vector.selections[i];
		}
		Double sum = 0.0;
		Double denominator;
		if(squareTotal != 0.0){
			denominator = Math.sqrt(squareTotal);
			for(int j = 0; j < Constants.champions.size(); j++){
				ratios[j] = vector.selections[j]/denominator;
			}
			for(int k = 0; k < Constants.champions.size(); k++){
				sum += Math.pow(ratios[k], 2.0);
			}
			
		}
		else{
			for(int j = 0; j < Constants.champions.size(); j++){
				ratios[j] = 0.0;
			}
			denominator = 0.0;
		}
		System.out.println("Number of games sampled: "+componentSum/10);
		gamesRepresented = componentSum/10;
		System.out.println("Magnitude of unit vector (ideally v. close to 1.0) : "+sum+"\n");
	}
	
	//copy constructor
	public ChampSelectUnitVector(ChampSelectUnitVector c){
		ratios = new Double[Constants.champions.size()];
		for(int i = 0; i < ratios.length; i++){
			ratios[i] = c.ratios[i];
		}
		this.gamesRepresented = c.gamesRepresented;
	}
	
	//difference constructor
	//returns a champselectunitvector that is a - b
	public ChampSelectUnitVector(ChampSelectUnitVector a, ChampSelectUnitVector b){
		ratios = new Double[Constants.champions.size()];
		for(int i = 0; i < ratios.length; i++){
			ratios[i] = a.ratios[i] - b.ratios[i];
		}
	}
	
	public String toString(){
		StringBuffer toReturn = new StringBuffer();
		for(int i = 0; i < ratios.length; i++){
			toReturn.append(Constants.champions.get(i).getName().substring(0, Math.min(4, Constants.champions.get(i).getName().length())) + ": \t"+ratios[i]+"\n");
		}
		return toReturn.toString();
	}
	
	public void multiply(double d){
		for(int i = 0; i < Constants.champions.size(); i++){
			ratios[i] = ratios[i] * d;
		}
	}
	
	public void divide(int n){
		for(int i = 0; i < Constants.champions.size(); i++){
			ratios[i] = ratios[i] / ((double)n);
		}
	}

	
	public void add(ChampSelectUnitVector b){
		if(b == null){return;}
		for(int i = 0; i < ratios.length; i++){
			ratios[i] += b.ratios[i];
		}
	}
	
	public void normalize() {
		Double sumSquared = 0.0;
		for (int i = 0; i < ratios.length; i++) {
			sumSquared += Math.pow(ratios[i], 2.0);
		}
		
		Double magnitude = Math.sqrt(sumSquared);
		Double sum = 0.0;
		for (int i = 0; i < ratios.length; i++) {
			ratios[i] = ratios[i] / magnitude;
			sum += Math.pow(ratios[i], 2);
		}
		System.out.println("Should be close to 1.0: " + sum);
		
	}
}
