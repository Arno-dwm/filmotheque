package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bll.contexte.ContexteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContexteController {
    private ContexteService contexteService;

    public ContexteController (ContexteService contexteService){
        this.contexteService = contexteService;
    }

    @GetMapping("contexte")
    public String afficherContextes(Model model){
        return "view-contexte";
    }
}
