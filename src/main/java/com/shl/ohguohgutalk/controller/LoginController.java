package com.shl.ohguohgutalk.controller;

import com.shl.ohguohgutalk.entity.Member;
import com.shl.ohguohgutalk.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Collections;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 로그인 화면(=첫 화면) 이동
     * @return 로그인 화면
     */
    @GetMapping({"/", "login"})
    public String home(HttpServletRequest request) {
        // TODO: 로그인 시 이전 url로 돌아가는 설정 필요
//        String url = request.getHeader("Referer");
//
//        if (url == null) {
//            Map<String, ?> paramMap = RequestContextUtils.getInputFlashMap(request);
//            assert paramMap != null;
//            url = (String) paramMap.get("referer");
//
//            request.getSession().setAttribute("prevPage", url);
//        } else {
//            request.getSession().setAttribute("prevPage", url);
//        }
        return "login";
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public void login() {
        // TODO: 로그인 로직 구현 필요
    }

    @GetMapping("/logout")
    public void logout() {
        // TODO: 로그아웃 로직 구현 필요
    }

    /**
     * 회원가입 화면 이동
     * @return 회원가입 화면
     */
    @GetMapping("/sign")
    public String signUp(@ModelAttribute Member member) {
        return "sign";
    }

    @PostMapping("/sign")
    public String signUpAndSelectLogin(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("sign error : {}", bindingResult.hasErrors());
            return "sign";
        }

        loginService.save(member);
        return "redirect:/login"; // getMapping으로 들어가는거 맞겠지?
    }
}
