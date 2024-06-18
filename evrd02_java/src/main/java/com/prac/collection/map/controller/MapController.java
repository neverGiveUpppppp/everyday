package com.prac.collection.map.controller;

import com.prac.collection.map.compare.SnackComparator;
import com.prac.collection.map.model.vo.Snack;
import com.prac.collection.map.model.vo.pModelVo04;

import java.util.*;

public class MapController {

    Scanner sc = new Scanner(System.in);


    public void doMap() {

        HashMap<String, Snack> map = new HashMap<String, Snack>();
//		HashMap<String, Snack> map = new HashMap<>(); // 타입추론 적용
        // HashMap 객체 생성( 키는 String 객체가, 값은 Snack 객체가 들어갈 수 있는)

        // 1.put()
        //        // put(K key, V value):V
        // 반환값이 value
        // 같은 키가 있을 때 덮어씌워야하니까 그 이전값을 반환하는거. 지금은 새로값을 넣기에 이전 값이 없어서 null이 뜨는 것
        // 반환하는 value값이 이전값. 이전값이 없으면 null 반환.
        map.put("새우깡", new Snack("짠맛",1500)); // 결과값 : 짠맛[1500원]
        System.out.println(map.put("새우깡", new Snack("짠맛",1500))); // 결과값 : null
        System.out.println(map); // 결과값 : {새우깡=짠맛[1500원]}
        // {key=value} 형식으로 출력됨 // map도 중괄호{} 찍혀서 나온다

        map.put("새우깡",new Snack("짠맛",1500)); 	  // 순서 유지x
        map.put("다이제",new Snack("단맛",2500)); 	  // 순서 유지x
        map.put("포테이토칩",new Snack("짠맛",1500)); // 순서 유지x
        map.put("고소미",new Snack("고소한 맛",1000)); // 순서 유지x
        System.out.println(map);

        System.out.println(map.put("새우깡",new Snack("매운맛",1500))); // 같은 키값으로 새롭게 집어 넣으면 새롭게 집어놓은 value가 덮어씌움. 짠맛->매운맛 변경됨
        System.out.println(map); // 이전에 있던 밸류값이 있으면 반환하고 없으면 새로 집어 넣는거는 null값이 뜨게 된다


        // 2.containsKey(Object key)
        // 키나 값이 들어가 있는지를 확인하는 메소드
        //
        // containsKey(Object key):boolean
        // containsValue(Object value):boolean
//        System.out.println(containsKey("새우깡")); // true : key에 새우깡이 존재해서 true 반환
//        // equals()없어도 true로 나옴. str이라 그런듯
//        System.out.println(containsValue(new Snack("짠맛",1500))); // false : 주소값이 달라서 없다고 나옴
        // containsValue나 containsKey나 주소값체크하지만 key에는 String Class라 오버라이딩 되어있음
        // containsValue는 Snack 클래스라 오버라이딩x라서 false 뜬 것

        // Snack class 오버라이딩 이후 map.containsValue(new Snack("짠맛",1500)) : 결과값 true


        // 3.get()
        // get(Object key) : v
        // key값에 맞는 value값 반환
        System.out.println(map.get("새우깡")); // 결과값 : 매운맛[1500원]
        // 없는 값을 호출하면? null
        System.out.println(map.get("홈런볼")); // 결과값 : null


        // Iterator는 List와 set에서만 사용가능
        // Map에서는 사용불가
        // index가 없기에 for문 사용불가
        // 반복할려면 뭘 써야할까? iterator 사용?
        // 문제는 iterator는 리스트 셋에서만 사용가능
        // Map에서 그럼 뭘 써야할까?
        // 해결책
        // 1)keySet():Set<K>
        // 맵에 있는 key들을 set에 담아 반환
        // 2)entrySet():Set<Map.Entry<K,V>>
        //
        // 둘다 반환값이 set



        // 1)keySet():Set<K>
        // 맵에 있는 key들을 set에 담아 반환
        // keySet에 제네릭 값은 String, why?
        // 맵에 대한 제네릭은 스트링으로 위에서 지정했고 맵에 있는 키들을 set에 담아 반환하기 때문에
        // set의 제네릭에는 String 밖에 못들어감. 즉, 맵의 키값을 string으로 지정해서

        // 방법1
        System.out.println("keySet="+map.keySet()); // [다이제, 새우깡, 포테이토칩, 고소미]

        // 방법2
        Set<String> set1 = map.keySet();
        Iterator<String> it1 = set1.iterator(); // 셋안에 스트링값이 들어가 있기에 스트링을 제너릭으로
        while(it1.hasNext()) {
            String key = it1.next();
            System.out.println("ketSet:"+key+" : "+map.get(key)); // 키값을 뽑았으니 밸류값도 get()메소드로 추출
        } // ketSet:다이제 : 단맛[2500원] ketSet:새우깡 : 매운맛[1500원] ketSet:포테이토칩 : 짠맛[1500원] ketSet:고소미 : 고소한 맛[1000원]
        // Q.방법1이 쉬운데 방법2가 필요한 경우는?
        // A.방법1 keySet()) // [내, 힘, 라]의 결과값이 나오는데 set 때문에 대괄호[]에 묶여 나오고
        //	  방법2는  출력 결과(내 힘 라)를 문자열 하나씩 뺄 수 있다



        // 2)entrySet()
        // entrySet():Set<Map.Entry<K,V>>
        // entry 의미 : 키와 값을 묶은 것(키와 값의 쌍)
        // map에 있는 entry들을 set 담에 반환(키와 값의 쌍을 set에 담아 반환)

        Set<Map.Entry<String, Snack>> set2 = map.entrySet(); // java.util entry
        // <Entry<String, Snack>> : 셋안에 엔트리들이 있고 엔트리들은 키와값이 쌍이니까 키와 값의 제너릭을 각각 지정해준 것

        Iterator<Map.Entry<String, Snack>> it2 = set2.iterator(); // 이터레이너는 엔트리 안에 있는 것들을 하나하나 꺼내므로 제네릭은 Entry
        while(it2.hasNext()) {
            Map.Entry<String, Snack> entry = it2.next();
            System.out.println(entry+" : "+entry.getValue()); // 키값을 뽑았으니 밸류값도 get()메소드로 추출
        } // 엔트리 하나로 키와 밸류 둘 다 뽑는 것

        System.out.println("=====================");

        // TreeMap
        TreeMap<String, Snack> map2 = new TreeMap<>(map);
        System.out.println(map2);
        // String클래스는 comparable인터페이스를 구현했기 때문에 comparato()가 이미 오버러아딩이 되어있어서
        // key값으로 자동 정렬 가능

        TreeMap<String, Snack> map3 = new TreeMap<>(new SnackComparator());
        map3.putAll(map);
        System.out.println(map3);



        // size():int
        System.out.println(map3.size());

        // remove(Object key):V
        System.out.println(map3 );
        System.out.println(map3.remove("다이제"));
        System.out.println(map3 );

        // replace(K key, V oldValue, V newValue):boolean
        // 키 밸류 둘 다 맞아야 삭제하는 메소드
        System.out.println(map3.remove("고소미",new Snack("고소한 맛",1000)));
        System.out.println(map3);
        System.out.println(map3.remove("포테이토",new Snack("짠맛",1500))); // 둘 다 맞아야 삭제됨


        // clear():void
        map3.clear();
        System.out.println(map3); // 결과값 : {}
        // isEmpty():boolean
        System.out.println(map3.isEmpty()); // 결과값 : true

        System.out.println("=========================");
        System.out.println("========Properties=======");

    }

