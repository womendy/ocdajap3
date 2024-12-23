package com.oc.rental.service.impl;

import com.oc.rental.models.Message;
import com.oc.rental.repository.impl.MessageRepository;
import com.oc.rental.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
  private MessageRepository messageRepository;

  @Autowired
  public MessageServiceImpl(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }


  @Override
  public Optional<Message> createMessage(Message message) {
    return Optional.of(messageRepository.save(message));
  }


}
