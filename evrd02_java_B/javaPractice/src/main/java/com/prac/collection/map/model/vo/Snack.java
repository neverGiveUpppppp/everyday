package com.prac.collection.map.model.vo;

import java.util.Objects;

public class Snack {

    private String flavor;
    private int price;

    public Snack() {}

    public Snack(String flavor, int price) {
        super(); // 안써도 됨. 자동완성해서 생긴것. 원래는 생성자를 불러올 때 부모생성자를 불러오고 시작함.
        this.flavor = flavor;   // 그래야 자식 객체를 만들 때 부모 객체 생성자를 만들기 때문
        this.price = price;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Snack)) return false;
        Snack snack = (Snack) o;
        return price == snack.price && flavor.equals(snack.flavor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flavor, price);
    }


    @Override
    public String toString() {
        return "Snack{" +
                "flavor='" + flavor + '\'' +
                ", price=" + price +
                '}';
    }
}

