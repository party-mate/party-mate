package com.example.partymate.controller;

import com.example.partymate.dto.MemberSaveRequestDto;
import com.example.partymate.dto.MemberUpdateRequestDto;
import com.example.partymate.dto.NicknameCheckRequestDto;
import com.example.partymate.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Unagi_zoso
 * @since 2023-11-12
 */
@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberController {
    private final MemberService memberService;

    @PatchMapping("/update")
    public ResponseEntity<?> updateMember(@RequestBody MemberUpdateRequestDto memberUpdateRequestDto, UserDetails userDetails) {
        memberService.updateMember(memberUpdateRequestDto, userDetails);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/checknick")
    public ResponseEntity<?> checkNickname(@RequestParam("nickname") String nickname) {
        if (memberService.checkNickname(nickname)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
