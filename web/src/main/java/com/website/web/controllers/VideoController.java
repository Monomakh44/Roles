package com.website.web.controllers;

import com.website.web.models.Url;
import com.website.web.services.interfaces.IGetImageService;
import com.website.web.services.interfaces.IUrlDownloadService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.security.Principal;
import java.util.List;

@Controller
public class VideoController {
    @Autowired
    private IGetImageService getImageService;
    @Autowired
    private IUrlDownloadService urlDownloadService;
    @SneakyThrows
    @GetMapping("/liveVideo")
    public String webCam(Model model, Principal principal) {
        getImageService.image(model, principal);
        return "LiveVideo";
    }
    @SneakyThrows
    @GetMapping("/recordVideo")
    public String webCam2(Model model, Principal principal) {
        getImageService.image(model, principal);
        Iterable<Url> url = urlDownloadService.findAllUrl();
        model.addAttribute("url",url);
        return "RecordVideo";
    }
    @RequestMapping(value="/recordVideo", method= RequestMethod.POST)
    @ResponseBody
    public String myMethod(@RequestParam("myUrl") String myUrl, Model model, Url url) {
        url.setUrl(myUrl);
        urlDownloadService.save(url);
        return "RecordVideo";
    }
}
