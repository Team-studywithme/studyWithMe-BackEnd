package team.studywithme.utils.profanity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import team.studywithme.structure.BaseDataTest;

class KoreanProfanityFilterTest extends BaseDataTest {

    @Autowired
    private KoreanProfanityFilter koreanProfanityFilter;

    @Nested
    @DisplayName("[Utils][Profanity] 욕설 필터링")
    class 욕설_필터링{

        @Test
        @DisplayName("[Utils][Profanity] 욕설 필터링 성공 테스트 - 필터링 할 나쁜단어를 포함하고 허용단어와 일치하지 않을 때")
        void 욕설_필터링_성공_테스트_필터링_할_나쁜단어를_포함하고_허용단어와_일치하지_않을_때() {
            // given
            String text = "ㅅㅂ";

            String expect = "**";

            // when
            String actual = koreanProfanityFilter.filterProfanity(text);

            // then
            Assertions.assertEquals(expect, actual);
        }

        @Test
        @DisplayName("[Utils][Profanity] 욕설 필터링 성공 테스트 - 필터링 할 나쁜단어를 포함하고 허용단어와 일치할 때")
        void 욕설_필터링_성공_테스트_필터링_할_나쁜단어를_포함하고_허용단어와_일치할_때() {
            // given
            String text = "ㅂㅅㅂ";

            String expect = "ㅂㅅㅂ";

            // when
            String actual = koreanProfanityFilter.filterProfanity(text);

            // then
            Assertions.assertEquals(expect, actual);
        }

        @Test
        @DisplayName("[Utils][Profanity] 욕설 필터링 성공 테스트 - 필터링 할 나쁜단어를 포함하고 허용단어와 일치하는 단어 사이에 특수문자가 있을 때")
        void 욕설_필터링_성공_테스트_필터링_할_나쁜단어를_포함하고_허용단어와_일치하는_단어_사이에_특수문자가_있을_때() {
            // given
            String text = "ㅅ@@ㅂ";

            String expect = "**";

            // when
            String actual = koreanProfanityFilter.filterProfanity(text);

            // then
            Assertions.assertEquals(expect, actual);
        }

        @Test
        @DisplayName("[Utils][Profanity] 욕설 필터링 성공 테스트 - 필터링 할 나쁜단어를 포함하고 허용단어와 일치하는 단어 앞에 특수문자가 있을 때")
        void 욕설_필터링_성공_테스트_필터링_할_나쁜단어를_포함하고_허용단어와_일치하는_단어_앞에_특수문자가_있을_때() {
            // given
            String text = "@@ㅅㅂ";

            String expect = "**";

            // when
            String actual = koreanProfanityFilter.filterProfanity(text);

            // then
            Assertions.assertEquals(expect, actual);
        }

        @Test
        @DisplayName("[Utils][Profanity] 욕설 필터링 성공 테스트 - 필터링 할 나쁜단어를 포함하고 허용단어와 일치하는 단어 뒤에 특수문자가 있을 때")
        void 욕설_필터링_성공_테스트_필터링_할_나쁜단어를_포함하고_허용단어와_일치하는_단어_뒤에_특수문자가_있을_때() {
            // given
            String text = "ㅅㅂ@@";

            String expect = "**";

            // when
            String actual = koreanProfanityFilter.filterProfanity(text);

            // then
            Assertions.assertEquals(expect, actual);
        }

        @Test
        @DisplayName("[Utils][Profanity] 욕설 필터링 성공 테스트 - 위의 모든 케이스가 문장으로 구성될 때")
        void 욕설_필터링_성공_테스트_위의_모든_케이스가_문장으로_구성될_때() {
            // given
            String text = "ㅅㅂ ㅂㅅㅂ ㅅ@ㅂ @@ㅅㅂ ㅅㅂ@@ @ㅅ@ㅂ@";

            String expect = "** ㅂㅅㅂ ** ** ** **";

            // when
            String actual = koreanProfanityFilter.filterProfanity(text);

            // then
            Assertions.assertEquals(expect, actual);
        }
    }
}