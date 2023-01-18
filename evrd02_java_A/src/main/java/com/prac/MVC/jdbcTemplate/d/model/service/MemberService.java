package com.prac.MVC.jdbcTemplate.d.model.service;

import com.prac.MVC.jdbcTemplate.d.common.TemplateMember;
import com.prac.MVC.jdbcTemplate.d.model.dao.MemberDAO;
import com.prac.MVC.jdbcTemplate.vo.MemberJSPTable;

import java.sql.Connection;
import java.util.ArrayList;

import static com.prac.MVC.jdbcTemplate.d.common.TemplateMember.close;
import static com.prac.MVC.jdbcTemplate.d.common.TemplateMember.getConnect;
import static com.prac.MVC.jdbcTemplate.d.common.TemplateMember.commit;
import static com.prac.MVC.jdbcTemplate.d.common.TemplateMember.rollback;

public class MemberService {

    MemberDAO mDAO = new MemberDAO();

    public ArrayList<MemberJSPTable> getMemberAll(){
        Connection conn = getConnect();
        ArrayList<MemberJSPTable> list = mDAO.getMemberAll(conn);

        if(list != null){
            TemplateMember.commit(conn);
        }else{
            TemplateMember.rollback(conn); // transaction 말고 stmt,rset 같은거 닫아줬었나?
        }
        return list;
    }



    public MemberJSPTable getMemberId(String mId){
        Connection con = getConnect();
        MemberJSPTable member = mDAO.getMemberId(con,mId);

        if(member != null){
            commit(con);
        }else{
            rollback(con);
        }
        return member;
    }
    public MemberJSPTable getMemberNickname(String mNickname){
        Connection con = getConnect();
        MemberJSPTable member = mDAO.getMemberNickname(con, mNickname);
        if(member != null){
            commit(con);
        }else{
            rollback(con);
        }
        return member;
    }



    public void exitApp(){
        Connection con = getConnect();
        close(con);

    }

}
