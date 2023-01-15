package com.prac.MVC.jdbcTemplate.d.model.service;

import com.prac.MVC.jdbcTemplate.d.common.TemplateMember;
import com.prac.MVC.jdbcTemplate.d.model.dao.MemberDAO;
import com.prac.MVC.jdbcTemplate.vo.MemberJSPTable;

import java.sql.Connection;
import java.util.ArrayList;

import static com.prac.MVC.jdbcTemplate.d.common.TemplateMember.close;

public class MemberService {

    MemberDAO mDAO = new MemberDAO();

    public ArrayList<MemberJSPTable> getMemberAll(){
        Connection conn = TemplateMember.getConnect();
        ArrayList<MemberJSPTable> list = mDAO.getMemberAll(conn);

        if(list != null){
            TemplateMember.commit(conn);
        }else{
            TemplateMember.rollback(conn); // transaction 말고 stmt,rset 같은거 닫아줬었나?
        }
        return list;
    }





    public void exitApp(){
        Connection connection = null;
        close(connection);

    }

}
