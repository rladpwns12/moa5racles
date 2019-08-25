package com.moa.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.moa.model.service.AndroidCreatePushTokenService;
import com.moa.model.service.AndroidPushNotificationService;
import com.moa.model.service.AndroidPushPeriodicNotification;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;


@Controller
@RequestMapping(value="/push")
public class NotificationController {
    @Autowired
    AndroidPushPeriodicNotification androidPushPeriodicNotification;
    @Autowired
    AndroidCreatePushTokenService androidCreatePushTokenService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AndroidPushNotificationService androidPushNotificationsService;

    @RequestMapping(value = "/send/{typeNum}")
    public @ResponseBody ResponseEntity<String> send(@PathVariable int typeNum) throws JSONException, InterruptedException  {
        String messageType = null;

        if(typeNum == 1){
            messageType = AndroidPushPeriodicNotification.MESSAGE_CODE_GREET;
        }
        else if(typeNum == 2){
            messageType = AndroidPushPeriodicNotification.MESSAGE_CODE_NEW_REQUEST;
        }
        else if(typeNum == 3){
            messageType = AndroidPushPeriodicNotification.MESSAGE_CODE_REQUEST_CONFIRM;
        }
        String notifications = androidPushPeriodicNotification.PeriodicNotificationJson(messageType);

        HttpEntity<String> request = new HttpEntity<>(notifications);

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try{
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        }
        catch (InterruptedException e){
            logger.debug("got interrupted!");
            throw new InterruptedException();
        }
        catch (ExecutionException e){
            logger.debug("execution error!");
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String getToken(@RequestParam String token){
        boolean res = androidCreatePushTokenService.insertPushToken(token);

        String messageType = AndroidPushPeriodicNotification.MESSAGE_CODE_GREET;

        String notifications = androidPushPeriodicNotification.PeriodicNotificationJson(messageType, token);

        HttpEntity<String> request = new HttpEntity<>(notifications);

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try{
            String firebaseResponse = pushNotification.get();
        }
        catch (InterruptedException e){
            logger.debug("got interrupted!");
        }
        catch (ExecutionException e){
            logger.debug("execution error!");
        }

        if(res) return "create token success";
        else return "fail token success";
    }
}