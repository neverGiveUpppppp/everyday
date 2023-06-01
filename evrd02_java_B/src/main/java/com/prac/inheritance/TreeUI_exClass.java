package com.prac.inheritance;

import java.util.ArrayList;
import java.util.Date;


class MenuItem{
    String header;
    ArrayList<MenuItem> childItems;

    public MenuItem(){
        childItems = new ArrayList<>();
    }
}

class League extends MenuItem{
    String leagueName;

    public League(String leagueName){
        this.leagueName = leagueName;
    }
}

class Team extends MenuItem{
    String teamName;
    Date createDate;
}

class Player extends MenuItem{
    String playerName;
}


public class TreeUI_exClass {
    public static void main(String[] args) {

        ArrayList<MenuItem> leagueList = new ArrayList<>();
        League premiLeague = new League("잉글랜드 프리미어리그");
        premiLeague.childItems.add(new Team());
        premiLeague.childItems.add(new Team());
        premiLeague.childItems.add(new Team());
        premiLeague.childItems.add(new Team());
        leagueList.add(new League("잉글랜드 프리미어리그"));

        System.out.println(leagueList);
        leagueList.forEach(System.out::println);
        System.out.println(premiLeague);

        Car aCar = new Car("Spark"); // aCar에 Spark가 아닌 bCar에서 저장한 Morning이 들어가있음
        Car bCar = aCar;                 // 이유 : referenceType이라 주소값 복사라서 값 공유. primitiveType이라면 주소값 복사 안되기 때문에 값 공유x
        bCar.setName("Morning");

    }

}
