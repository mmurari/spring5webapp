package guru.springframework.spring5webapp.controller;

import guru.springframework.spring5webapp.repository.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {
    private final AuthorRepository repo;

    public AuthorController(AuthorRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(path = "/authors")
    public String getAuthors(Model model) {
        model.addAttribute("authors", repo.findAll());
        return "authors/list";
    }
}
