package com.example.partymate.repository;

import com.example.partymate.dto.PostIntroResponseDtoList;
import com.example.partymate.dto.PostResponseDtoList;
import com.example.partymate.dto.PostIntroResponseDto;
import com.example.partymate.dto.PostResponseDto;
import com.example.partymate.dto.QPostIntroResponseDto;
import com.example.partymate.dto.QPostResponseDto;
import static com.example.partymate.model.QCaptionImage.captionImage;
import static com.example.partymate.model.QCategory.category;
import static com.example.partymate.model.QParty.party;
import static com.example.partymate.model.QPost.post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Unagi_zoso
 * @since 2023-11-08
 */
@Repository
public class PostCustomRepositoryImpl implements PostCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public PostCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public PostResponseDto findPostByPartyId(Long partyId) {
        return jpaQueryFactory
                .select(new QPostResponseDto(
                        post.postId,
                        post.title,
                        post.member.nickname,
                        post.content,
                        captionImage.imageUrl,
                        post.duration,
                        category.categoryName))
                .from(post)
                .leftJoin(captionImage)
                .on(post.eq(captionImage.post))
                .leftJoin(category)
                .on(post.eq(category.post))
                .where(post.party.partyId.eq(partyId)
                        .and(post.erasedFlag.eq(0)))
                .fetchOne();
    }

    @Override
    public PostResponseDtoList findPostsByMemberId(Long memberId) {
        List<PostResponseDto> postResponseDtoList = jpaQueryFactory
                .select(new QPostResponseDto(
                        post.postId,
                        post.title,
                        post.member.nickname,
                        post.content,
                        captionImage.imageUrl,
                        post.duration,
                        category.categoryName))
                .from(post)
                .leftJoin(captionImage)
                .on(post.eq(captionImage.post))
                .leftJoin(category)
                .on(post.eq(category.post))
                .where(post.member.memberId.eq(memberId)
                        .and(post.erasedFlag.eq(0)))
                .fetch();
        return new PostResponseDtoList(postResponseDtoList);
    }

    @Override
    public Page<PostIntroResponseDto> findPagePostIntroInDuration(LocalDate duration, Pageable pageable) {
        PostIntroResponseDtoList postIntroResponseDtoList  = new PostIntroResponseDtoList(jpaQueryFactory
                .select(new QPostIntroResponseDto(
                        post.postId,
                        post.party.partyId,
                        post.title,
                        post.member.nickname,
                        post.duration,
                        captionImage.thumbnailImageUrl,
                        party.currentMemberCount,
                        party.maxPartyMemberCount,
                        category.categoryName))
                .from(post)
                .leftJoin(captionImage)
                .on(post.eq(captionImage.post))
                .leftJoin(category)
                .on(post.eq(category.post))
                .leftJoin(party)
                .on(party.eq(post.party))
                .where((post.duration.before(duration)
                        .or(post.duration.eq(duration)))
                        .and(post.erasedFlag.eq(0)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch());

        Long count = jpaQueryFactory
                .select(post.count())
                .from(post)
                .where((post.duration.before(duration)
                        .or(post.duration.eq(duration)))
                        .and(post.erasedFlag.eq(0))).fetchOne();

        return new PageImpl<>(postIntroResponseDtoList.getImmutableList(), pageable, count == null ? 0 : count);
    }

    @Override
    public Page<PostIntroResponseDto> findPageAllPostIntro(Pageable pageable) {
        PostIntroResponseDtoList postIntroResponseDtoList  = new PostIntroResponseDtoList(jpaQueryFactory
                .select(new QPostIntroResponseDto(
                        post.postId,
                        post.party.partyId,
                        post.title,
                        post.member.nickname,
                        post.duration,
                        captionImage.thumbnailImageUrl,
                        party.currentMemberCount,
                        party.maxPartyMemberCount,
                        category.categoryName))
                .from(post)
                .leftJoin(captionImage)
                .on(post.eq(captionImage.post))
                .leftJoin(category)
                .on(post.eq(category.post))
                .leftJoin(party)
                .on(party.eq(post.party))
                .where(post.erasedFlag.eq(0))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch());

        Long count = jpaQueryFactory
                .select(post.count())
                .from(post)
                .where(post.erasedFlag.eq(0))
                .fetchOne();

        return new PageImpl<>(postIntroResponseDtoList.getImmutableList(), pageable, count == null ? 0 : count);
    }
}
