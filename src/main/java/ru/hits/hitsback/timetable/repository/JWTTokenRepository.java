package ru.hits.hitsback.timetable.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.Account;
import ru.hits.hitsback.timetable.model.entity.Group;
import ru.hits.hitsback.timetable.model.entity.JWTToken;

import java.util.UUID;

public interface JWTTokenRepository extends CrudRepository<JWTToken, Long> {
    JWTToken getBySecret(String secret);

    void deleteAllBySecret(String secret);

    void deleteAllByAccount(Account account);
}
