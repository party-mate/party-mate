package com.example.partymate.repository;

import com.example.partymate.model.Member;
import com.example.partymate.model.Member.MemberResponse;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-11
 */
public interface MemberCustomRepository {

    MemberResponse findMemberByEmail(String emailAddress);
    MemberResponse findMemberByPhoneNumber(String phoneNumber);
}
