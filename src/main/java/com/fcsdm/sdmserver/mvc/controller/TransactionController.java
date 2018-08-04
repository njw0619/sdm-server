package com.fcsdm.sdmserver.mvc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fcsdm.sdmserver.mvc.model.dto.FlashMessage;
import com.fcsdm.sdmserver.mvc.model.dto.Transaction;
import com.fcsdm.sdmserver.mvc.service.TransactionService;
import com.fcsdm.sdmserver.rest.service.SlackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("transactions")
public class TransactionController extends BaseController {
	
private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	TransactionService transactionService;

	@Autowired
	SlackService slackService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getTransactions() {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String currentMonth = sdf.format(new Date());
		return "redirect:transactions/" + currentMonth;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value= "{month}", method = RequestMethod.GET)
	public String getTransactions(@PathVariable(value = "month") String month, Model model) {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String currentMonth = sdf.format(new Date());
		if(StringUtils.isEmpty(month)) {
			month = sdf.format(new Date());
		}
		String startDate = month + "01";
		String endDate = month + "99";
		int income = 0;
		int expense = 0;
		List<Transaction> transactions = transactionService.getTransactions(startDate, endDate);
		for(Transaction transaction : transactions) {
			if("I".equals(transaction.getStatus())) income += transaction.getAmount();
			else expense += transaction.getAmount();
		}
		model.addAttribute("income", income);
		model.addAttribute("expense", expense);
		model.addAttribute("transactions", transactions);
		List<Map<String, String>> months = new ArrayList<Map<String, String>>();
		for(int i = 1; i <= Integer.parseInt(currentMonth.substring(4, 6)); i++) {
			Map<String, String> monthInfo = new HashMap<String, String>();
			String yyyyMM = currentMonth.substring(0, 4) + String.format("%02d", i);
			monthInfo.put("MM", i + "월");
			monthInfo.put("yyyyMM", yyyyMM);
			if(yyyyMM.equals(month)) monthInfo.put("selected", "Y");
			else monthInfo.put("selected", "N");
			months.add(monthInfo);			
		}
		model.addAttribute("months", months);
		return "transaction/list";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String goTransactioForm() {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String currentMonth = sdf.format(new Date());
		return "transaction/form";
	}
	
	@RequestMapping(value = "form", method = RequestMethod.POST)
	public String addTransaction(@RequestParam("status") String status,
			@RequestParam("title") String title, @RequestParam("amount") int amount, 
			@RequestParam("occur_date") String occurDate, Model model) {
		Transaction transaction = new Transaction();
		transaction.setStatus(status);
		transaction.setTitle(title);
		transaction.setAmount(amount);
		transaction.setOccurDate(occurDate);
		transactionService.addTransaction(transaction);

		slackService.sendMessage("회비 사용 내역 알림\n" + title + " " + amount + "원" + ("O".equals(status) ? "지출" : "수입"));

		this.addFlashMessage(new FlashMessage("success", "정상적으로 등록되었습니다."));
		return "redirect:form";
	}
	
}
