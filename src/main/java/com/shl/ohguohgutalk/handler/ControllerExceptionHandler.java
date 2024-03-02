package com.shl.ohguohgutalk.handler;

import com.shl.ohguohgutalk.exception.CustomizedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestControllerAdvice(basePackages = "com.shl.ohguohgutalk.controller")
public class ControllerExceptionHandler {

    @ExceptionHandler(NoSuchMethodException.class)
    public String testExceptionHandler() {
        // TODO : NoSuchMethodException 가 controller 에서 발생 시 로직 설정
        return "";
    }

    @ExceptionHandler(CustomizedException.class)
    public ModelAndView customizedExceptionHandler(HttpServletRequest request, CustomizedException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("member", e.getMember());
        mv.addObject("errorMessage", e.getErrorMessage());
        mv.addObject("url", request.getRequestURI());
        mv.setViewName("error");
        log.info("member : {}", e.getMember());
        return mv;
    }


}
