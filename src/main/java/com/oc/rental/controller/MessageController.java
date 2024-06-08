package com.oc.rental.controller;

import com.oc.rental.dto.HttpMessageDto;
import com.oc.rental.models.Message;
import com.oc.rental.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rentals")
public class MessageController {
  private MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping("/messages")
  public HttpMessageDto createMessage(@RequestBody Message message) {
    return messageService.createMessage(message)
      .map(r -> new HttpMessageDto("Message send with success")).orElseThrow();
  }

}
