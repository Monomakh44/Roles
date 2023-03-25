package com.website.web.controllers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.website.web.models.Home;
import com.website.web.repositories.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
public class CalendarController {
    @Autowired
    HomeRepository homeRepository;

    @GetMapping("/home/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public Iterable<Home> events(@RequestParam(value = "start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @RequestParam(value = "end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
        return homeRepository.findBetween(start, end);
    }

    @PostMapping("/home/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public Home createEvent(@RequestBody EventCreateParams params) {

        Home home = new Home();
        home.setStart(params.start);
        home.setStop(params.end);
        home.setText(params.text);
        homeRepository.save(home);
        return home;
    }

    @PostMapping("/home/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public Home moveEvent(@RequestBody EventMoveParams params) {

        Home home = homeRepository.findById(params.id).get();
        home.setStart(params.start);
        home.setStop(params.end);
        homeRepository.save(home);
        return home;
    }

    @PostMapping("/home/events/edit")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public Home EditEvent(@RequestBody EventEditParams params) {

        Home home = homeRepository.findById(params.id).get();
        home.setText(params.text);
        homeRepository.save(home);
        return home;
    }


    @PostMapping("/home/events/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public Home setColor(@RequestBody SetColorParams params) {

        Home home = homeRepository.findById(params.id).get();
        home.setColor(params.color);
        homeRepository.save(home);
        return home;
    }
    @PostMapping("/home/events/delete")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public Home deleteEvent(@RequestBody EventDeleteParams params) {
        Home home = homeRepository.findById(params.id).get();
        homeRepository.delete(home);
        return home;
    }

    public static class EventDeleteParams {
        public Long id;
    }
    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public Long resource;
    }

    public static class EventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public Long resource;
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
