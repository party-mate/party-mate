package com.example.partymate.config.auth;

import com.example.partymate.repository.MemberRepository;
import com.example.partymate.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정의 loginProcessingUrl('/login');
// /login이 오면 자동으로 Service 타입으로 IoC되어있는 loadUserByUsername으로
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // security session(Authentication(UserDetails))
    @Override
    public PrincipalDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        // System.out.println(emailAddress);
        Member member = memberRepository.findByEmailAddress(emailAddress);

        if(member == null){
            System.out.println("DB에서 아무 것도 못가져옴");
        }
        System.out.println("" + member.getMemberId() + " " + member.getEmailAddress() + " " + member.getPassword() + " " + member.getRole());
        return new PrincipalDetails(member);
    }
}
