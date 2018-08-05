package com.fcsdm.sdmserver.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fcsdm.sdmserver.mvc.model.dto.Member;
import com.fcsdm.sdmserver.mvc.service.MemberService;
import com.fcsdm.sdmserver.mvc.service.MembershipService;
import com.fcsdm.sdmserver.rest.service.SlackService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@RestController
@RequestMapping("/internal/slack")
@Slf4j
public class SlackController {

    @Autowired
    MembershipService membershipService;

    @Autowired
    SlackService slackService;

    @Autowired
    MemberService memberService;

    @Autowired
    RestTemplate restTemplate;

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

    @PostMapping("help/instruction")
    public Map<String, String> getInstruction(){
        Map<String, String> response = new HashMap<>();
        response.put("text", "회비계좌: 회비계좌조회\n홈페이지: SDM 페이지\n번개해요: 번개출석투표 생성\n운영진: 운영진 정보 조회\n방생성: 번개방 생성");
        return response;
    }

    @PostMapping("help/manager")
    public Map<String, String> getManagerList(){
        Map<String, String> response = new HashMap<>();
        response.put("text", "2018년도 임원진\n회장: 박찬휘\n부회장: 차민수\n총무: 남재우,김무성\n고문: 김보성\n감독: 안호근,선민석");
        return response;
    }

    @PostMapping("conversation")
    public void createConversation(@RequestParam("token") String token, @RequestParam("text") String text) throws IOException, InterruptedException {

        log.info("Slack: create conversation parameter. token={}, text={}", token, text);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        StringTokenizer st = new StringTokenizer(text, " ");

        StringBuilder userList = new StringBuilder();
        while(st.hasMoreTokens()){
            String word = st.nextToken();
            if("방생성".equals(word)) continue;
            if(!StringUtils.isEmpty(userList.toString())) userList.append(",");
            userList.append(word.replaceAll("@", "").replaceAll("<", "").replaceAll(">", ""));
        }

        String title = today.format(formatter) + "-경기";

        JsonNode node = slackService.createConversation(title);
        Thread.sleep(1000);
        slackService.inviteConversation(node.get("id").toString().replace("\"", ""), userList.toString());
    }

}
