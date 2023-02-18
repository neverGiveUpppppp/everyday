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
        return list;

        // 조회는 트랜잭션 처리 필요x
        // DML문만 트랜잭션 처리
    }



    public MemberJSPTable getMemberId(String mId){
        Connection con = getConnect();
        MemberJSPTable member = mDAO.getMemberId(con,mId);
        // 조회는 트랜잭션 처리 필요x
        // DML문만 트랜잭션 처리
        return member;
    }
    public MemberJSPTable getMemberNickname(String mNickname){
        Connection con = getConnect();
        MemberJSPTable member = mDAO.getMemberNickname(con, mNickname);
        // 조회는 트랜잭션 처리 필요x
        // DML문만 트랜잭션 처리
        return member;
    }
    public MemberJSPTable getMemberPhone(String mPhone){
        Connection con = getConnect();
        MemberJSPTable member = mDAO.getMemberPhone(con, mPhone);
        return member;
    }
    public MemberJSPTable getMemberAdres(String mAdres){
        Connection conn = getConnect();
        MemberJSPTable member = mDAO.getMemberAdres(conn, mAdres);
        // 조회는 트랜잭션 처리 필요x
        // DML문만 트랜잭션 처리
         return member;
    }



    public int postMember(MemberJSPTable memberVo){
        Connection con = getConnect();
        int postReslt = mDAO.postMember(con, memberVo);
        if(postReslt > 0){
            commit(con);
        }else{
            rollback(con);
        }
        return postReslt;
    }



    public int checkId(String checkId){
        Connection conn = getConnect();
        int result = mDAO.checkId(conn, checkId);
        return result;
    }

    public int putMemberPwd(String checkId, int menuNum, String putContext){
        Connection conn = getConnect();
        int result = mDAO.putMemberPwd(conn, checkId, menuNum,putContext);

        if(result > 0){
            commit(conn);
        }else{
            rollback(conn);
        }
        return result;
    }
    public int putMemberName(String checkId, int menuNum, String putContext){
        Connection conn = getConnect();
        int result = mDAO.putMemberName(conn, checkId, menuNum, putContext);

        if(result > 0){
            commit(conn);
        }else{
            rollback(conn);
        }
        return result;
    }
    public int putMemberNick(String checkId, int menuNum, String putContext){
        Connection conn = getConnect();
        int result = mDAO.putMemberNick(conn, checkId, menuNum, putContext);
        if(result > 0){
            commit(conn);
        }else{
            rollback(conn);
        }
        return result;
    }
    public int putMemberEmail(String checkId, int menuNum, String putContext) {
        Connection conn = getConnect();
        int result = mDAO.putMemberEmail(conn, checkId, menuNum, putContext);
        if(result > 0){
            commit(conn);
        }else{
            rollback(conn);
        }
        return result;
    }
















    public void exitApp(){
//        Connection con = getConnect();
//        close(con);
        close(getConnect()); // 위의 쓸데 없는 코드 없애고 간소화

    }


}
