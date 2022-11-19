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


        // Properties prop = new Properties();
        // key와 value를 String으로 제한시켜놓은 Map구조의 컬렉션
        Properties prob = new Properties();
        prob.setProperty("ㄱ","1");
        prob.setProperty("ㄴ","2");
        System.out.println(prob.getProperty("ㄱ","1")); // 1 : getProperty 밸류값1을 반환

        System.out.println(prob.get("ㄱ")); // 1 : get()은 키값으로 밸류값 가져옴
        System.out.println(prob.contains("ㄱ")); // false


        // 1.HashMap
        // put(K key, V value):V
        // 반환타입 : value
        HashMap hMap = new HashMap();
        hMap.put("ㄱ",1);
        hMap.put("ㄱㄱ","1");
        System.out.println(hMap); // {ㄱ=1, ㄱㄱ=1}
        System.out.println(hMap.put("ㄴ",2)); // null
        System.out.println(hMap); // {ㄱ=1, ㄴ=2, ㄱㄱ=1}


        // 2.containsKey(Object key)
        // 키나 값이 들어가 있는지를 확인하는 메소드
        // containsKey(Object key):boolean
        // containsValue(Object value):boolean
        hMap.containsKey("ㄴ");
        System.out.println(hMap.containsKey("ㄴ")); // true


        // 3.get()
        // get(Object key) : v
        // key값에 맞는 'value값 반환'
        System.out.println(hMap.get("ㄴ")); // 2 : ㄴ키에 밸류인 2가 반환된 것


        // 4-1.remove(Object key):V
        // 4-2.remove(Object key, Object value):default boolean
        System.out.println(hMap); // {ㄱ=1, ㄴ=2, ㄱㄱ=1}
        System.out.println(hMap.remove("ㄴ"));  // 2 : 지운 키의 밸류값 반환
        System.out.println(hMap.remove("ㄱㄱ","1")); // true


        // 5.keySet() & entrySet()
        // keySet()
        // keySet():Set<K>
        // 맵에 있는 key들을 set에 담아 반환
        System.out.println("keySet() : "+hMap.keySet()); // [ㄱ]
        // set의 []를 빼고 값을 뽑고 싶다면?


        // size():int
        System.out.println(hMap.size()); // 1


        // TreeMap
        // 정렬 가능
        // putAll()
        // putAll(Map<? extends K,? extends V> m):void
        // 다른 맵의 값을 추가
        TreeMap<String, Snack> treeMap = new TreeMap<>(hMap);
        treeMap.put("ㄴㄴ",new Snack("맛1",100));
        treeMap.put("ㄷㄷ",new Snack("맛2",200));
        System.out.println(treeMap);
        // {ㄱ=1, ㄴㄴ=Snack{flavor='맛1', price=100}, ㄷㄷ=Snack{flavor='맛2', price=200}}

        // remove(Object key):V
        // remove(Object key, Object value):boolean
        System.out.println(treeMap.remove("ㄱ")); // 1
        System.out.println(treeMap.remove("ㄷㄷ",new Snack("맛2",200))); // true

        // replace(K key, V oldValue, V newValue):boolean
        treeMap.replace("ㄴㄴ",new Snack("맛1",100),new Snack("맛11",1000));
        System.out.println(treeMap); // {ㄴㄴ=Snack{flavor='맛11', price=1000}}

    }

    public void method03() {


        // Properties prop = new Properties();
        // key와 value를 String으로 제한시켜놓은 Map구조의 컬렉션

        Properties pro = new Properties();
        pro.setProperty("a","aa");
        pro.setProperty("b","bb"); // setProperty는 str만 받음
        pro.put("c","cc");         // put은 object를 받음

        System.out.println("pro : "+pro); // {b=bb, a=aa, c=cc} : map은 순서x

        pro.getProperty("a"); // getProperty는 String만 취급
        pro.get("b"); // get은 object를 받음
        System.out.println(pro.getProperty("a")); // aa
        System.out.println(pro.get("b"));   // bb -> 해당 key의 value값 출력됨
        pro.remove("c");
        System.out.println(pro); // {b=bb, a=aa}


        // 1.HashMap
        // put(K key, V value):V
        // 반환타입 : value
        HashMap<String,Snack> hmap = new HashMap<>();
        hmap.put("A", new Snack("짠맛",100)); // list add랑 다르게 put으로 추가
        hmap.put("B",new Snack("단맛",100));
        System.out.println(hmap); // {A=Snack{flavor='짠맛', price=100}, B=Snack{flavor='단맛', price=100}}
        // Map은{key=value}으로 출력됨
        // 구분자로 나눠서 뭔가 해볼 수 있을 듯?

        // 2.containsKey(Object key)
        // 키나 값이 들어가 있는지를 확인하는 메소드
        // containsKey(Object key):boolean
        // containsValue(Object value):boolean
        hmap.containsKey("A");
        System.out.println(hmap.containsKey("A")); /// true
        System.out.println(hmap.containsValue(new Snack("단맛",100)));


        // 3.get()
        // get(Object key) : v
        // key값에 맞는 'value값 반환'
        hmap.get("B");
        System.out.println(hmap.get("A")); // Snack{flavor='짠맛', price=100}

        // 4-1.remove(Object key):V
        // 4-2.remove(Object key, Object value):default boolean
        System.out.println(hmap.remove("B")); // Snack{flavor='단맛', price=100}
        System.out.println(hmap); // {A=Snack{flavor='짠맛', price=100}} 남은 값

        // 5.keySet() & entrySet()
        // keySet()
        // keySet():Set<K>
        // 맵에 있는 key들을 set에 담아 반환

        // 방법1
        hmap.keySet();
        System.out.println(hmap.keySet()); // [A]

        // 방법2
        // set의 [] 없이 안에 값만 뽑고 싶다면 방법2 사용
        // Set객체 생성하여 map의 keySet()를 넣어주고 이를 다시 Iterator에 넣어서
        // while + hasNext()로 읽어들인다.
        // set이기 때문에 찍으면 기본적으로 key값이 나옴
        // value값을 읽어오고 싶다면 원본 데이터인 map에서 get()를 통해 끌어오면된다
        Set<String> set = hmap.keySet();
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String str = it.next();
            System.out.println("String : "+str+" // hmap.get(a) : "+hmap.get(str));
            // a : A // hmap.get(a) : Snack{flavor='짠맛', price=100}
        }



        // entrySet()
        // entrySet():Set<Map.Entry<K,V>>
        // map에 있는 entry들을 set 담에 반환(키와 값의 쌍을 set에 담아 반환)
        // entry 의미 : 키와 값을 묶은 것(키와 값의 쌍)

        // 방법1
