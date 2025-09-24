package backend.blogi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import backend.blogi.domain.Post;
import backend.blogi.domain.PostRepository;
import backend.blogi.domain.User;
import backend.blogi.domain.UserRepository;

@SpringBootApplication
public class BlogiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogiApplication.class, args);
	}

	//tietokannan alustaminen:
	//demodatan luominen CommandLineRunnerilla
	@Bean
	public CommandLineRunner blogCommandLineRunner(PostRepository pRepository, UserRepository uRepository) {
		return (args) -> {
			//luodaan käyttäjiä

			User u1 = uRepository.save(new User("Elli", "Esimerkki", "ellinen", "e.e@gmail.com"));
			User u2 = uRepository.save(new User("Ensio", "Esimerkki", "ensio", "e2.e@gmail.com"));
			User u3 = uRepository.save(new User("Koodi", "Koodaaja", "koodinen", "k.k@gmail.com"));

			//luodaan postauksia

			pRepository.save(new Post("Urheiluvammat", "Blogipostaustekstiä", "23.9.2025", u1));
			pRepository.save(new Post("Olkapään kuntoutus", "Blogipostaustekstiä 2", "24.9.2025", u1));
			pRepository.save(new Post("Nilkan kuntoutus", "Blogipostaustekstiä 3", "20.9.2025", u2));

		};
	}


}
