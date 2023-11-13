package com.example.partymate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Unagi_zoso
 * @since 2023-11-12
 */
@RequestMapping("/member")
@Controller
public class MemberController {
    @PostMapping
    public ResponseEntity<?> createMember() {
        return ResponseEntity.ok().build();
    }
}
