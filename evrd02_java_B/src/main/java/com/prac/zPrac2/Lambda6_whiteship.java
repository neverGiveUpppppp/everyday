package com.prac.zPrac2;


import javax.swing.plaf.metal.MetalTheme;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;

/********************************** A_Functional_Interface_Lambda_Introduce ***********************************/

@FunctionalInterface
interface Func_Inter1 {
    void doIt();
    static void printName(){
        System.out.println("static method");
    }

    default void printAge(){
        System.out.println("default method");
    }
}
interface Func_Inter2{
    int doIt(int num);
}


class Functional_class1{
    public static void main(String[] args) {
        Func_Inter1 fi1 = () -> System.out.println("Hello");
        Func_Inter1 fi2 = () -> {
            System.out.println("hello");
            System.out.println("Lambda");
        };
        Func_Inter1 fi3 = () -> System.out.println("exec");
        fi3.doIt();
    }
}

class Functional_class2{
    public static void main(String[] args) {
        Func_Inter2 fi3 = (num) -> {
            return num + 100;
        };

        System.out.println(fi3.doIt(100));
        System.out.println(fi3.doIt(100));
        System.out.println(fi3.doIt(200));
        System.out.println();
    }
}

class Ex_PureFunction1{
    public static void main(String[] args) {
        // 1)상태값을 가지고 있다, 상태값에 의존한다의 경우

        int baseNumber1 = 1; // 함수 밖
        Func_Inter2 funcInter1 = new Func_Inter2() {
            int baseNumber2 = 1; // 함수 밖. 순수함수x "상태값을 가지고있다, 상태값에 의존한다"라고 표현함

            @Override
            public int doIt(int num){
                return num + baseNumber2; // return num + baseNumber2;에서 baseNumber2 사용하는게
                // 상태값을 가지고있다, 상태값에 의존한다고 라고 말함
            }
        };
        Func_Inter2 funcInter2 = new Func_Inter2(){
            @Override
            public int doIt(int num) {
                return num + baseNumber1;
            }
        };

        // 2)외부에 있는 값을 변경할려는 경우
        int baseNum3 = 11;
        Func_Inter2 fi = new Func_Inter2(){
            int baseNum4 = 21;
            @Override
            public int doIt(int num){
//              baseNum3++;     // 자바 문법 자체로 막혀있음
                baseNum4++;     // 위처럼 문법으로 막혀있는 건 아니나 순수함수는 x
                return num + baseNum4;
            }
        };
    }
}



/********************************************** B.Java Functional Interface *********************************************************************/

// 1.Function<T, R>
//     T가 입력값 R이 리턴값
//     R apply(T t)
// 방법1
class Ex_JavaFuncIntfce1 implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}
class Ex_JFI_Embodiment{  // 구현체 : embodiment
    public static void main(String[] args) {
        Ex_JavaFuncIntfce1 func = new Ex_JavaFuncIntfce1();
        func.apply(1);
        System.out.println(func.apply(1)); // 11
    }
}


// 람다 + 내장 함수형인터페이스 Function
class Ex_Lambda1{
    public static void main(String[] args) {
        Function<Integer,Integer> function = (i) -> i + 100;
        function.apply(100);
        System.out.println(function.apply(100));
    }
}




//  default method : andThen(), compose()
class JavaFuncDefaultMethod{
    public static void main(String[] args) {
        Function<Integer,Integer> add5 = (i) -> i + 5;
        Function<Integer,Integer> multi3 = (i) -> i * 3;

        // 함수 조합하기1 : compose()
        // variable.compose() : compose() 소괄호 뒤부터 먼저 수행하고 이후에 앞에 variable을 수행함
        //      고차함수(Higher-Order Function) 성격을 가짐(함수가 함수를 매개변수로 사용하거나 함수를 리턴하는 것)

        add5.compose(multi3).apply(5);
        multi3.compose(add5).apply(5);
        System.out.println(add5.compose(multi3).apply(5)); // 20 = 5 + 15
        System.out.println(multi3.compose(add5).apply(5)); // 30 = 3 * 10


        // 함수 조합하기2 : andThen()
        // compose()와 반대순서로 계산. 뒤에다가 붙이는 것.
        add5.andThen(multi3).apply(5);
        multi3.andThen(add5).apply(5);
        System.out.println(add5.andThen(multi3).apply(5)); // 30 = 10 * 3
        System.out.println(multi3.andThen(add5).apply(5)); // 20 = 15 + 5

    }
}



