package ru.hits.hitsback.timetable.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hits.hitsback.timetable.model.entity.Account;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {
    Account getAccountByEmail(String email);

    Account getAccountById(UUID uuid);

    List<Account> getAccountByAcceptedEquals(Boolean accepted);
}