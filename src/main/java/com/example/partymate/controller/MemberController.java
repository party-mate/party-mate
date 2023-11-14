package com.example.partymate.controller;

import com.example.partymate.dto.MemberSaveRequestDto;
import com.example.partymate.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Unagi_zoso
 * @since 2023-11-12
 */
@RequestMapping("/member")
@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        memberService.saveMember(memberSaveRequestDto);
        return ResponseEntity.ok().build();
    }
}
