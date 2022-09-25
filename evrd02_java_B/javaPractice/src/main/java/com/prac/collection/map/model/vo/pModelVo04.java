package com.prac.collection.map.model.vo;

public class pModelVo04 {

    private String name;
    private int score;

    public pModelVo04() {}
    public pModelVo04(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // getter & setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }


    // toString
    @Override
    public String toString() {
//		return name(score점); 이 줄이 아래 코드 의미...
        return name+"("+score+"점)";
    }

//    @Override
//    public boolean equals(Object obj) {
//        // 클래스 비교
//        if(this == obj) {
//            return true;
//        }
//        if(obj == null) {
//            return false;
//        }
//        if(getClass() != obj.getClass()) {
//            return false;
//        }
//
//        // 내용 비교
//        Student other = (Student)obj;
//        if(name == null) {
//            if(other.name != null) {
//                return false;
//            }
//        } else if(!name.equals(other.name)) {
//            return false;
//        }
//
//        if(score != other.score) {
//            return false;
//        }
//
//        return true;
//    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;

        result = PRIME * result + (name == null ? 0 : name.hashCode());
        result = PRIME * result + score;

        return result;
    }

}
