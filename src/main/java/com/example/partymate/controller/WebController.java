package com.example.partymate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Unagi_zoso
 * @since 2023-11-20
 */
@Controller
public class WebController {
    @GetMapping("/post/{postId}")
    public String getDetailPost(@PathVariable(name = "postId") Long postId, Model model) {
        model.addAttribute("postId", postId);
        return "PostDetail";
    }
}
