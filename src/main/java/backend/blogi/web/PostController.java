package backend.blogi.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import backend.blogi.domain.PostRepository;

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

    @GetMapping("/postlist")
    public String postList(Model model) {
        model.addAttribute("postaukset", pRepository.findAll());
        return "postlist";
    }

    

}
