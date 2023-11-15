package com.example.partymate.controller;

import com.example.partymate.dto.PartySaveRequestDto;
import com.example.partymate.service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
@RequiredArgsConstructor
@RequestMapping("/api/party")
@RestController
public class PartyController {
    private final PartyService partyService;

    @PostMapping
    public void saveParty(PartySaveRequestDto partySaveRequestDto) {
           partyService.saveParty(partySaveRequestDto);
    }
}
