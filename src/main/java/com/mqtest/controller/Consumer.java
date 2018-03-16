package com.mqtest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mqtest.service.ProducerService;

@RestController
@RequestMapping("/activemq")
public class Consumer {
	@Autowired
	ProducerService queueSender;
    @ResponseBody
    @RequestMapping("queue")
	public String receiveQueue(@RequestParam("message")String message) {
        String opt = "";
        for (int i = 0; i < 100; i++) {
            try {                
                queueSender.sendQueue("QueueReceiver1", message+i);
                System.out.println(message+i);
                opt = "suc";
            } catch (Exception e) {
                opt = e.getCause().toString();
            }
        }

        return opt;
	}
    @ResponseBody
    @RequestMapping("topic")
	public String receiveSub1(HttpServletRequest req) {
    	 String opt = "";
         String message = req.getParameter("message");
         //for (int i = 0; i < 100; i++) {
         try {
        	 queueSender.sendTopic("TopicReceiver1", message);
        	 queueSender.sendTopic("TopicReceiver2", message);            
             opt = "suc";
         } catch (Exception e) {
             opt = e.getCause().toString();
         }
         //}
         return opt;
     }

}