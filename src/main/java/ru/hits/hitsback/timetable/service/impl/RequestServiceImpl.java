package ru.hits.hitsback.timetable.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.exception.AccountNotFoundException;
import ru.hits.hitsback.timetable.exception.GroupNotFoundException;
import ru.hits.hitsback.timetable.mapper.GroupChangingRequestMapper;
import ru.hits.hitsback.timetable.model.dto.request.*;
import ru.hits.hitsback.timetable.model.entity.Account;
import ru.hits.hitsback.timetable.model.entity.GroupChangingRequest;
import ru.hits.hitsback.timetable.repository.AccountRepository;
import ru.hits.hitsback.timetable.repository.GroupChangingRequestRepository;
import ru.hits.hitsback.timetable.service.RequestService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final AccountRepository accountRepository;
    private final GroupChangingRequestRepository groupChangingRequestRepository;
    private final GroupChangingRequestMapper groupChangingRequestMapper;

    @Override
    public List<RegistrationRequestDto> fetchRegistrationRequests() {
        return accountRepository.getAccountByAcceptedEquals(false).stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public List<GroupChangingRequestDto> fetchGroupChangingRequests() {
        return groupChangingRequestRepository.findAll().stream().map(groupChangingRequestMapper::toDto).collect(Collectors.toList());
    }

    @Override
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

    @Override
    public AcceptDto resolveGroupChangingRequest(GroupChangingRequestIdDto groupChangingRequestIdDto, Boolean accept) {
        AcceptDto acceptDto = new AcceptDto();
        acceptDto.setAccept(accept);
        GroupChangingRequest groupChangingRequest = groupChangingRequestRepository.findById(groupChangingRequestIdDto.getId()).orElse(null);

        if (groupChangingRequest == null) {
            throw new GroupNotFoundException();
        }

        Account account = accountRepository.getAccountById(groupChangingRequest.getAccount().getId());

        if (account == null) {
            throw new AccountNotFoundException();
        }

        if (accept) {
            account.setGroup(groupChangingRequest.getGroup());
        }

        groupChangingRequestRepository.delete(groupChangingRequest);
        accountRepository.save(account);
        return acceptDto;
    }

    private RegistrationRequestDto map(Account account) {
        RegistrationRequestDto registrationRequestDto = new RegistrationRequestDto();
        registrationRequestDto.setRegistrationRequestIdDto(new RegistrationRequestIdDto(account.getId().toString()));
        registrationRequestDto.setAccount(account.toDto());
        return registrationRequestDto;
    }
}
