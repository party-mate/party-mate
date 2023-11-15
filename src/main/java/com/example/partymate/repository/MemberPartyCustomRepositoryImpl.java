package com.example.partymate.repository;

import com.example.partymate.dto.MemberPartyResponseDto;
import com.example.partymate.dto.MemberPartyResponseDtoList;
import com.example.partymate.dto.PostResponseDto;
import com.example.partymate.dto.PostResponseDtoList;
import com.example.partymate.dto.QMemberPartyResponseDto;
import static com.example.partymate.model.QCaptionImage.captionImage;
import static com.example.partymate.model.QCategory.category;
import static com.example.partymate.model.QMember.member;
import static com.example.partymate.model.QMemberParty.memberParty;
import static com.example.partymate.model.QParty.party;
import static com.example.partymate.model.QPost.post;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-12
 */
@Repository
public class MemberPartyCustomRepositoryImpl implements MemberPartyCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    public MemberPartyCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public MemberPartyResponseDtoList findPartyMembersByPartyId(Long partyId) {
        List<MemberPartyResponseDto> memberPartyResponseDtoList = jpaQueryFactory
                .select(new QMemberPartyResponseDto(
                        party.partyName,
                        member.nickname,
                        memberParty.partyMemberRole))
                .from(memberParty)
                .innerJoin(member)
                .on(memberParty.member.eq(member))
                .innerJoin(party)
                .on(memberParty.party.eq(party))
                .where(memberParty.erasedFlag.eq(0)
                        .and(memberParty.party.partyId.eq(partyId)))
                .fetch();
        return new MemberPartyResponseDtoList(memberPartyResponseDtoList);
    }

    @Override
    public boolean existMemberInParty(Long memberId, Long partyId) {
        return jpaQueryFactory
                .select(memberParty)
                .from(memberParty)
                .where(memberParty.erasedFlag.eq(0)
                        .and(memberParty.member.memberId.eq(memberId))
                        .and(memberParty.party.partyId.eq(partyId)))
                .fetchFirst() != null;
    }
}
