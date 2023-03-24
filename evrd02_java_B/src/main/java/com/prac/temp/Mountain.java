package com.prac.temp;


class Atom {
      Atom() { System.out.print("atom "); } // {Mountain@509}  // 5 : {Rock@512}
}

class Rock extends Atom {
       Rock(String type) { System.out.print(type); } // {Mountain@509} // 9 : {Rock@512}
}

public class Mountain extends Rock {
    Mountain() {
        super("granite1 "); // {Mountain@509}
        int c= 5+3;
        new Rock("granite2 ");

    }

    public static void main(String[] a) {
        new Mountain(); // {Mountain@508}
    }
}

