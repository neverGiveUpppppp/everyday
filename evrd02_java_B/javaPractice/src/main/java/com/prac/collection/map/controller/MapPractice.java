package com.prac.collection.map.controller;

import com.prac.collection.map.model.vo.Snack;

import java.util.*;

public class MapPractice {

    public void method01() {


        // Properties prop = new Properties();
        // key와 value를 String으로 제한시켜놓은 Map구조의 컬렉션
        Properties prop = new Properties();

        prop.setProperty("a","aa");
        prop.setProperty("b","bb");
        System.out.println(prop); // {b=bb, a=aa}
        // 순서가 앞에서부터 추가 되나?
        prop.setProperty("c","cc");
        System.out.println(prop); // {b=bb, a=aa, c=cc}
        System.out.println(prop.getProperty("a")); // aa   <- map형태가 아닌 str

        System.out.println(prop.containsKey("a")); // true


        // 1.HashMap
        // put(K key, V value):V
        // 반환타입 : value
        HashMap<String, Snack> hm = new HashMap<>();
        hm.put("a",new Snack("단맛",10));
        hm.put("b",new Snack("짠맛",10));
        System.out.println(hm);  // {a=Snack{flavor='단맛', price=10}, b=Snack{flavor='짠맛', price=10}}

        HashMap<String,Snack> hm2 = new HashMap<>(hm);
        System.out.println(hm2); // {a=Snack{flavor='단맛', price=10}, b=Snack{flavor='짠맛', price=10}}

//        HashMap<String, String> hm3 = new HashMap<>(hm2);
//        밸류가 Snack인데 String에 넣으려고 해서 에러 발생



        // 2.containsKey(Object key)
        // 키나 값이 들어가 있는지를 확인하는 메소드
        // containsKey(Object key):boolean
        // containsValue(Object value):boolean
        hm.containsKey("a");
        hm.containsKey("b");
        System.out.println(hm.containsKey("a")); // true
        System.out.println(hm.containsKey("b")); // true
        System.out.println(hm.containsKey("c")); // false



        // 3.get()
        // get(Object key) : v
        // key값에 맞는 'value값 반환'
        System.out.println(hm.get("a")); // Snack{flavor='단맛', price=10}
        // 밸류인 Snack 객체에서 두 인자인 flavor와 price가 출력됨
        System.out.println(hm.get("b")); // Snack{flavor='짠맛', price=10}
        System.out.println(hm.get("c")); // null <-없을 경우 null반환
        System.out.println();

        // 4-1.remove(Object key):V
        // 4-2.remove(Object key, Object value):default boolean
        System.out.println(hm2.remove("b")); // Snack{flavor='짠맛', price=10}
        System.out.println(hm2);    // {a=Snack{flavor='단맛', price=10}}
        System.out.println(hm2.remove("a",new Snack("단맛",10))); // true
        System.out.println(hm2);    // {}


        // 5.keySet() & entrySet()
        // keySet()
        // keySet():Set<K>
        // 맵에 있는 key들을 set에 담아 반환
        // 방법1
        System.out.println("keyset() : "+hm.keySet()); // [a, b]
        System.out.println(hm.keySet());

        // 방법2
        // set의 [] 없이 안에 값만 뽑고 싶다면 방법2 사용
        // Set객체 생성하여 map의 keySet()를 넣어주고 이를 다시 Iterator에 넣어서
        // while + hasNext()로 읽어들인다.
        // set이기 때문에 찍으면 기본적으로 key값이 나옴
        // value값을 읽어오고 싶다면 원본 데이터인 map에서 get()를 통해 끌어오면된다

        Set<String> set = hm.keySet();
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String kset = it.next();
            System.out.println(kset); // a
            System.out.println("밸류값 : "+hm.get(kset)); // 밸류값 : Snack{flavor='단맛', price=10}

        }


        // entrySet()
        // entrySet():Set<Map.Entry<K,V>>
        // map에 있는 entry들을 set 담에 반환(키와 값의 쌍을 set에 담아 반환)
        // entry 의미 : 키와 값을 묶은 것(키와 값의 쌍)
        // 방법1
        hm.entrySet();
        System.out.println(hm.entrySet());
        // [a=Snack{flavor='단맛', price=10}, b=Snack{flavor='짠맛', price=10}]
        System.out.println();

