package ru.gb.lesson6_homework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.lesson6_homework.models.Consumer;
import ru.gb.lesson6_homework.repositories.ConsumerDao;

@Service
public class ConsumerService {
    private ConsumerDao consumerDao;

    @Autowired
    public ConsumerService(ConsumerDao consumerDao) {
        this.consumerDao = consumerDao;
    }

    public Consumer getConsumerById(Long id) {
        return consumerDao.getConsumerById(id);
    }
}