// 2.BiFunction<T, U, R>
//     두 개의 값(T,U)를 받아서 R타입을 리턴하는 함수 인터페이스
//        R apply(T t, U u)
class Ex_BiFunction{
    public static void main(String[] args) {
        BiFunction<Integer,Integer,Integer> bi1 = (i,j) -> i + j;
        BiFunction<Integer,Integer,String> bi2 = (i,j) -> "str " + String.valueOf(i + j);

        bi1.apply(1,2);
        bi2.apply(3,4);
        System.out.println(bi1.apply(1,2)); // 3
        System.out.println(bi2.apply(3,4)); // str 7
    }
}


//3.Consumer<T>
//    ● T 타입을 받아서 아무값도 리턴하지 않는(void) 함수 인터페이스
//        리턴이 없기 때문에 void
//        ○ void Accept(T t)
//    ● 함수 조합용 메소드
//        ○ andThen
class Ex_Consumer{
    public static void main(String[] args) {
        Consumer<String> consumer = (str) -> System.out.println(str);

        consumer.accept("consumer.accept()");
        System.out.println(consumer);

        Consumer<String> consumer1 = (str) -> System.out.print(str);
        Consumer<String> consumer2 = (str) -> System.out.print(str);
        consumer1.accept("hello");   // hello
        System.out.println(consumer1); // com.prac.zPrac2.Consumer_$$Lambda$14/0x0000000800c01200@41629346

        consumer1.andThen(consumer2).accept("Hi"); // HiHi
    }
}


//4.Supplier<T>
//    ● T 타입의 값을 제공하는 함수 인터페이스
//        값을 하나 받아오는데 받아올 값의 타입을 지정함
//        ○ T get()
class Ex_Supplier{
    public static void main(String[] args) {
        Supplier<String> supplier1 = () -> "서플라이어";
        Supplier<Integer> supplier2 = () -> 100;

        supplier1.get();
        supplier2.get();
        System.out.println(supplier1.get());
        System.out.println(supplier2.get());
    }
}


//5.Predicate<T>
//    ● T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
//        어떤 값을 하나 받아서 true false를 리턴해줌
//        ○ startsWith()
//        ○ boolean test(T t) : t 파라미터값을 넣어서 똑같은 게 있으면 true 아니면 false
//    ● 함수 조합용 메소드
//        ○ And
//        ○ Or
//        ○ Negate

class Ex_Predicate{
    public static void main(String[] args) {
        Predicate<String> predicate_str1 = (s) -> s.equals("a");
        Predicate<String> predicate_str2 = (s) -> s.startsWith("a");
        Predicate<Integer> predicate_int1 = (i) -> i.equals(1);
        Predicate<Integer> predicate_int2 = (i) -> i % 2 == 0;

        predicate_str1.test("a");
        predicate_str2.test("a");
        predicate_int1.test(1);
        predicate_int2.test(1);
        System.out.println(predicate_str1.test("a"));    // true    // a == a
        System.out.println(predicate_str1.test("b"));    // false   // b != a
        System.out.println(predicate_str2.test("a"));    // true    // a로 시작,포함
        System.out.println(predicate_str2.test("ba"));   // false   // a가 아닌 b로 시작
        System.out.println(predicate_str2.test("abc"));  // true    // a로 시작,포함

        System.out.println(predicate_int1.test(1));      // true
        System.out.println(predicate_int2.test(1));      // false

    }
}




/********************************************** C.Lambda Expression *********************************************************************/






/***************************************** D.Method Reference ****************************************/

class MethodRef{ // vo class
    private String name;

    public MethodRef(String s){
    }

