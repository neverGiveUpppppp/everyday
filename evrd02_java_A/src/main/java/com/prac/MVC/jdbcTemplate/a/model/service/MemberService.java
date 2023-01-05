package main.java.com.prac.MVC.jdbcTemplate.a.model.service;

import com.prac.MVC.jdbcTemplate.a.model.dao.MemberDao;
import com.prac.MVC.jdbcTemplate.a.model.vo.MemberJSPTable;

import java.sql.Connection;

import static com.prac.MVC.jdbcTemplate.a.common.Template.*;

public class MemberService {

    MemberDao md = new MemberDao();

    public int memberInsert(MemberJSPTable member) {
        Connection con = getConnectionOuter();
        int result = md.memberInsert(member, con);

        if(result > 0){
            commit(con);
        }else{
            rollback(con);
        }
        return result;
    }

}
