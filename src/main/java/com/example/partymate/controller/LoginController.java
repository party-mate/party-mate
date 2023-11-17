package com.example.partymate.controller;

import com.example.partymate.dto.MemberSaveRequestDto;
import com.example.partymate.model.Member;
import com.example.partymate.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JJDabean
 * @since 2023-11-8
 */
@RequiredArgsConstructor
@Controller
public class LoginController {
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    // 스프링시큐리티에서 해당 주소를 낚아 챈다 > SecurityConfig 파일 생성후 작동 안함
    @GetMapping("/loginForm")
    public String loginForm(){
        return "login/loginForm";
    }

    @GetMapping("/signup")
    public String joinForm(){
        return "login/joinForm";
    }

//    @PostMapping("/in")
//    public String loginFinish(HttpServletRequest request, Member member){
//    //    List<> = memberService.selectOne(member.getMemberId());
//        System.out.println(memberService);
//        System.out.println(member);
//
//        return "in";
//    }

    @PostMapping("/join")
    public String join(@RequestBody MemberSaveRequestDto memberSaveRequestDto){
        // 비밀번호 암호화
        String rawPassword = memberSaveRequestDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        memberSaveRequestDto.setPassword(encPassword);

        memberService.saveMember(memberSaveRequestDto);

        return "login/loginForm";
    }
}
