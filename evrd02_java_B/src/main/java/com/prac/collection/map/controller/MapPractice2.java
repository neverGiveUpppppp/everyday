package com.prac.collection.map.controller;

import com.prac.collection.map.model.vo.Snack;

import java.util.HashMap;
import java.util.Properties;

public class MapPractice2 {

    public void method01(){




        // Properties prop = new Properties();
        // key와 value를 String으로 제한시켜놓은 Map구조의 컬렉션
        Properties prop = new Properties();

        prop.setProperty("1", "a");
        prop.setProperty("2", "b");
        prop.setProperty("3", "c");
        System.out.println(prop);   // {1=a, 2=b, 3=c}

        prop.setProperty("4","d");
        System.out.println(prop);   // {1=a, 2=b, 3=c, 4=d}

        prop.get("1");
        prop.getProperty("1");
        System.out.println(prop.get("1"));          // a   <- map형태가 아닌 str
        System.out.println(prop.getProperty("1"));  // a   <- map형태가 아닌 str


        // 1.HashMap
        // put(K key, V value):V
        // 반환타입 : value
        HashMap<String, Snack> hmap = new HashMap<>();
        hmap.put("1", new Snack("단맛", 100));
        hmap.put("2", new Snack("짠맛", 150));
        System.out.println(hmap);   // {1=Snack{flavor='단맛', price=100}, 2=Snack{flavor='짠맛', price=150}}

        HashMap<String, Snack> hmap2 = new HashMap<>(hmap);
        hmap2.put("3", new Snack("신맛", 200));
        hmap2.put("4", new Snack("쓴맛", 200));
        System.out.println(hmap2);  // {1=Snack{flavor='단맛', price=100}, 2=Snack{flavor='짠맛', price=150}, 3=Snack{flavor='신맛', price=200}, 4=Snack{flavor='쓴맛', price=200}}


        // 2-1.containsKey(Object key)
        // 2-2.containsValue(Object value)
        // 키나 값이 들어가 있는지를 확인하는 메소드
        // containsKey(Object key):boolean
        // containsValue(Object value):boolean



        // 3.get()
        // get(Object key) : v
        // key값에 맞는 'value값 반환'

        // 4-1.remove(Object key):V
        // 4-2.remove(Object key, Object value):default boolean
        hmap2.remove("3");
        hmap2.remove("2", new Snack("짠맛", 150));
        System.out.println(hmap2.remove("1"));              // Snack{flavor='단맛', price=100}
        System.out.println(hmap2.remove("4", new Snack("쓴맛", 200)));  // true
        System.out.println(hmap2);                                                 // {}


        // 5.keySet() & entrySet()
        // keySet()
        // keySet():Set<K>
        // 맵에 있는 key들을 Set에 담아 Set으로 반환
        System.out.println(hmap.keySet()); // [1, 2]


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

    public void method02(){

    }

    public void method03(){

    }



    // Properties prop = new Properties();
    // key와 value를 String으로 제한시켜놓은 Map구조의 컬렉션



    // 1.HashMap
    // put(K key, V value):V
    // 반환타입 : value

    // 2-1.containsKey(Object key)
    // 2-2.containsValue(Object value)
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
    // 맵에 있는 key들을 Set에 담아 Set으로 반환

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
