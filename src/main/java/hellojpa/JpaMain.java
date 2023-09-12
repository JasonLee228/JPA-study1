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
