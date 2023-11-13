package com.example.partymate.testutils;

import com.example.partymate.model.Member;
import com.example.partymate.model.Party;
import com.example.partymate.model.Post;
import static com.example.partymate.testutils.MemberHelper.generateMember;
import static com.example.partymate.testutils.PartyHelper.generateParty;
import java.time.LocalDate;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-08
 */
public class PostHelper {

    public static Post generatePost() {
        return Post.builder()
                .member(generateMember())
                .title("테스트용 제목")
                .content("테스트용 게시글")
                .duration(LocalDate.now())
                .party(generateParty())
                .build();
    }
    public static Post generatePost(Member member) {
        return Post.builder()
                .member(member)
                .title("테스트용 제목")
                .content("테스트용 게시글")
                .duration(LocalDate.now())
                .party(generateParty())
                .build();
    }

    public static Post generatePost(Party party) {
        return Post.builder()
                .member(generateMember())
                .title("테스트용 제목")
                .content("테스트용 게시글")
                .duration(LocalDate.now())
                .party(party)
                .build();
    }

    public static Post generatePost(Member member, Party party) {
        return Post.builder()
                .member(member)
                .title("테스트용 제목")
                .content("테스트용 게시글")
                .duration(LocalDate.now())
                .party(party)
                .build();
    }

    public static Post generatePost(Member member, Party party, LocalDate duration) {
        return Post.builder()
                .member(member)
                .title("테스트용 제목")
                .content("테스트용 게시글")
                .duration(duration)
                .party(party)
                .build();
    }
}
