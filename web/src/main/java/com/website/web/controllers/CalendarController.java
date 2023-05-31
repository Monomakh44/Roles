package com.website.web.controllers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.website.web.models.Home;
import com.website.web.models.Image;
import com.website.web.models.Users;
import com.website.web.repositories.HomeRepository;
import com.website.web.services.interfaces.IImageService;
import com.website.web.services.interfaces.IUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
public class CalendarController {
    @Autowired
    HomeRepository homeRepository;
    @Autowired
    private IUserService userService;
    @GetMapping("/record/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public Iterable<Home> events(@RequestParam(value = "start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @RequestParam(value = "end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
        return homeRepository.findBetween(start, end);
    }

    @PostMapping("/record/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public Home createEvent(@RequestBody EventCreateParams params, Principal principal) {
        Users user = userService.getUserByPrincipal(principal);
        Home home = new Home();
        home.setStart(params.start);
        home.setStop(params.end);
        home.setText(params.text);
        home.setUserId(user.getId());
        home.setName(user.getName());
        home.setSurname(user.getSurname());
        homeRepository.save(home);
        return home;
    }

    @PostMapping("/record/events/modal")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public Home editEventModal(@RequestBody EventModalParams params, Principal principal, Model model) {
        Users user = userService.getUserByPrincipal(principal);
        Home home = homeRepository.findById(params.id).get();
        if(home.getUserId().equals(user.getId())) {
            home.setStart(params.start);
            home.setStop(params.end);
            home.setText(params.text);
            home.setColor(params.color);
            homeRepository.save(home);
        }else model.addAttribute("errorMessage", "Отклонено, обновите страницу");
        return home;
    }

    @PostMapping("/record/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public Home moveEvent(@RequestBody EventMoveParams params, Principal principal, Model model) {
        Users user = userService.getUserByPrincipal(principal);
        Home home = homeRepository.findById(params.id).get();
        if(home.getUserId().equals(user.getId())) {
            home.setStart(params.start);
            home.setStop(params.end);
            homeRepository.save(home);
        }else model.addAttribute("errorMessage", "Отклонено, обновите страницу");
        return home;
    }

    @PostMapping("/record/events/edit")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public Home editEvent(@RequestBody EventEditParams params, Principal principal, Model model) {
        Users user = userService.getUserByPrincipal(principal);
        Home home = homeRepository.findById(params.id).get();
        if(home.getUserId().equals(user.getId())) {
            home.setText(params.text);
            homeRepository.save(home);
        }
        else model.addAttribute("errorMessage", "Отклонено, обновите страницу");
        return home;
    }


    @PostMapping("/record/events/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public Home setColor(@RequestBody SetColorParams params, Principal principal, Model model) {
        Users user = userService.getUserByPrincipal(principal);
        Home home = homeRepository.findById(params.id).get();
        if(home.getUserId().equals(user.getId())) {
            home.setColor(params.color);
            homeRepository.save(home);
        }else model.addAttribute("errorMessage", "Отклонено, обновите страницу");
        return home;
    }
    @PostMapping("/record/events/delete")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public Home deleteEvent(@RequestBody EventDeleteParams params, Principal principal, Model model) {
        Users user = userService.getUserByPrincipal(principal);
        Home home = homeRepository.findById(params.id).get();
        if(home.getUserId().equals(user.getId())) {
            homeRepository.delete(home);
        }else model.addAttribute("errorMessage", "Отклонено, обновите страницу");
        return home;
    }

    public static class EventDeleteParams {
        public Long id;
    }
    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
    }
    public static class EventModalParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public String color;
    }

    public static class EventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
    }

    public static class SetColorParams {
        public Long id;
        public String color;
    }

    public static class EventEditParams {
        public Long id;
        public String text;
    }
}
