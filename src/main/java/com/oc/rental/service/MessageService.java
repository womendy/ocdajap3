package com.oc.rental.service;

import com.oc.rental.models.Message;
import com.oc.rental.models.Rental;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    public Optional<Message> createMessage(Message message);

}
