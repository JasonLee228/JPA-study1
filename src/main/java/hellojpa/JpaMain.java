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
            member.setTeam(team);
            em.persist(member);

            // 맴버 조회 쿼리를 확인하기 위해 em을 비워준다.
            em.flush();
            em.clear();

            // 이전과 저장, 조회 방법이 변경되었다.
            // 맴버의 팀 키를 통해 팀을 다시 조회하는 것이 아닌 맴버를 통해 팀 정보를 참조를 통해 바로 조회할 수 있다.
            // 객체 그래프 탐색
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();


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
