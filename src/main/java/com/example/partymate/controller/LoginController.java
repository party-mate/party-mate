package com.example.partymate.controller;

import com.example.partymate.domain.member.Member;
import com.example.partymate.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : JJDabean
 * @date : 2023-11-8
 */
@RequiredArgsConstructor
@Controller
public class LoginController {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"","/"})
    public String index(){
        return "home";
    }

    // 스프링시큐리티에서 해당 주소를 낚아 챈다 > SecurityConfig 파일 생성후 작동 안함
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

//    @RequestMapping(value="/member/checkNick", produces = "application/text;charset=utf8")
//    @ResponseBody
//    public String checkNick(String nickname){
//        if(memberService.checkNick(nickname) != null) {
//            return "이미 사용중인 닉네임입니다.";
//        }else {
//            return "사용 가능한 닉네임입니다";
//        }
//    }

    @PostMapping("/join")
    public String join(Member member){
        System.out.println(member);
        // 비밀번호 암호화
        String rawPassword = member.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        member.setPassword(encPassword);

        // agreeMarketing 클릭되지 않았으면 null값대신 0으로 넣기
    //    int agreeM = member.getAgreeMarketingFlag();

        memberRepository.save(member);
        System.out.println(member);
        return "redirect:/loginForm";
    }
}
