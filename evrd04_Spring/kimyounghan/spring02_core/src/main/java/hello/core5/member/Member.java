package hello.core5.member;

import lombok.Data;

@Data
public class Member {

    private Long id;
    private String name;
    private Grade grade; // enum의 Grade

}
