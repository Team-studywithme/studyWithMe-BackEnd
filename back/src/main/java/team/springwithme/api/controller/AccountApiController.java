package team.springwithme.api.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.springwithme.config.jwt.JwtProperties;
import team.springwithme.domain.dto.StudyDto;
import team.springwithme.domain.entity.Account;
import team.springwithme.domain.entity.Avatar;
import team.springwithme.service.AccountService;
import team.springwithme.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Date;

@RestController
public class AccountApiController {

    private final AccountService accountService;
    private final AvatarService avatarService;

    public AccountApiController(AccountService accountService, AvatarService avatarService) {
        this.accountService = accountService;
        this.avatarService = avatarService;
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<?> studyLogin(@RequestParam String code,
                                        HttpServletResponse response){

        StudyDto studyDto = avatarService.verificationKakao(code);

        Account accountIsNull = accountService.findByEmail(studyDto.getEmail());

        // 서비스 등록 회원이 아니라면 회원가입
        if(accountIsNull == null) {
            Avatar avatar = Avatar.builder().id(null).nickname(studyDto.getNickname()).build();
            avatarService.save(avatar);

            Account account = Account.builder().id(null).email(studyDto.getEmail()).avatar(avatar).build();
            accountService.save(account);
        }

        String token = JWT.create()
                .withSubject("JwtToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("email", studyDto.getEmail())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);

        return new ResponseEntity(HttpStatus.OK);
    }
}
