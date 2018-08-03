package com.fcsdm.sdmserver.mvc.controller;

import java.util.List;
import java.util.StringTokenizer;

import com.fcsdm.sdmserver.mvc.model.dto.*;
import com.fcsdm.sdmserver.mvc.service.GameService;
import com.fcsdm.sdmserver.mvc.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("games")
public class GameController extends BaseController{
	
private static final Logger logger = LoggerFactory.getLogger(GameController.class);
	
	@Autowired
	GameService gameService;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "records/form", method = RequestMethod.GET)
	public String getTransactions(Model model) {
		return "game/form";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "records/form", method = RequestMethod.POST)
	public String addGame(@RequestParam("title") String title, 
			@RequestParam("place") String place, 
			@RequestParam("occur_date") String occurDate,
			@RequestParam("attendee") String attendee,
			@RequestParam("mvp") String mvp,
			@RequestParam("scorer") String scorer,
			@RequestParam("car_owner") String carOwner, Model model) {
		
		Game game = new Game();
		game.setTitle(title);
		game.setPlace(place);
		game.setOccurDate(occurDate);
		
		gameService.addGame(game);
		int seq = gameService.getLatestSeq();
		
		StringTokenizer st = null;
		
		st = new StringTokenizer(attendee);
		while(st.hasMoreTokens()) {
			String word = st.nextToken();
			Member member = memberService.getMemberByName(word);
			if(member == null) continue;
			Attendance attendance = new Attendance();
			attendance.setMatchSeq(seq); attendance.setMemberId(member.getId());
			gameService.addAttendance(attendance);
		}
		
		st = new StringTokenizer(carOwner);

		while(st.hasMoreTokens()) {
			String word = st.nextToken();	
			
			Member member = memberService.getMemberByName(word);
			if(member == null) continue;
			Carpool carpool = new Carpool();
			carpool.setMatchSeq(seq); carpool.setMemberId(member.getId());
			gameService.addCarpool(carpool);
		}
		
		st = new StringTokenizer(mvp);

		while(st.hasMoreTokens()) {
			String word = st.nextToken();			
			Member member = memberService.getMemberByName(word);
			if(member == null) continue;
			
			Record record = new Record();
			record.setMatchSeq(seq); 
			record.setMemberId(member.getId());
			record.setType("MVP"); 
			record.setTeam(member.getTeam());
			gameService.addRecord(record);			
		}
		
		st = new StringTokenizer(scorer);
		
		int score1 = 0;
		int score2 = 0;
		
		while(st.hasMoreTokens()) {
			String word = st.nextToken();			
			Member member = memberService.getMemberByName(word);
			if(member == null) continue;
			
			String value = st.nextToken();
			if("BLUE".equals(member.getTeam())) score1 += Integer.parseInt(value);
			else if("WHITE".equals(member.getTeam())) score2 += Integer.parseInt(value);
			
			Record record = new Record();
			record.setMatchSeq(seq); 
			record.setMemberId(member.getId());
			record.setType("GOAL"); 
			record.setValue(value);
			record.setTeam(member.getTeam());
			gameService.addRecord(record);			
		}
		
		Score score = new Score();
		score.setMatchSeq(seq);
		score.setTeam1("BLUE");
		score.setScore1(score1);
		score.setTeam2("WHITE");
		score.setScore2(score2);
		
		gameService.addScore(score);
		
		model.addAttribute("game", game);
		this.addFlashMessage(new FlashMessage("success", "정상적으로 등록되었습니다."));
		return "redirect:form";
	}
	
	@RequestMapping(value = "records", method = RequestMethod.GET)
	public String getLeague(Model model) {
		
		List<Score> history = gameService.getScore();
		
		League blue = new League();
		League white = new League();
		
		int win1= 0;
		int diff1 = 0;
		int goal1 = 0;
		
		int draw = 0;
		
		int win2= 0;
		int diff2 = 0;
		int goal2 = 0;
		
		int count = 0;
		
		for(int i = 0; i < history.size(); i++) {
			Score score = history.get(i);
			if(score.getScore1() > score.getScore2()) win1++;
			else if(score.getScore1() < score.getScore2()) win2++;
			else draw++;
			
			diff1 += score.getScore1() - score.getScore2(); 
			diff2 += score.getScore2() - score.getScore1();
			goal1 += score.getScore1();
			goal2 += score.getScore2();
			if(i < 5) {
				blue.setLastWin(win1);
				blue.setLastDraw(draw);
				blue.setLastLose(win2);
				white.setLastWin(win2);
				white.setLastDraw(draw);
				white.setLastLose(win1);
			}
		}
		
		blue.setTeam("청");
		blue.setPoint(win1 * 3 + draw);
		blue.setWin(win1);
		blue.setDraw(draw);
		blue.setLose(win2);
		blue.setDiff(diff1);
		blue.setGoal(goal1);
		blue.setLosePoint(goal2);
		
		white.setTeam("백");
		white.setPoint(win2 * 3 + draw);
		white.setWin(win2);
		white.setDraw(draw);
		white.setLose(win1);
		white.setDiff(diff2);
		white.setGoal(goal2);
		white.setLosePoint(goal1);
		
		model.addAttribute("count", history.size());
		
		boolean isBlue = true;
		
		if(blue.getPoint() < white.getPoint()) {
			isBlue = false;
		}
		else if(blue.getPoint() == white.getPoint()){
			if(white.getDiff() > blue.getDiff())
				isBlue = false;
		}
		
		model.addAttribute("history", history);
		model.addAttribute("first", isBlue ? blue : white);
		model.addAttribute("second", isBlue ? white : blue);
		model.addAttribute("attendRanking", gameService.getAttendRanking());
		model.addAttribute("goalRanking", gameService.getGoalRanking());
		model.addAttribute("mvpRanking", gameService.getMVPRanking());
		
		return "game/record";
	}
	
}
