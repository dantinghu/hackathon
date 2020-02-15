package com.example.demo.Controller;

import com.example.common.ResultBase;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import com.example.demo.Utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public ResultBase doReg(@Valid User user) {
        userService.save(user);
        ResultBase rs=new ResultBase();
        rs.setToSuccess();
        return rs;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultBase doLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        ResultBase rs=new ResultBase();
        if (userService.checkLogin(username,password)) {
            User user = userService.findByUsernameAndPassword(username, password);
            UserUtil.saveUserToSession(session, user);
            rs.setToSuccess("login succeed");
            return rs;
        }
        rs.setToFail("incorrect username or password");
        return rs;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResultBase logout(HttpSession session, HttpServletResponse response) {
        UserUtil.deleteUserFromSession(session);
        ResultBase rs=new ResultBase();
        rs.setToSuccess("logout succeed");
        return rs;
    }
}
