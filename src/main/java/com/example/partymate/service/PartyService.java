package com.example.partymate.service;

import com.example.partymate.model.Party;
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

    public Party saveParty(PartySaveRequestDto partySaveRequestDto) {
        return partyRepository.save(toParty(partySaveRequestDto));
    }
}
