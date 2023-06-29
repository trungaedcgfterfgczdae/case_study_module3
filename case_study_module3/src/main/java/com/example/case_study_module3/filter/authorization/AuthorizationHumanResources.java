package com.example.case_study_module3.filter.authorization;

import com.example.case_study_module3.model.Employee;
import com.example.case_study_module3.role.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/view/hr.jsp")
public class AuthorizationHumanResources extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session=req.getSession();
        Employee employee=(Employee) session.getAttribute("employee");
        if(employee!=null){
            if(employee.getIdPosition()== Role.ROLE_HR){
                chain.doFilter(req,res);
            }else{
                res.sendRedirect("/view/notrole.jsp");
            }
        }
    }
}
