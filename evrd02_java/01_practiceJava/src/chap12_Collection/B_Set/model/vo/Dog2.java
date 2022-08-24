package chap12_Collection.B_Set.model.vo;

public class Dog2 {
	
	private String name;
	private double weight;
	
	
	// default constructor
	public void Dog2() {}
	
	// constructor with parameter
	public void Dog2(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}
	
	// getter & setter
	public void setName(String name) {	// 값을 받아 저장하는게 setter니 리턴이 필요x -> void
		this.name = name;				// 값을 받아야하니 parameter 필요  
	}
	public String getName() {	// 해당 멤버변수의 값을 읽을 수 있게하는 역할의 getter이므로
		return name;			// return이 필요 & 데이터타입 필요
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getWeight() {
		return weight;
	}
	
	
	// toString()
	@Override
	public String toString() {
		return name + " "+weight+"kg";
	}
	
	// equals()
	public boolean equals(Object obj) {
		
		// 이퀄즈의 비교 내용 2가지
		// 1.클래스 비교
		// 2.내용 비교
		
		// 1.클래스비교
		if(this == obj) {
			return true;
		}	
		if(obj == null) {
			return false;
		}
		
		if(getClass() != obj.getClass()) {
			return false;
		}
		
		// 내용비교
		// 	1번 멤버변수 name 비교
		Dog2 other = (Dog2)obj;
		if(name == null) {
			if (other.name != null) {
				return false;
			}
		}else if(!name.equals(other.name)) {
			return false;
		}
		// 	2번 멤버변수 weight 비교
		if(weight != other.weight) {
			return false;
		}
		return true;
	}
	
	
	
	// hashCode()
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		
		result = PRIME * result + (name == null ? 0 : name.hashCode());
		result = PRIME * result + (int)weight;
		
		return result;
		
	}
	
}

