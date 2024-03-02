package com.shl.ohguohgutalk.controller;

import com.shl.ohguohgutalk.dto.MemberDTO;
import com.shl.ohguohgutalk.entity.Member;
import com.shl.ohguohgutalk.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;

@Controller
@Slf4j
@RequestMapping("/")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 로그인 화면(=첫 화면) 이동
     * @return 로그인 화면
     */
    @GetMapping("/")
    public String home(HttpServletRequest request, @ModelAttribute MemberDTO member, Model mv) {
        // TODO: 로그인 시 이전 url로 돌아가는 설정 필요
        mv.addAttribute("member", member);

        //
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
    public String login(@Validated @ModelAttribute MemberDTO member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        // dto와 domain을 나누는 이유는 필요한 걸로만 도메인을 구성하고 싶고, controller 단의 dto 에 국한되고 싶지 않기 때문임
        // 로그인 할 때 필요한거 userId, userPwd
        // id : 존재하는지 확인
        // pwd : id 존재하면 암호화해서 맞는지 확인하기
        // - functionalInterface 만들어서 sha-256 암호화 시켜서 저장하기


        // TODO: 로그인 로직 구현 필요
        return "redirect:/chats";
    }

    @GetMapping("/logout")
    public void logout() {
        // chatList.html에서 로그아웃 버튼 만들 예정
        // TODO: 로그아웃 로직 구현 필요
    }

    /**
     * 회원가입 화면 이동
     * @return 회원가입 화면
     */
    @GetMapping("/sign")
    public String signUp(@ModelAttribute Member member) {
        log.info("get:/sign member : {}", member);
        return "sign";
    }

    // TODO: userId 중복체크하는 method 만들기

    @PostMapping("/sign")
    public String signUpAndSelectLogin(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("sign not validated : {}", bindingResult.hasErrors());
            return "sign";
        }

        memberService.save(member);
        return "redirect:/";
    }
}
