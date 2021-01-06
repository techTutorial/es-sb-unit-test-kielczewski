package es.sb.utest.example.service;

import java.util.List;

import es.sb.utest.example.domain.EsUserEntity;

public interface EsUserService {

    EsUserEntity saveUser(EsUserEntity user);

    List<EsUserEntity> getAllUsers();

}
