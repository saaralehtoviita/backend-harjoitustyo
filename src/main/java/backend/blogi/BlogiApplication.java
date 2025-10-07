package backend.blogi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import backend.blogi.domain.KeywordRepository;
import backend.blogi.domain.Keyword;
import backend.blogi.domain.Post;
import backend.blogi.domain.PostRepository;
import backend.blogi.domain.BlogUser;
import backend.blogi.domain.UserRepository;

@SpringBootApplication
public class BlogiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogiApplication.class, args);
	}

	//tietokannan alustaminen:
	//demodatan luominen CommandLineRunnerilla
	@Bean
	public CommandLineRunner blogCommandLineRunner(PostRepository pRepository, UserRepository uRepository, KeywordRepository kRepository) {
		return (args) -> {
			//luodaan avainsanoja
			Keyword knee = new Keyword("knee");
			Keyword shoulder = new Keyword("shoulder");
			Keyword crossfit = new Keyword("crossfit");
			Keyword ankle = new Keyword("ankle");
			Keyword injury = new Keyword("injury");

			//luodaan käyttäjiä

			BlogUser u1 = uRepository.save(new BlogUser("Elli", "Esimerkki", "ellinen", "e.e@gmail.com"));
			BlogUser u2 = uRepository.save(new BlogUser("Ensio", "Esimerkki", "ensio", "e2.e@gmail.com"));
			BlogUser u3 = uRepository.save(new BlogUser("Koodi", "Koodaaja", "koodinen", "k.k@gmail.com"));

			//luodaan postauksia
			//rakenne muuttuu jos many to many rakenne toteutuu postausten ja avainsanojen välillä

			Post post1 = new Post("Urheiluvammat", 
			"Blogipostaustekstiä 1: ", 
			"23.9.2025", u1);
			pRepository.save(post1);
			injury.setPostaus(post1);
			crossfit.setPostaus(post1);

			Post post2 = new Post("Olkapään kuntoutus", "Blogipostaustekstiä 2", "24.9.2025", u1);
			pRepository.save(post2);
			shoulder.setPostaus(post2);
			injury.setPostaus(post2);

			Post post3 = new Post("Nilkan kuntoutus", "Blogipostaustekstiä 3", "20.9.2025", u2);
			pRepository.save(post3);
			ankle.setPostaus(post3);

			kRepository.save(crossfit);
			kRepository.save(injury);
			kRepository.save(ankle);
			kRepository.save(shoulder);
			kRepository.save(knee);
						
		};
	}


}
