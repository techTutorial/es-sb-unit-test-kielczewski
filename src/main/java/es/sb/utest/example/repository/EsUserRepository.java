package es.sb.utest.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.sb.utest.example.domain.EsUserEntity;

@Repository
public interface EsUserRepository extends JpaRepository<EsUserEntity, String> {
}
