package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Member2RepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional // 테스트 케이스에 있을 시 테스트 후에 rollback 해버림.
    @Rollback(value = false)
    public void testMember() throws Exception {
        //given
        Member2 member2 = new Member2();
        member2.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member2);
        Member2 findMember2 = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember2.getId()).isEqualTo(member2.getId());
        Assertions.assertThat(findMember2.getUsername()).isEqualTo(member2.getUsername());
        Assertions.assertThat(findMember2).isEqualTo(member2); // 영속성 컨택스트 관련

    }

}