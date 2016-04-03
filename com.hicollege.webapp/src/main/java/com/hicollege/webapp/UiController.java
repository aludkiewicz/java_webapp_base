package com.hicollege.webapp;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/ui", method = RequestMethod.GET)
public class UiController {
    
    @RequestMapping(value = "/album", method = RequestMethod.GET)
    public String getUIAlbums() throws IOException {
        return "ui/ui_album";
    }
}