package ru.hits.hitsback.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hits.hitsback.timetable.model.entity.Account;

import java.util.UUID;
@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {

}