    public MethodRef() {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    // 인스턴스 메소드
    public String hello(String name) {
        return "hello! " + name;
    }

    // static한 메소드 : public static이 달림
    public static String hi(String name) {
        return "hi! " + name;
    }
}


/*
메소드 참조하는 방법
    1)스태틱 메소드 참조 (데이터타입::스태틱 메소드)
    2)특정 객체의 인스턴스 메소드 참조 (객체 레퍼런스::인스턴스 메소드)
    3)임의 객체의 인스턴스 메소드 참조 (데이터타입::인스턴스 메소드)
    4)생성자 참조 (데이터타입::new)
*/

// 1)스태틱 메소드 참조 (데이터타입::스태틱 메소드)
class Method_Ref1{
    public static void main(String[] args) {
        UnaryOperator<String> hi = MethodRef::hi;
        hi.apply("Elena");
        System.out.println(hi.apply("Elena")); // hi! Elena
    }
}


// 2)특정 객체의 인스턴스 메소드 참조 (객체 레퍼런스::인스턴스 메소드)
class Static_Method_Ref2{
    public static void main(String[] args) {
        MethodRef ref = new MethodRef();
        UnaryOperator<String> hello = ref::hello;
        hello.apply("Elain");
        System.out.println(hello.apply("Elain"));
    }
    private void prac(){
        MethodRef objRef = new MethodRef();
        UnaryOperator<String> hello = objRef::hello;
//        UnaryOperator<String> hello = objRef::hi; // 컴파일 에러 : Static method referenced through non-static qualifier
                                                    // hello만 사용가능
    }
}


// 3)임의 객체의 인스턴스 메소드 참조 (데이터타입::인스턴스 메소드)
class Method_Ref3{
    public static void main(String[] args) {
        String[] names = {"instance method", "static method", "object"};

        // 1)익명 함수
        Arrays.sort(names,new Comparator<String>(){
           @Override
           public int compare(String a, String b){
               return 0;
           }
        });

        // 2)람다
        Arrays.sort(names, (a,b) -> 0);
        System.out.println(Arrays.toString(names));

        // 3)메소드 참조
        Arrays.sort(names, String::compareToIgnoreCase);
// Arrays.sort(names, String::compareToIgnoreCase); 코드 의미 : keesun이 whiteship을 compareToIgnoreCase 파라미터에 넘겨서 int값을 리턴 받는 것
// 두번째는 whiteship이 toby랑 비교를 해서 int값을 return함
    }
}


// 4)생성자 참조 (데이터타입::new)
class Method_Ref4{
    public static void main(String[] args) {
        // 1)매개변수 없는 기본생성자 참조
        Supplier<MethodRef> constructorRef = MethodRef::new;
        MethodRef methodRef = constructorRef.get();
        System.out.println(methodRef);

        // 2)매개변수 있는 생성자 참조
        Function<String, MethodRef> function = MethodRef::new;
        function.apply("bye");
        System.out.println(function.apply("bye"));

    }
}




/***************************************** D.Method Reference ****************************************/


/*
메소드 참조하는 방법
    1)스태틱 메소드 참조 (데이터타입:스태틱 메소드)
    2)특정 객체의 인스턴스 메소드 참조 (객체 레퍼런스::인스턴스 메소드)
    3)임의 객체의 인스턴스 메소드 참조 (데이터타입::인스턴스 메소드)
    4)생성자 참조 (데이터타입::new)
 */


// 1)스태틱 메소드 참조(데이터타입::스태틱메소드)
class Static_Method_Ref_1{
    public static void main(String[] args) {
        UnaryOperator<String> hi = MethodRef::hi;
        System.out.println(hi.apply("데이터타입::스태틱메소드"));  // hi! 데이터타입::스태틱메소드
    }
}

// 2)특정한 객체의 인스턴스 메소드 참조(객체 레퍼런스::인스턴스 메소드)
class Specfic_InstanceMethod_Red_1{
    public static void main(String[] args) {
        MethodRef methodRef = new MethodRef();
        UnaryOperator<String> hello = methodRef::hello;
        System.out.println(hello.apply("객체 레퍼런스::인스턴스 메소드")); // hello! 객체 레퍼런스::인스턴스 메소드
    }
}

// 3)임의 객체의 인스턴스 메소드 참조(데이터타입::인스턴스 메소드)
class RanObj_InstanceMethod_Ref{
    public static void main(String[] args) {
        UnaryOperator<String> hello = String::toUpperCase;
        hello.apply("try");
        System.out.println(hello.apply("try")); // TRY

        Function<Integer,Integer> math = Math::abs;
        math.apply(-5);
        System.out.println(math.apply(-5)); // 5 : Math.abs()로 -5가 절대값으로 5가 됨



        String[] names = {"instance method", "static method","object"};
        // 1)익명 함수
        Arrays.sort(names, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
        // 2)람다
        Arrays.sort(names, (o1,o2) -> 0);
        // 3)메소드 참조
        Arrays.sort(names, String::compareToIgnoreCase);
    }
}


// 4)생성자 참조(데이터타입::new)
class Constructor_Ref_1{
    public static void main(String[] args) {
        // 1)기본생성자 참조
        Supplier<Greeting> greetingsSupplier = Greeting::new; // new 찍어서 어디로 가나 확인
        greetingsSupplier.get();
        System.out.println(greetingsSupplier.get()); // com.prac.zPrac2.Greeting@3b07d329


        // 2)매개변수 있는 생성자 참조
        Supplier<MethodRef> meth = MethodRef::new; // new 찍어서 어디로 가나 확인
        MethodRef methodRef = meth.get();
        System.out.println(methodRef.hello("constructor"));    // hello! constructor

        UnaryOperator<String> hello = methodRef::hello;
        hello.apply("default constructor reference");
        System.out.println(hello.apply("default constructor reference")); // hello! default constructor reference

    }
}




/***************************************** D.Method Reference ****************************************/


class GreetingD{

