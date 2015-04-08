
public class Champion {
	public String name;
	public Integer key;
	
	public Champion(String name, Integer key) {
		super();
		this.name = name;
		this.key = key;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
}
