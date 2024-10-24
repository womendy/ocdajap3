package com.oc.rental.service;

import com.oc.rental.models.Message;

import java.util.Optional;

public interface MessageService {

    public Optional<Message> createMessage(Message message);

}
