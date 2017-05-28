package com.dgit.member.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgit.controller.CommandHandler;
import com.dgit.jdbc.ConnectionProvider;
import com.dgit.jdbc.JdbcUtil;
import com.dgit.member.model.Member;
import com.dgit.member.model.MemberDao;

public class MemberHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			MemberDao dao = MemberDao.getInstance();

			List<Member> list = dao.selectByAll(conn);
			req.setAttribute("viewData", list);
			return "/WEB-INF/view/memberList.jsp";

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return null;
	}

}
