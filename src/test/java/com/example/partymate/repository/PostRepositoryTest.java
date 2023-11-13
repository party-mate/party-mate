package com.example.partymate.repository;

import com.example.partymate.dto.PostIntroResponseDtoList;
import com.example.partymate.dto.PostResponseDtoList;
import com.example.partymate.dto.PostIntroResponseDto;
import com.example.partymate.dto.PostResponseDto;
import com.example.partymate.model.CaptionImage;
import com.example.partymate.model.Category;
import com.example.partymate.model.Member;
import com.example.partymate.model.Party;
import com.example.partymate.model.Post;
import com.example.partymate.testutils.config.TestConfig;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static com.example.partymate.testutils.CaptionImageHelper.generateCaptionImage;
import static com.example.partymate.testutils.CategoryHelper.generateCategory;
import static com.example.partymate.testutils.MemberHelper.generateMember;
import static com.example.partymate.testutils.PartyHelper.generateParty;
import static com.example.partymate.testutils.PostHelper.generatePost;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 * @author : Unagi_zoso
 * @date : 2023-11-08
 */

@ActiveProfiles("local")
@Import(TestConfig.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private CaptionImageRepository captionImageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntityManager entityManager;

    final static int NUM_OF_DATA = 3;

    @BeforeEach
    public void givenData(){
        for (int i = 0; i < NUM_OF_DATA; i++) {
            makeGivenData();
        }

        entityManager.flush();
    }

    @AfterEach
    public void tearDown() {
        categoryRepository.deleteAll();
        captionImageRepository.deleteAll();

        rollbackAllTableGenerateValue();
    }

    @DisplayName("post 조회 테스트")
    @Test
    public void test_findAll(){
        // given

        // when
        List<Post> posts = postRepository.findAll();

        // then
        assertEquals(NUM_OF_DATA, posts.size());
    }

    @DisplayName("partyId로 Post 조회 테스트")
    @Test
    void test_findPostByPartyId(){
        //given
        Post givenPost = postRepository.findById(1L).orElseThrow(RuntimeException::new);
        CaptionImage givenCaptionImage = captionImageRepository.findById(givenPost.getPostId()).orElseThrow(RuntimeException::new);
        Category givenCategory = categoryRepository.findById(givenPost.getPostId()).orElseThrow(RuntimeException::new);

        //when
        PostResponseDto postResponseDto = postRepository.findPostByPartyId(givenPost.getParty().getPartyId());

        //then
        assertAll(
                () -> assertEquals(givenPost.getPostId(), postResponseDto.getPostId()),
                () -> assertEquals(givenPost.getTitle(), postResponseDto.getTitle()),
                () -> assertEquals(givenPost.getMember().getNickname(), postResponseDto.getNickname()),
                () -> assertEquals(givenPost.getContent(), postResponseDto.getContent()),
                () -> assertEquals(givenCaptionImage.getImageUrl(), postResponseDto.getImageUrl()),
                () -> assertEquals(givenPost.getDuration(), postResponseDto.getDuration()),
                () -> assertEquals(givenCategory.getCategoryName(), postResponseDto.getCategoryName())
        );
    }

    @DisplayName("memberId로 Post 조회 테스트")
    @Test
    void test_findPostsByMemberId(){
        //given
        Post givenPost = postRepository.findById(1L).orElseThrow(RuntimeException::new);
        //when
        PostResponseDtoList postResponseDtoList = postRepository
                .findPostsByMemberId(givenPost.getMember().getMemberId());

        //then
        assertEquals(1, postResponseDtoList.getList().size());
    }

    @DisplayName("duration 으로 페이징 PostIntroDto 조회 테스트")
    @Test
    void test_findPagePostIntroInDuration(){
        //given
        Post givenPost = postRepository.findById(1L).orElseThrow(RuntimeException::new);
        int pageSize = 6;
        Pageable pageable = PageRequest.of(0, pageSize);

        //when
        Page<PostIntroResponseDto> postIntroPageResult = postRepository.findPagePostIntroInDuration(givenPost.getDuration(), pageable);
        PostIntroResponseDtoList postIntroResponseDtoList = new PostIntroResponseDtoList(postIntroPageResult.stream().collect(Collectors.toList()));
        PostIntroResponseDto postIntroResponseDto = postIntroResponseDtoList.get(0);

        //then
        assertEquals(NUM_OF_DATA, postIntroResponseDtoList.getSize());
        assertAll(
                () -> assertNotNull(postIntroResponseDto.getPostId()),
                () -> assertNotNull(postIntroResponseDto.getPartyId()),
                () -> assertNotNull(postIntroResponseDto.getTitle()),
                () -> assertNotNull(postIntroResponseDto.getNickname()),
                () -> assertNotNull(postIntroResponseDto.getDuration()),
                () -> assertNotNull(postIntroResponseDto.getThumbnailImageUrl()),
                () -> assertNotNull(postIntroResponseDto.getCategoryName())
        );
    }

    @DisplayName("기간 상관 없이 페이징 PostIntroDto 조회 테스트")
    @Test
    void test_findPageAllPostIntro() {
        //given
         makeGivenDataOverDuration();
        int pageSize = 6;
        Pageable pageable = PageRequest.of(0, pageSize);

        //when
        Page<PostIntroResponseDto> postIntroPageResult = postRepository.findPageAllPostIntro(pageable);
        PostIntroResponseDtoList postIntroResponseDtoList = new PostIntroResponseDtoList(postIntroPageResult.stream().collect(Collectors.toList()));
        PostIntroResponseDto postIntroResponseDto = postIntroResponseDtoList.get(0);

        //then
        assertEquals(NUM_OF_DATA + 1, postIntroResponseDtoList.getSize());
        assertAll(
                () -> assertNotNull(postIntroResponseDto.getPostId()),
                () -> assertNotNull(postIntroResponseDto.getPartyId()),
                () -> assertNotNull(postIntroResponseDto.getTitle()),
                () -> assertNotNull(postIntroResponseDto.getNickname()),
                () -> assertNotNull(postIntroResponseDto.getDuration()),
                () -> assertNotNull(postIntroResponseDto.getThumbnailImageUrl()),
                () -> assertNotNull(postIntroResponseDto.getCategoryName())
        );
    }

    private void makeGivenData() {
        Member givenMember = generateMember();
        Party givenParty = generateParty();
        Post givenPost = generatePost(givenMember, givenParty);
        CaptionImage givenCaptionImage = generateCaptionImage(givenPost);
        Category givenCategory = generateCategory(givenPost);

        captionImageRepository.save(givenCaptionImage);
        categoryRepository.save(givenCategory);
    }

    private void makeGivenDataOverDuration() {
        Member givenMember = generateMember();
        Party givenParty = generateParty();
        Post givenPost = generatePost(givenMember, givenParty, LocalDate.now().plusDays(1));
        CaptionImage givenCaptionImage = generateCaptionImage(givenPost);
        Category givenCategory = generateCategory(givenPost);

        captionImageRepository.save(givenCaptionImage);
        categoryRepository.save(givenCategory);
    }

    private void rollbackAllTableGenerateValue() {
        entityManager.createNativeQuery("ALTER TABLE post ALTER COLUMN post_id RESTART WITH 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE member ALTER COLUMN member_id RESTART WITH 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE party ALTER COLUMN party_id RESTART WITH 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE category ALTER COLUMN category_id RESTART WITH 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE caption_image ALTER COLUMN caption_image_id RESTART WITH 1").executeUpdate();
    }
}
