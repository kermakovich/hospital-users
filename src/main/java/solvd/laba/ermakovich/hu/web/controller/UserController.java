package solvd.laba.ermakovich.hu.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.service.UserInfoService;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserInfoService userInfoService;

    @GetMapping
    public Mono<Boolean> isExistByExternalId(@RequestParam UUID externalId) {
        return userInfoService.isExistByExternalId(externalId);
    }

}
