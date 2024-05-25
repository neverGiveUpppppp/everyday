package kr.co.polycube.backendtest.user;

public interface UserService {

    Long createUser(Users user);

    Users findOne(Long id);

    Users updateUser(Users user);


}
