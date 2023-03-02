package ru.hits.hitsback.timetable.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.dto.request.AcceptDto;
import ru.hits.hitsback.timetable.dto.request.GroupChangingRequestDto;
import ru.hits.hitsback.timetable.dto.request.RegistrationRequestDto;
import ru.hits.hitsback.timetable.dto.request.RegistrationRequestIdDto;
import ru.hits.hitsback.timetable.exception.AccountNotFoundException;
import ru.hits.hitsback.timetable.model.entity.Account;
import ru.hits.hitsback.timetable.repository.AccountRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final AccountRepository accountRepository;

    public List<RegistrationRequestDto> fetchRegistrationRequests() {
        return accountRepository.getAccountByAcceptedEquals(false)
                .stream().map(this::map).collect(Collectors.toList());
    }

    public List<GroupChangingRequestDto> fetchGroupChangingRequests() {
        return null;
    }

    public AcceptDto resolveRegistrationRequest(String id, Boolean accept) {
        Account account = accountRepository.getAccountById(UUID.fromString(id));
        if (account == null) {
            throw new AccountNotFoundException();
        }
        if (accept) {
            account.setAccepted(true);
            accountRepository.save(account);
        } else {
            accountRepository.delete(account);
        }
        return null;
    }

    public AcceptDto resolveGroupChangingRequest(String id, Boolean accept) {
        return null;
    }

    private RegistrationRequestDto map(Account account) {
        RegistrationRequestDto registrationRequestDto = new RegistrationRequestDto();
        registrationRequestDto.setRegistrationRequestIdDto(new RegistrationRequestIdDto(account.getId().toString()));
        registrationRequestDto.setAccount(account.toDto());
        return registrationRequestDto;
    }
}