    private String name;

    public GreetingD(){
    }
    public GreetingD(String name){
        this.name = name;
    }

    // 인스턴스 메소드
    public String hello(String name) {
        return "hello! " + name;
    }
    // static한 메소드 : public static이 달림
    public static String hi(String name) {
        return "hi! " + name;
    }
}

/*
메소드 참조하는 방법
    1)스태틱 메소드 참조 (데이터타입:스태틱 메소드)
    2)특정 객체의 인스턴스 메소드 참조 (객체 레퍼런스::인스턴스 메소드)
    3)임의 객체의 인스턴스 메소드 참조 (데이터타입::인스턴스 메소드)
    4)생성자 참조 (데이터타입::new)
 */


// 1)스태틱 메소드 참조 (데이터타입::스태틱 메소드)
class staticMethod_Refe{
    public static void main(String[] args) {
        Function<String, String> hi = GreetingD::hi;
        hi.apply("get away");
        System.out.println(hi.apply("get away"));

        UnaryOperator<String> hi2 = GreetingD::hi;
        System.out.println(hi2.apply("UnaryOperator"));

        Function<String, String> hi3 = String::toUpperCase;
        hi3.apply("string class 타입+static메소드(?)toUpperCase");
        System.out.println(hi3.apply("string class 타입+static메소드(?)toUpperCase"));
    }
}

// 2)특정 객체의 인스턴스 메소드 참조 (객체 레퍼런스::인스턴스 메소드)
class SpecificObj_InstanceMethod{
    public static void main(String[] args) {
        GreetingD greetingD = new GreetingD();
        UnaryOperator<String> hello = greetingD::hello;
        hello.apply("go away");
        System.out.println(hello.apply("go away"));

        GreetingD greetingD1 = new GreetingD();
        Function<String, String> hello1 = greetingD1::hello;
        UnaryOperator<String> hello2 = greetingD::hello;
        hello2.apply("입력타입과 리턴타입 같은게 유너리");
        System.out.println(hello2.apply("입력타입과 리턴타입 같은게 유너리"));

        String str = new String(greetingD1.toString());
        Function<String, String> str1 = str::concat;
        str1.apply("이어붙이기 작동");
        System.out.println(str1.apply("이어붙이기 작동")); // com.prac.zPrac2.GreetingD@7cca494b이어붙이기 작동

    }
}

// 3)임의 객체의 인스턴스 메소드 참조 (데이터타입::인스턴스 메소드)
class RandObj_InstanceMethod{
    public static void main(String[] args) {
        String[] arr = {"instance method", "static method", "object","b","a"};

        // 1)익명 함수
         Arrays.sort(arr, new Comparator<String>() {
             @Override
             public int compare(String o1, String o2) {
                 return 0;
             }
         });

        // 2)람다
        Arrays.sort(arr, (o1,o2) -> 0);

        // 3)메소드 참조
        Arrays.sort(arr, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(arr)); // 정렬됨
        }
}

// 4)생성자 참조 (데이터타입::new)
class Constructor_Refer{
    public static void main(String[] args) {
        // 매개변수 없는 기본생성자 참조
        Supplier<GreetingD> func = GreetingD::new;
        func.get();
        System.out.println(func.get()); // com.prac.zPrac2.GreetingD@3b07d329

        // 매개변수 있는 생성자 참조
        Function<String, GreetingD> func2 = GreetingD::new;
        func2.apply("매개변수 있는 생성자");
        System.out.println(func2.apply("매개변수 있는 생성자")); // com.prac.zPrac2.GreetingD@404b9385
    }
}

public class Lambda6_whiteship {

}
