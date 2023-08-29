```
1. 쿼리

//            // 저장 쿼리
//            // insert 할 맴버 객체 생성
//            Member member = new Member();
//
//            // 객체 속성 설정
//            member.setId(1L);
//            member.setName("changeun");
//
//            // 저장 쿼리 ,,
//            em.persist(member);

// ---

//            // 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

// ---

//            // 삭제
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember);

// ---

//            // 수정
//            // jpa 를 통해 엔티티를 가져오면 해당 객체는 jpa 가 관리.
//            // 커밋 전에 해당 객체에 변경점 있는지 확인, 변경점 있다면 update 쿼리 전달
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("changeun");

            // 데이터베이스에 커밋(전달)
            // 모든 각각의 작업 이후 진행
            tx.commit();
```