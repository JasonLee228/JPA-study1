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
//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());

            System.out.println("############### 1 ##############");
            System.out.println("findTeam = " + findTeam.getName());

            System.out.println();
            System.out.println();

            System.out.println("############### 2 ##############");
            System.out.println("## findTeam.getMembers() Run ##");

            List<Member> members = findTeam.getMembers();

            System.out.println();
            System.out.println();
            System.out.println("############### 3 ##############");

            int members_size = members.size();
            System.out.println("members_size = " + members_size);
            for (Member memberInTeam : members) {
                System.out.println("memberInTeam = " + memberInTeam.getName());
            }
            System.out.println("############## end #############");

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
