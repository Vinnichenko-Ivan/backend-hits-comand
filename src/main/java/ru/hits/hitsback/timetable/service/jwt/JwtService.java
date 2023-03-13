package ru.hits.hitsback.timetable.service.jwt;

import ru.hits.hitsback.timetable.model.entity.Account;

public interface JwtService {

    String generateToken(Account account);

    Account getAccountByToken(String token);

    void deleteAllBySecret(String token);

    void deleteAllByAccount(Account account);
}
