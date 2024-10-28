package com.oc.rental.service;

import com.oc.rental.models.Message;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface MessageService {

    public Optional<Message> createMessage(Message message);

}