//        System.out.println(hmap.entrySet());//[사과류=아오리[200원], 참외류=참외[300원]]
        hmap.entrySet();
        System.out.println(hmap.entrySet()); // [A=Snack{flavor='짠맛', price=100}]

        hmap.put("B",new Snack("단맛",100));
        System.out.println(hmap.entrySet());
        // [A=Snack{flavor='짠맛', price=100}, B=Snack{flavor='단맛', price=100}]


        // 방법2
        // set의 [] 없이 안에 값만 뽑고 싶다면 방법2 사용
        Set<Map.Entry<String,Snack>> setEnt = hmap.entrySet();
        Iterator<Map.Entry<String,Snack>> iter = setEnt.iterator();
        while(iter.hasNext()){
            Map.Entry<String,Snack> ent = iter.next();
            System.out.println(ent); // A=Snack{flavor='짠맛', price=100} B=Snack{flavor='단맛', price=100}
            System.out.println(ent.getValue()); // Snack{flavor='짠맛', price=100} Snack{flavor='단맛', price=100}
        }

        // size():int
        hmap.size();
        System.out.println(hmap.size()); // 2


        // TreeMap
        // 정렬 가능
        // putAll()
        // putAll(Map<? extends K,? extends V> m):void
        // 다른 맵의 값을 추가

        TreeMap<String,Snack> tmap = new TreeMap<>(hmap);
        System.out.println(tmap); // {A=Snack{flavor='짠맛', price=100}, B=Snack{flavor='단맛', price=100}}
        tmap.put("C",new Snack("신맛",100));
        tmap.put("D",new Snack("쓴맛",100));
        System.out.println(tmap);
        // {A=Snack{flavor='짠맛', price=100}, B=Snack{flavor='단맛', price=100}, C=Snack{flavor='신맛', price=100}, D=Snack{flavor='쓴맛', price=100}}
        // A,B,C,D 정렬되서 나옴

        // remove(Object key):V
        // remove(Object key, Object value):boolean
        System.out.println(tmap.remove("D")); // Snack{flavor='쓴맛', price=100}
        System.out.println(tmap.remove("C",new Snack("신맛",100))); // true
        System.out.println(tmap); // {A=Snack{flavor='짠맛', price=100}, B=Snack{flavor='단맛', price=100}}

        // replace(K key, V oldValue, V newValue):boolean
        tmap.replace("B",new Snack("단맛",100),new Snack("뉴",10));
        System.out.println(tmap);
        // {A=Snack{flavor='짠맛', price=100}, B=Snack{flavor='뉴', price=10}}
        // b의 밸류값인 snack의 맛과 가격이 변경됨
        tmap.replace("B",new Snack("뉴뉴",10000));
        System.out.println(tmap);
        // {A=Snack{flavor='짠맛', price=100}, B=Snack{flavor='뉴뉴', price=10000}}




    }

    public void method04() {


        // Properties prop = new Properties();
        // key와 value를 String으로 제한시켜놓은 Map구조의 컬렉션
        Properties pr = new Properties();
        pr.setProperty("채소","토마토");
        pr.setProperty("채소","브로콜리");
        System.out.println(pr); // {채소=브로콜리}

        pr.setProperty("과일","귤");
        System.out.println(pr); // {과일=귤, 채소=브로콜리}
        pr.setProperty("과일","사과");
        System.out.println(pr); // {과일=사과, 채소=브로콜리}



        // 1.HashMap
        // put(K key, V value):V
        // 반환타입 : value
        HashMap<Snack,String> map = new HashMap<>();
        map.put(new Snack("신맛",100),"sour");
        map.put(new Snack("짠맛",100),"salty");
        System.out.println(map); // {Snack{flavor='짠맛', price=100}=salty, Snack{flavor='신맛', price=100}=sour}

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
