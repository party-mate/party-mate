package com.example.partymate.controller;

import com.example.partymate.dto.MemberLoginResponseDto;
import com.example.partymate.dto.MemberSaveRequestDto;
import com.example.partymate.model.Member;
import com.example.partymate.service.MemberService;
import static com.example.partymate.service.MemberService.AUTH_CODE_PREFIX;
import static com.example.partymate.service.MemberService.codeMap;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
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
        if (memberSaveRequestDto.getAuthCode() == null) {
            System.out.println("이건 무슨 헛소리야!");
            throw new IllegalArgumentException("이메일 인증을 해주세요.");
        }
        System.out.println("들어는 왔니? " + memberSaveRequestDto.getAuthCode());
        System.out.println("그럼 누가 문제니?! " + codeMap.get(AUTH_CODE_PREFIX + memberSaveRequestDto.getEmailAddress()));
        if (!memberSaveRequestDto.getAuthCode().equals(codeMap.get(AUTH_CODE_PREFIX + memberSaveRequestDto.getEmailAddress()))) {
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
        }
        // 비밀번호 암호화
        String rawPassword = memberSaveRequestDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        memberSaveRequestDto.setPassword(encPassword);

        memberService.saveMember(memberSaveRequestDto);

        return "login/loginForm";
    }

    @ResponseBody
    @GetMapping("/api/user")
    public MemberLoginResponseDto getCurrentUserDetails(@AuthenticationPrincipal String userDetails) {
        System.out.println("Hello?");
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        /*System.out.println("principal: " + principal);
        UserDetails u = (UserDetails) principal;
        System.out.println("principal: " + u.getUsername() + u.getPassword());
        if (userDetails == null) {
            System.out.println("userDetails is null");
        } */
        Member member = memberService.findMemberByEmailAddress(userDetails);
        return new MemberLoginResponseDto(member.getNickname(), "https://d3abta8yg4gqiw.cloudfront.net/profile.png");
    }

    @PostMapping("/emails/verification-requests")
    public ResponseEntity sendMessage(@RequestParam("email") String email) {
        System.out.println("WHat?!?!!!!!!!!!!!!!!!!!");
        memberService.sendCodeToEmail(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/emails/verifications")
    public ResponseEntity verificationEmail(@RequestParam("email") String email,
                                            @RequestParam("code") String authCode) {
        boolean response = memberService.verifiedCode(email, authCode);
        if (!response) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
