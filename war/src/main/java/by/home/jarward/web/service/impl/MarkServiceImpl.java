package by.home.jarward.web.service.impl;

import by.home.jarward.jar.repository.interfaces.MarkJpaRepository;
import by.home.jarward.web.service.intarfaces.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkServiceImpl implements MarkService {
    @Autowired
    MarkJpaRepository markJpaRepository;
}
