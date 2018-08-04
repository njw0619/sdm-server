package com.fcsdm.sdmserver.rest.controller;

import com.fcsdm.sdmserver.mvc.service.MembershipService;
import com.fcsdm.sdmserver.rest.service.SlackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

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
    private String token;

    @Value("${slack.test.channel.id}")
    private String channelId;

    @Value("${game.regular.dayOfWeek}")
    private int dayOfWeek;

    @Value("${game.regular.time}")
    private String kickoff;


    static final List<DayOfWeek> dayOfWeeks = new ArrayList<DayOfWeek>() {{
        add(DayOfWeek.SUNDAY);
        add(DayOfWeek.MONDAY);
        add(DayOfWeek.TUESDAY);
        add(DayOfWeek.WEDNESDAY);
        add(DayOfWeek.THURSDAY);
        add(DayOfWeek.FRIDAY);
        add(DayOfWeek.SATURDAY);
    }};

    @PostMapping("poll/regular")
    public void makeRegularPollToSlack(){

        LocalDate today = LocalDate.now();
        LocalDate gameDate = today.with(TemporalAdjusters.next(dayOfWeeks.get(dayOfWeek)));

        String title = gameDate.getMonthValue() + "/" + gameDate.getDayOfMonth() + " (" + gameDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN) + ") " + kickoff + " 정기게임";
        slackService.makePoll(title);

    }

    @PostMapping("poll/meetups")
    public void makeMeetupsPollToSlack(@RequestParam("token") String token, @RequestParam("text") String text){

        log.info("Slack outcomming webhook params. token={}, text={}", token, text);

        StringTokenizer st = new StringTokenizer(text, " ");

        StringBuilder gameInfo = new StringBuilder();
        while(st.hasMoreTokens()){
            String word = st.nextToken();
            if("번개해요".equals(word)) continue;
            gameInfo.append(word + " ");
        }

        String title = "번개! " + gameInfo.toString();
        slackService.makePoll(title);
    }

    @PostMapping("notice/membership")
    public void noticeMembership(){

        String memo = membershipService.getUnpaidMemberListToString();

        slackService.sendMessage(memo);
    }

}
