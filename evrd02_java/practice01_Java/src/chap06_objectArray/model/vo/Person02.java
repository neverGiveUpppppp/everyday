package chap06_objectArray.model.vo;

public class Person02 {

	private String name;
	private int age;
	private char gender;
	private double height;
	private double weight;
	
	public Person02(){}
	
	public Person02(String name, int age) {
		this.name= name;
		this.age = age;
	}
	
	public Person02(String name, int age, char gender, double height, double weight) {
		this(name, age);
		this.gender = gender;
		this.height = height;
		this.weight = weight;
	}
	
	// getter & setter
	
	public void setName(String name) {
		this.name= name;
	}
	public String getName() {
		return name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public char getGender() {
		return gender;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double height() {
		return height;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	
	// toString() 포지션
	public String personInfo() {
		return name + ", " + age + ", " + gender + ", " + height + ", " + weight;
	}
	
}
