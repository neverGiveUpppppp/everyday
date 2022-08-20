package chap12_Collection.B_Set.model.vo;

public class Dog {
	
	private String name;
	private double weight;
	
	public Dog() {}
	public Dog(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public double getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return name + " " + weight+"kg";
	}

	@Override
	public boolean equals(Object obj) {
		
		if(this==obj) {
			return true;
		}
		if(obj==null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		
		Dog other = (Dog)obj;
		if(name == null) {
			if(other.name != null) {
				return false;
			}
		}else if(!name.equals(other.name)) {
			return false;
		}
		
		if(weight != other.weight) {
			return false;
		}
		return true;
		
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		
		result = PRIME * result + (name == null ? 0 : name.hashCode());
		result = PRIME * result + (int)weight;
		
		return result;
	}
	
	
}
