package toy.project.suddenPoo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GlobalController {

    @Value("${map.id}")
    private String clientId;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("clientId", clientId);
        return "home";
    }
}
