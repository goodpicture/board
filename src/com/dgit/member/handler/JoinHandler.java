package com.dgit.member.handler;

import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgit.controller.CommandHandler;
import com.dgit.jdbc.ConnectionProvider;
import com.dgit.jdbc.JdbcUtil;
import com.dgit.member.model.Member;
import com.dgit.member.model.MemberDao;

public class JoinHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		if (req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/joinForm.jsp";
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String pass = req.getParameter("password");

			Member mem = new Member(id, name, pass, new Date());

			Connection conn = null;

			try {
				conn = ConnectionProvider.getConnection();
				MemberDao dao = MemberDao.getInstance();
				//이미 존재하는 회원인지 확인
				Member existMember = dao.selectById(conn, id);
				if(existMember != null){
					req.setAttribute("duplicateId", true);
					return "/WEB-INF/view/joinForm.jsp";
				}
				
				dao.insert(conn, mem);
				
				req.setAttribute("id", id);
			} finally {
				JdbcUtil.close(conn);
			}
			
			return "/WEB-INF/view/joinSuccess.jsp";
		}
		return null;
	}

}
