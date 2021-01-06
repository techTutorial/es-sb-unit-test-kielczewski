package es.sb.utest.example.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import es.sb.utest.example.domain.EsUserEntity;
import es.sb.utest.example.repository.EsUserRepository;
import es.sb.utest.example.service.EsUserService;
import es.sb.utest.example.service.exception.EsUserAlreadyExistsException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class EsUserServiceImpl implements EsUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsUserServiceImpl.class);
    
    @Autowired
    private EsUserRepository userRepo;

    @Override
    @Transactional
    public EsUserEntity saveUser(@NotNull @Valid EsUserEntity user) {
        LOGGER.debug("Creating {}", user);
        EsUserEntity existing = userRepo.findOne(String.valueOf(user.getUserId()));
        if (existing != null) {
            throw new EsUserAlreadyExistsException(
                    String.format("User already exists with id=%s", user.getUserId()));
        }
        return userRepo.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EsUserEntity> getAllUsers() {
        LOGGER.debug("Retrieving the list of all users");
        return userRepo.findAll();
    }

}
