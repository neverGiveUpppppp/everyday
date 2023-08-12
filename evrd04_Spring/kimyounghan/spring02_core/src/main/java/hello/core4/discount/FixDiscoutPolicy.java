package hello.core4.discount;

import hello.core4.member.Grade;
import hello.core4.member.Member;


public class FixDiscoutPolicy implements DiscountPolicy{
    private int fixdiscoutPrice = 1000;

    public int discount(Member member, int price){
        if(member.getGrade() == Grade.VIP)
            return fixdiscoutPrice;
        else
            return 0;
    }
}
