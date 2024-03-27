package batch.techtree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person person) throws Exception{
        final String firstName = person.getFirstName().toUpperCase(); /// final이 왜 필요할까?
        final String lastName = person.getLastName().toUpperCase();
        final Person transformedPerson = new Person(firstName, lastName);
        log.info("Converting (" + person + ") into (" + transformedPerson + ")");
        return transformedPerson;
    } // 읽어온 데이터를 대문자로 변환하는 역할

}

// Spring Batch에 데이터 변환처리를 담당할 클래스를 알려주어야함 : BatchConfiguration 클래스에서 구현


// 출처 : https://it-techtree.tistory.com/entry/creating-a-batchservice-using-springboot