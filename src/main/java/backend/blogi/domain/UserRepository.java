package backend.blogi.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<BlogUser, Long> {
    
    //oma metodi jolla tietokannasta voi etsiä käyttäjiä käyttäjänimen perusteella
    //BlogUser findByUsername(String username);

}
