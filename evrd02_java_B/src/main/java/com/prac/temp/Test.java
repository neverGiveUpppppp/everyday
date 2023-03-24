package com.prac.temp;

class Student{
    String name;
    int point;
}

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Student[] students = new Student[10];
		/*
		for(int i = 0 ; i < students.length ; i++) {
			students[i] = new Student();
			students[i].name = "이름"+i;
			students[i].point = i+10;
		}

		for(int i = 0 ; i < students.length ; i++) {
			System.out.println(students[i].name + "의 점수 : "+students[i].point);
		}
		*/
        int count = 0;
        for(Student student : students) {

            student = new Student();
            student.name = "이름"+count;
            student.point = count+10;
            count++;
        }

        for(Student student : students) {
            System.out.println(student.name + "의 점수 : "+student.point);
        }

    }

}