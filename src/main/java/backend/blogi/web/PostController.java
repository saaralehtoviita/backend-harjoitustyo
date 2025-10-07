package backend.blogi.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import backend.blogi.domain.Post;
import backend.blogi.domain.PostRepository;
import jakarta.validation.Valid;

@Controller
public class PostController {

    //PostRepository rajapinnan injektointi
    //voisi myös käyttää @Autowired annotaatiota mutta ilmeisesti ei olekaan pakollinen?
    private PostRepository pRepository;

    public PostController(PostRepository pRepository) {
        this.pRepository = pRepository;
    }

    @ResponseBody
    @GetMapping("/index")
    public String testi() {
        return "Tervetuloa blogipostaus-sovellukseen";
    }

    //postilist-endpoint palauttaa postlist.html thymeleaf sivun
    //sovelluksen pääsivu, kaikilla pääsy 
    @GetMapping("/postlist")
    public String postList(Model model) {
        model.addAttribute("postaukset", pRepository.findAll());
        return "postlist";
    }

    //uusien postausten lisääminen
    //muutetaan jossain vaiheessa sellaiseksi, että vain sisäänkirjautuneet käyttäjät pääsevät lisäämään postauksia
    @RequestMapping("/addPost")
    public String addPost(Model model) {
        model.addAttribute("post", new Post());
        return "addPost";
    }

    //uusien postausten tallentaminen syötteiden validoinnilla 
    @PostMapping("/savePost")
    public String savePost(@Valid @ModelAttribute("post") Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addPost";
        }
        pRepository.save(post);
        return "redirect:postlist";
    }

    //yksittäisen postauksen näyttäminen, sisältö vaihtuu postauksen id:n mukaan
    @GetMapping("/post/{id}")
    public String showPost(@PathVariable Long id, Model model) {
        Post postaus = pRepository.findById(id).orElse(null);
        model.addAttribute("post", postaus);
        return "post";
    }


}