        // 방법2
        // set의 [] 없이 안에 값만 뽑고 싶다면 방법2 사용
        Set<Map.Entry<String, Snack>> setMap = hm.entrySet();
        Iterator<Map.Entry<String, Snack>> itEnt = setMap.iterator();
        while(itEnt.hasNext()){
            Map.Entry<String, Snack> a = itEnt.next();
            System.out.println(a);
//            a=Snack{flavor='단맛', price=10}
//            b=Snack{flavor='짠맛', price=10}
        }


        // size():int
        System.out.println(hm.size()); // 2


        // TreeMap
        // 정렬 가능
        // putAll()
        // putAll(Map<? extends K,? extends V> m):void
        // 다른 맵의 값을 추가
        TreeMap<String, Snack> tm = new TreeMap<>(hm);
        System.out.println(tm.get("a")); // Snack{flavor='단맛', price=10}
        System.out.println(tm);
        // {a=Snack{flavor='단맛', price=10}, b=Snack{flavor='짠맛', price=10}}
        tm.putAll(hm); // 중복 안됐음
        System.out.println(tm);
        tm.put("a",new Snack("ㄱ",10));
        System.out.println(tm); // {a=Snack{flavor='ㄱ', price=10}, b=Snack{flavor='짠맛', price=10}}
        tm.put("c", new Snack("ㄷ",10));
        System.out.println(tm); // {a=Snack{flavor='ㄱ', price=10}, b=Snack{flavor='짠맛', price=10}, c=Snack{flavor='ㄷ', price=10}}



        // remove(Object key):V
        // remove(Object key, Object value):boolean
        tm.remove("c");
        tm.remove("b",new Snack("짠맛",10));
        tm.remove("a",new Snack("짠맛",10)); // 다르게 적으면 삭제x
        System.out.println(tm); // {a=Snack{flavor='ㄱ', price=10}}

        // replace(K key, V oldValue, V newValue):boolean
        tm.replace("a",new Snack("단맛",10));
        System.out.println(tm); // {a=Snack{flavor='단맛', price=10}}
        tm.replace("a",new Snack("단맛",10),new Snack("단단맛",10));
        System.out.println(tm); // {a=Snack{flavor='단단맛', price=10}}




    }
    public void method02() {

    }

    public void method03() {

    }

    public void method04() {

    }

    public void method05() {

    }

    public void method06() {

    }

    public void method07() {

    }


    // Properties prop = new Properties();
    // key와 value를 String으로 제한시켜놓은 Map구조의 컬렉션



    // 1.HashMap
    // put(K key, V value):V
    // 반환타입 : value

    // 2.containsKey(Object key)
    // 키나 값이 들어가 있는지를 확인하는 메소드
    // containsKey(Object key):boolean
    // containsValue(Object value):boolean


    // 3.get()
    // get(Object key) : v
    // key값에 맞는 'value값 반환'

    // 4-1.remove(Object key):V
    // 4-2.remove(Object key, Object value):default boolean


    // 5.keySet() & entrySet()
    // keySet()
    // keySet():Set<K>
    // 맵에 있는 key들을 set에 담아 반환

    // 방법1
//        System.out.println(hmap.keySet()); // [사과류, 참외류]

    // 방법2
    // set의 [] 없이 안에 값만 뽑고 싶다면 방법2 사용
    // Set객체 생성하여 map의 keySet()를 넣어주고 이를 다시 Iterator에 넣어서
    // while + hasNext()로 읽어들인다.
    // set이기 때문에 찍으면 기본적으로 key값이 나옴
    // value값을 읽어오고 싶다면 원본 데이터인 map에서 get()를 통해 끌어오면된다

    // entrySet()
    // entrySet():Set<Map.Entry<K,V>>
    // map에 있는 entry들을 set 담에 반환(키와 값의 쌍을 set에 담아 반환)
    // entry 의미 : 키와 값을 묶은 것(키와 값의 쌍)

    // 방법1
//        System.out.println(hmap.entrySet());//[사과류=아오리[200원], 참외류=참외[300원]]

    // 방법2
    // set의 [] 없이 안에 값만 뽑고 싶다면 방법2 사용


    // size():int



    // TreeMap
    // 정렬 가능
    // putAll()
    // putAll(Map<? extends K,? extends V> m):void
    // 다른 맵의 값을 추가

    // remove(Object key):V
    // remove(Object key, Object value):boolean

    // replace(K key, V oldValue, V newValue):boolean





}
