package ru.hits.hitsback.timetable.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.exception.UnauthorizedException;
import ru.hits.hitsback.timetable.model.dto.authorisation.JWTTokenDto;
import ru.hits.hitsback.timetable.model.entity.Account;
import ru.hits.hitsback.timetable.model.entity.JWTToken;
import ru.hits.hitsback.timetable.repository.AccountRepository;
import ru.hits.hitsback.timetable.repository.JWTTokenRepository;
import ru.hits.hitsback.timetable.service.JwtService;

import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final Random random = new Random();

    private final AccountRepository accountRepository;

    private final JWTTokenRepository jwtTokenRepository;

    @Override
    public String generateToken(Account account) {
        return doGenerateToken(account, 3600l);
    }

    private String doGenerateToken(Account account, long expiration) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);

        final String secret = getRandomString(20);

        JWTToken jwtToken = new JWTToken();
        JWTTokenDto jwtTokenDto = new JWTTokenDto();

        jwtTokenDto.setAccountId(account.getId());
        jwtToken.setAccount(account);

        jwtTokenDto.setSecret(secret);
        jwtToken.setSecret(hashed(secret));

        jwtTokenDto.setDateCreated(createdDate);
        jwtToken.setDateCreated(createdDate);

        jwtTokenDto.setDateExp(expirationDate);
        jwtToken.setDateExp(expirationDate);

        jwtTokenDto.setRole(account.getRole().getAuthority());

        jwtTokenRepository.save(jwtToken);
        return toToken(jwtTokenDto);
    }

    @Override
    public Account getAccountByToken(String token) throws UnauthorizedException {
        JWTTokenDto jwtTokenDto = fromToken(token);
        if (jwtTokenDto == null) {
            throw new UnauthorizedException();
        }
        String hashed = hashed(jwtTokenDto.getSecret());
        JWTToken jwtToken = jwtTokenRepository.getBySecret(hashed);
        if (jwtToken == null) {
            throw new UnauthorizedException();
        }
        if (!jwtToken.getAccount().getId().equals(jwtTokenDto.getAccountId())) {
            throw new UnauthorizedException();
        }
        if (jwtToken.getDateCreated().compareTo(jwtTokenDto.getDateCreated()) != 0) {
            throw new UnauthorizedException();
        }
        if (jwtToken.getDateExp().compareTo(jwtTokenDto.getDateExp()) != 0) {
            throw new UnauthorizedException();
        }
        if (jwtToken.getDateExp().before(new Date())) {
            throw new UnauthorizedException();
        }
        return jwtToken.getAccount();
    }

    private String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

    private String hashed(String secret) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(secret.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (Exception e) {
            return null;
        }
    }

    private String toToken(JWTTokenDto jwtTokenDto) {
        try {
            StringWriter writer = new StringWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, jwtTokenDto);
            String result = writer.toString();
            return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." + Base64.getEncoder().encodeToString(result.getBytes()) + ".SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        } catch (Exception e) {
            return null;
        }
    }

    private JWTTokenDto fromToken(String token) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(token.split("\\.")[1]);
            String decode = new String(decodedBytes);
            StringReader reader = new StringReader(decode);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(reader, JWTTokenDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    @Override
    public void deleteAllBySecret(String token) {
        String secret = fromToken(token).getSecret();
        jwtTokenRepository.deleteAllBySecret(hashed(secret));
    }


    @Transactional
    @Override
    public void deleteAllByAccount(Account account) {
        jwtTokenRepository.deleteAllByAccount(account);
    }
}
