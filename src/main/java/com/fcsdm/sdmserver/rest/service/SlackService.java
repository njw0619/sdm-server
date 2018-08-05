package com.fcsdm.sdmserver.rest.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SlackService {

    @Autowired
    RestTemplate restTemplate;

    String token = "xoxp-409226773363-410396625607-410286296912-6d98b791c4f6af017a74a979d080459f";

    @Value("${slack.channel.id}")
    String channelId;

    public void makePoll(String title){

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "x-www-form-urlencoded", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        params.add("channel", channelId);
        params.add("command", "/poll");
        LocalDate today = LocalDate.now();
        params.add("text", "\"" + title + "\" \"참석\" \"불참\" \"미정\"");

        log.info("Slack: make poll parameters={}", params.toString());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(params, headers);

        String uri = "https://slack.com/api/chat.command";

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        log.info("Slack: make poll response={}", response.toString());

    }

    public JsonNode createConversation(String title) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "x-www-form-urlencoded", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        params.add("name", title);
        params.add("is_private", "false");

        log.info("Slack: make poll parameters={}", params.toString());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(params, headers);

        String uri = "https://slack.com/api/conversations.create";

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.getBody().toString());

        log.info("Slack: create conversation response={}", response.toString());

        return node.get("channel");

    }

    public void inviteConversation(String channelId, String userIdList){

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "x-www-form-urlencoded", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        params.add("channel", channelId);
        params.add("users", userIdList);

        log.info("Slack: make poll parameters={}", params.toString());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(params, headers);

        String uri = "https://slack.com/api/conversations.invite";

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        log.info("Slack: create conversation response={}", response.toString());

    }

    public void sendMessage(String message){

        String uri = "https://hooks.slack.com/services/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";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String params = "{\"text\":\"" + message + "\"}";

        HttpEntity<String> entity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        log.info("Send to Slack, Response={}", response.getBody());

    }
}
