package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository // 컴포넌트 스캔
public class MemberRepository {

    @PersistenceContext // 주입을 해줌.
    private EntityManager em;

    public Long save(Member2 member2) {
        em.persist(member2);
        return member2.getId();
    }

    public Member2 find(Long id) {
        return em.find(Member2.class, id);
    }
}
