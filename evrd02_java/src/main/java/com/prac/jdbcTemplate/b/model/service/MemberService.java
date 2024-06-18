package com.prac.MVC.jdbcTemplate.b.model.service;

import com.prac.MVC.jdbcTemplate.b.common.TemplateMember;
import com.prac.MVC.jdbcTemplate.b.model.dao.MemberDAO;
import com.prac.MVC.jdbcTemplate.b.model.vo.MemberVO;

import java.sql.Connection;
import java.sql.SQLException;

import static com.prac.MVC.jdbcTemplate.b.common.TemplateMember.*;

/*
	   <Service의 역할>
	 1.controller로부터 인자 전달 받음
	 2.Connection 객체 생성
	 3.DAO 객체 생성 후 controller로부터 전달 받은 인자와 conn을 Service가 전달
	 4. 트랜젝션 관리,실행

*/
public class MemberService {
    MemberDAO md = new MemberDAO();
    public int insertMember(MemberVO member){
        Connection con = TemplateMember.getConnectionInner();
        int result = md.insertMember(con, member);

        if (result > 0){
            commit(con);
        }else{
            rollback(con);
        }
        return result;
    }


    public int checkYN(String userId){
        Connection con = getConnectionOuter();
        int result = md.checkYN(con, userId);
        return result;
    }
    public int updateMember(String userId, int updtMemPartNo, String updtMemPartInfo){
        Connection con = getConnectionOuter();
        int result = md.updateMember(con, userId, updtMemPartNo, updtMemPartInfo);

        if(result > 0){
            commit(con);
        }else{
            rollback(con);
        }
        return result;
    }





    public void exitApp(){
        Connection con = getConnectionOuter();
        close(con);
    }


}
