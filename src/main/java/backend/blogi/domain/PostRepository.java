package backend.blogi.domain;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

    //tänne tulisi esim oma metodi, jolla voi etsiä postauksia keywordin perusteella
    //List<Post> findByKeyword(String strKeyword)
}
