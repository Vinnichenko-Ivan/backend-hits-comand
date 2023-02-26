package ru.hits.hitsback.timetable.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.dto.authorisation.CredentialsDto;
import ru.hits.hitsback.timetable.dto.authorisation.TokenDto;
import ru.hits.hitsback.timetable.exception.UnauthorizedException;
import ru.hits.hitsback.timetable.model.entity.Account;
import ru.hits.hitsback.timetable.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository accountRepository;

    private final JwtService jwtService;

    public TokenDto singIn(CredentialsDto credentialsDto) throws UnauthorizedException
    {
        Account account = accountRepository.getAccountByEmail(credentialsDto.getEmail());
        if(account == null || !credentialsDto.getPassword().equals(account.getPassword()))
        {
            throw new UnauthorizedException();
        }
        return new TokenDto(jwtService.generateToken(account));
    }
}
