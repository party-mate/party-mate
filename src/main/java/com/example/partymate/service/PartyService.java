package com.example.partymate.service;

import static com.example.partymate.model.Party.toParty;

import com.example.partymate.dto.PartySaveRequestDto;
import com.example.partymate.repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Unagi_zoso
 * @since 2023-11-13
 */
@Service
@RequiredArgsConstructor
public class PartyService {
    private final PartyRepository partyRepository;
    private final MemberService memberService;
    private final PostService postService;

    public void saveParty(PartySaveRequestDto partySaveRequestDto) {
        partyRepository.save(toParty(partySaveRequestDto));
    }
}
