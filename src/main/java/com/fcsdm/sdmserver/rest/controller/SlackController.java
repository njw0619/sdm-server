package com.fcsdm.sdmserver.rest.controller;

import com.fcsdm.sdmserver.mvc.service.MembershipService;
import com.fcsdm.sdmserver.rest.service.SlackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

@RestController
@RequestMapping("/internal/slack")
@Slf4j
public class SlackController {

    @Autowired
    MembershipService membershipService;

    @Autowired
    SlackService slackService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${slack.api.token}")
    String token;

    @Value("${slack.test.channel.id}")
    String channelId;


    @PostMapping("poll/regular")
    public void makeRegularPollToSlack(){

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "x-www-form-urlencoded", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        params.add("channel", channelId);
        params.add("command", "/poll");
        LocalDate today = LocalDate.now();
        LocalDate thisSunday = today.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        params.add("text", "\"" + thisSunday.getMonthValue() + "/" + thisSunday.getDayOfMonth() + " (" + thisSunday.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN) + ") 22-24 정기게임\" " + "\"참석\" \"불참\" \"미정\"");
        log.info("parameter={}", params.toString());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(params, headers);

        String uri = "https://slack.com/api/chat.command";

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        log.info("response={}", response.getBody());

    }

    @PostMapping("poll/meetups")
    public void makeMeetupsPollToSlack(){

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "x-www-form-urlencoded", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        params.add("channel", channelId);
        params.add("command", "/poll");
        LocalDate today = LocalDate.now();
        params.add("text", "\"금일 축구 번개\" \"참석\" \"불참\" \"미정\"");

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(params, headers);

        String uri = "https://slack.com/api/chat.command";

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        log.info("response={}", response.getBody());
    }

    @PostMapping("notice/membership")
    public void noticeMembership(){

        String memo = membershipService.getUnpaidMemberListToString();
        String uri = "https://hooks.slack.com/services/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";

        slackService.sendMessageToSlack(uri, memo);
    }

}