    public void doProperties() {

        Properties prop = new Properties();
        // key와 value를 String으로 제한시켜놓은 Map구조의 컬렉션

        prop.setProperty("채소", "오이");
        prop.setProperty("과일", "사과");
        prop.setProperty("간식", "젤리");
        prop.setProperty("채소", "피망");  // 채소,오이->피망	// 키값이 같을 경우 밸류값이 덮어쓴다.
        System.out.println(prop);		// {과일=사과, 채소=피망, 간식=젤리}

        System.out.println(prop.getProperty("채소")); // 결과값 : 피망
        System.out.println(prop.getProperty("견과")); // 결과값 : null
        System.out.println(prop.getProperty("채소","배추")); // 프로퍼티도 오버로딩이 되어있음
        System.out.println(prop.getProperty("견과","땅콩")); // 원래 null인데 땅콩이 나옴

        // getProperty(String key, String defaultValue):String
        // 		key값이 존재하면 그 key에 해당하는 value값을 반환
        // 		key값이 존재하지 않으면 defaultValue를 반환







    }



    // Map
    // {key=value} 형식으로 출력됨 // map도 중괄호{} 찍혀서 나온다
    public void pMap() {

        // 1.HashMap
        // 2.TreeMap


        // 1.HashMap
        // put(K key, V value):V
        // 반환타입 : value
        System.out.println("===HashMap===");

        HashMap<String, pModelVo04> hmap = new HashMap<>();

        hmap.put(new String("블루베리류"), new pModelVo04("블루베리", 100)); // 순서x
        hmap.put(new String("사과류"), new pModelVo04("아오리", 200));
        System.out.println(hmap);    // {사과류=아오리[200원], 블루베리류=블루베리[100원]}
        System.out.println(hmap.put(new String("참외류"), new pModelVo04("참외", 300))); // null
        System.out.println(hmap.put(new String("1"), new pModelVo04("1", 300)));    // null
        System.out.println(hmap);        // {사과류=아오리[200원], 블루베리류=블루베리[100원], 참외류=참외[300원]}


        // 2.containsKey(Object key)
        // 키나 값이 들어가 있는지를 확인하는 메소드
        // containsKey(Object key):boolean
        // containsValue(Object value):boolean

        hmap.containsKey(new String("블루베리류"));
        System.out.println(hmap);   // {사과류=아오리[200원], 1=1[300원], 블루베리류=블루베리[100원], 참외류=참외[300원]}
        System.out.println(hmap.containsKey(new String("블루베리류")));// true

        hmap.containsValue(new pModelVo04("블루베리", 100));
        System.out.println(hmap.containsValue(new pModelVo04("블루베리", 100))); // false
        System.out.println(hmap.containsValue(new pModelVo04("블루베리", 100))); // true    // model에서 오버라이딩 후 true


        // 3.get()
        // get(Object key) : v
        // key값에 맞는 'value값 반환'
        hmap.get(new String("참외류"));
        System.out.println(hmap.get(new String("사과류"))); // 아오리[200원]
        System.out.println(hmap.getClass()); // class java.util.HashMap
        System.out.println(hmap); // {사과류=아오리[200원], 1=1[300원], 블루베리류=블루베리[100원], 참외류=참외[300원]}

        System.out.println("===4.remove()======");
        // 4-1.remove(Object key):V
        System.out.println(hmap.remove("1")); // 1[300원]
        System.out.println(hmap); // {사과류=아오리[200원], 블루베리류=블루베리[100원], 참외류=참외[300원]}

        // 4-2.remove(Object key, Object value):default boolean
        System.out.println(hmap.remove(new String("블루베리류"), new pModelVo04("블루베리", 100)));//true 오바라이딩 적용
        hmap.remove(new String("블루베리류"), new pModelVo04("블루베리", 100));
        System.out.println(hmap); // {사과류=아오리[200원], 참외류=참외[300원]} 오버라이딩 적용


        // 5.keySet() & entrySet()
        System.out.println("===keySet() & entrySet()======");

        // keySet()
        // keySet():Set<K>
        // 맵에 있는 key들을 set에 담아 반환

        // 방법1
        System.out.println(hmap.keySet()); // [사과류, 참외류]

        // 방법2
        // set의 [] 없이 안에 값만 뽑고 싶다면 방법2 사용
        // Set객체 생성하여 map의 keySet()를 넣어주고 이를 다시 Iterator에 넣어서
        // while + hasNext()로 읽어들인다.
        // set이기 때문에 찍으면 기본적으로 key값이 나옴
        // value값을 읽어오고 싶다면 원본 데이터인 map에서 get()를 통해 끌어오면된다
        Set<String> sset = hmap.keySet();
        Iterator<String> is = sset.iterator();
        while (is.hasNext()) {
            String s = is.next();
            System.out.print(s + " "); // 사과류  참외류
        }
        System.out.println();


        // entrySet()
        // entrySet():Set<Map.Entry<K,V>>
        // map에 있는 entry들을 set 담에 반환(키와 값의 쌍을 set에 담아 반환)
        // entry 의미 : 키와 값을 묶은 것(키와 값의 쌍)

        // 방법1
        System.out.println(hmap.entrySet());//[사과류=아오리[200원], 참외류=참외[300원]]

        // 방법2
        // set의 [] 없이 안에 값만 뽑고 싶다면 방법2 사용
        Set<Map.Entry<String, pModelVo04>> eset = hmap.entrySet();
        Iterator<Map.Entry<String, pModelVo04>> it_eset = eset.iterator();
        while (it_eset.hasNext()) {
            Map.Entry<String, pModelVo04> a = it_eset.next();
            System.out.print(a + " ");        // 사과류=아오리[200원] 참외류=참외[300원]
        }
        System.out.println();


        // size():int
        System.out.println(hmap.size()); // 2


        // TreeMap
        // 정렬 가능
        System.out.println("===TreeMap===");

        TreeMap<String, pModelVo04> treeM = new TreeMap<>();

        treeM.put(new String("TreeMap"), new pModelVo04("Map", 1));
        System.out.println(treeM.put(new String("TreeMap"), new pModelVo04("Map", 1)));//Map[1원]
        System.out.println(treeM); // {TreeMap=Map[1원]}
        treeM.put(new String("a"), new pModelVo04("b", 2));
        System.out.println(treeM);

        // putAll()
        // putAll(Map<? extends K,? extends V> m):void
        // 다른 맵의 값을 추가
        treeM.putAll(hmap);
        System.out.println(treeM); // {TreeMap=Map[1원], 사과류=아오리[200원], 참외류=참외[300원]}

        // remove(Object key):V
        System.out.println(treeM.remove(new String("참외류"))); // 참외[300원]
        System.out.println(treeM); // {TreeMap=Map[1원], 사과류=아오리[200원]}

        // replace(K key, V oldValue, V newValue):boolean
        treeM.replace(new String("TreeMap"), new pModelVo04("Map", 1), new pModelVo04("맵", 2));
        System.out.println(treeM); // {TreeMap=맵[2원], 사과류=아오리[200원]}

        // remove(Object key, Object value):boolean
        System.out.println(treeM.remove(new String("사과류"), new pModelVo04("아오리", 200))); // true 오버라이딩 적용
        treeM.remove(new String("사과류"), new pModelVo04("아오리", 200));
        System.out.println(treeM); // {TreeMap=맵[2원]}


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
