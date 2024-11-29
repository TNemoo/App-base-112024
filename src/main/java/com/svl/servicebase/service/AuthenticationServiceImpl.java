package com.svl.servicebase.service;

import com.svl.servicebase.entity.PersonCredentials;
import com.svl.servicebase.exception.SecurityBadRequestException;
import com.svl.servicebase.jwt.JwtTokenProvider;
import com.svl.servicebase.repository.PersonCredentialsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PersonCredentialsRepository personCredentialsRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;



//    @Override
//    public AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto requestDto) {
//
//        User user = personRepository.findByLogin(requestDto.email());
//
//        if (user != null && passwordEncoder.matches(requestDto.password(), user.getPassword())) {
//            AuthenticationUserDto authenticationUserDto = authenticationUserMapper.userToAuthenticationUserDto(user);
//            log.info("IN findByEmailAndPassword - authenticationUserDto: {} found by email: {}", authenticationUserDto,
//                    authenticationUserDto.email());
//            return authenticationUserDto;
//        } else {
//            log.info("IN findByEmailAndPassword - Invalid username or password");
//            return null;
//        }
//    }

    public String authAndGetToken(String login, String password){

        // Создание токена аутентификации с логином
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(login, password);
        // в этот стандартный SS объект передается только открытый пароль типа String, он сам его хэширует и сравнивает;

        /* Аутентификация через AuthenticationManager, он отправляет authenticationToken на проверку всем провайдерам,
           которые к нему подключены */
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // После проверки отдаем объект authentication контексту для доступности в любой точке приложения.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        PersonCredentials personCredentials = personCredentialsRepository.findByLogin(login)
                .orElseThrow(SecurityBadRequestException::new);

        // Генерация JWT токена на основе данных пользователя
        return jwtTokenProvider.createToken(personCredentials);
    }
}
