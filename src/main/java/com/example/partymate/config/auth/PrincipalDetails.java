package com.example.partymate.config.auth;

import com.example.partymate.domain.member.Member;
import com.example.partymate.domain.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author : JJDabean
 * @date: 2023-11-12
 */


// 로그인 완료되면 시큐리티 session값에 저장 (Security ContextHolder)
// 들어가기 가능한 오브젝트 > Authentication 타입 객체
// UserDetails(Principal Details) 타입


public class PrincipalDetails implements UserDetails {

    private Member member;
//    private Role role;

    public PrincipalDetails(Member member){
        this.member = member;
//        this.role = role;
    }

    // 해당 user 권한 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getName();
            //    return role.getRoleName();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 사이트 내에서 1년이상 로그인을 하지 않으면 휴먼계정으로
        // Member 내에서 Timesamp loginDate 추가
        // member.getLoginDate();
        // 현재시간 - 로그인 시간 => 1년을 초과하면 return false로
        return true;
    }
}
