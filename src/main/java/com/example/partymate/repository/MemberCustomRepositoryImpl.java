package com.example.partymate.repository;

import com.example.partymate.model.Member;
import com.example.partymate.model.Member.MemberResponse;
import com.example.partymate.model.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

/**
 * @author Unagi_zoso
 * @since 2023-11-11
 */
@Repository
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public MemberCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public MemberResponse findMemberByEmail(String emailAddress) {
        Member member = jpaQueryFactory.select(QMember.member)
                .from(QMember.member)
                .where(QMember.member.emailAddress.eq(emailAddress))
                .where(QMember.member.erasedFlag.eq(0))
                .fetchOne();
        if (member == null) {
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }
        System.out.println("나다 " + member.getMemberId() + " " + member.getNickname() + " " + member.getEmailAddress() + " " + member.getPhoneNumber());
        return new MemberResponse(member);
    }

    @Override
    public MemberResponse findMemberByPhoneNumber(String phoneNumber) {
        Member member = jpaQueryFactory.selectFrom(QMember.member)
                .where(QMember.member.phoneNumber.eq(phoneNumber))
                .where(QMember.member.erasedFlag.eq(0))
                .fetchOne();
        if (member == null) {
            throw new IllegalArgumentException("존재하지 않는 전화번호입니다.");
        }
        return new MemberResponse(member);
    }

    @Override
    public MemberResponse findMemberByNickname(String nickname) {
        Member member = jpaQueryFactory.select(QMember.member)
                .from(QMember.member)
                .where(QMember.member.nickname.eq(nickname))
                .where(QMember.member.erasedFlag.eq(0))
                .fetchOne();
        if (member == null) {
            throw new IllegalArgumentException("존재하지 않는 닉네임 입니다.");
        }
        return new MemberResponse(member);
    }

    @Override
    public boolean findMemberByNicknameForCheck(String nickname) {
        Member member = jpaQueryFactory.select(QMember.member)
                .from(QMember.member)
                .where(QMember.member.nickname.eq(nickname))
                .where(QMember.member.erasedFlag.eq(0))
                .fetchOne();
        if (member == null) {
            return false;
        }
        return true;
    }
}
