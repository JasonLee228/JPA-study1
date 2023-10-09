package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // DB 커넥션이라고 생각하기. 쓰고 버려야함. 무조건.
        EntityManager em = emf.createEntityManager();

        // 트랜젝션 얻어오기
        EntityTransaction tx = em.getTransaction();
        // 트랜젝션 시작
        tx.begin();

        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeamId(team.getId());
            em.persist(member);

            // 연관관계가 없기 때문에 특정 유저의 소속 팀 정보를 얻기 위해서는 맴버를 찾고, 다시 팀아이디로 팀을 조회해야 한다.
            // 또는 조인을 통해 얻어와야 함.
            // 테이블은 외래 키로 조인을 사용해서 연관된 테이블을 찾는다.
            // ==> 객체지향스럽지 않음. 객체는 참조를 사용해서 연관된 객체를 찾아야 한다.
            // member.getTeam(); 이렇게.
            Member findMember = em.find(Member.class, member.getId());
            Long findMemberTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findMemberTeamId);

            // 데이터베이스에 커밋(전달)
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            // 작업 종료 이후 꼭 닫아야 한다.
            em.close();
        }

        // application 종료할 때 닫아줘야 함
        emf.close();
    }

}
