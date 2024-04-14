package com.shl.ohguohgutalk.controller;

import com.shl.ohguohgutalk.domain.LoginMember;
import com.shl.ohguohgutalk.dto.LoginDTO;
import com.shl.ohguohgutalk.dto.MemberDTO;
import com.shl.ohguohgutalk.entity.Member;
import com.shl.ohguohgutalk.exception.EmptyParameterException;
import com.shl.ohguohgutalk.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
public class MemberController {

    private final ModelMapper mapper;
    private final MemberService memberService;

    @Autowired
    public MemberController(ModelMapper mapper, MemberService memberService) {
        this.mapper = mapper;
        this.memberService = memberService;
    }

    /**
     * 로그인 화면(=첫 화면) 이동
     * @return 로그인 화면
     */
    @GetMapping({"/", "/home"})
    public String home(HttpServletRequest request, @ModelAttribute Member member, Model mv) {
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
    public String login(@Validated @ModelAttribute LoginDTO login, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        log.info("/login userId: {}, password: {}", login.getUserId(), login.getPassword());

        LoginMember loginMember = mapper.map(login, LoginMember.class);
        boolean isExistId = memberService.login(loginMember);
        if(isExistId) return "redirect:/chats";

        model.addAttribute("member", login);
        return "login";
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
    public String signUp(@ModelAttribute MemberDTO member) {
        log.info("get:/sign member : {}", member);
        return "sign";
    }

    @ResponseBody
    @GetMapping("/check/{userId}")
    public boolean checkUserId(@PathVariable @NotNull String userId) {
        if (userId.equals("null")) {
            log.info("userId is empty or null : {}", userId);
            throw new EmptyParameterException("아이디값이 비어있습니다.", userId, "home");
        }
        // validation 체크 하고 로직 만들기
        log.info("userId : {}", userId);
        return memberService.checkUserId(userId);
    }

    @PostMapping("/sign")
    public String signUpAndSelectLogin(@Validated @ModelAttribute MemberDTO member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("sign not validated : {}", bindingResult.hasErrors());
            return "sign";
        }
        Member savedMember = mapper.map(member, Member.class);
        memberService.save(savedMember);
        return "redirect:/";
    }
}
