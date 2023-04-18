package team.studywithme.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import team.studywithme.api.controller.dto.KakaoAccessTokenDto;
import team.studywithme.api.controller.dto.StudyDto;
import team.studywithme.config.jwt.JwtProperties;
import team.studywithme.domain.entity.Account;
import team.studywithme.domain.entity.Avatar;
import team.studywithme.repository.AccountRepository;

import javax.transaction.Transactional;
import java.util.Date;


@Service
@RequiredArgsConstructor
@PropertySource("classpath:application-security.yml")
public class AccountService {
    private final ObjectMapper objectMapper;
    private final AccountRepository accountRepository;
    private final AvatarService avatarService;

    @Value("${registration.client-id}")
    private String client_id;
    @Value("${registration.redirect-uri}")
    private String redirect_uri;
    @Value("${registration.authorization-grant-type}")
    private String authorization_grant_type;
    @Value("${provider.token-uri}")
    private String token_uri;
    @Value("${provider.user-info-uri}")
    private String user_info_uri;

    @Transactional
    public String kakaoLogic(String userInfo) throws JsonProcessingException {
        StudyDto studyDto = verificationKakao(userInfo);

        Account accountIsNull = accountRepository.findAccountByEmail(studyDto.getEmail());

        // 서비스 등록 회원이 아니라면 회원가입
        if(accountIsNull == null) {
            Avatar avatar = avatarService.saveByNickname(studyDto.getNickname());

            Account account = Account.builder().id(null).email(studyDto.getEmail()).avatar(avatar).build();
            accountRepository.save(account);
        }

        return JWT.create()
                .withSubject("JwtToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("email", studyDto.getEmail())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    public StudyDto verificationKakao(String userInfo) throws JsonProcessingException{
        StudyDto studyDto = new StudyDto();

        JsonNode jsonNode = objectMapper.readTree(userInfo);

        String email = String.valueOf(jsonNode.get("kakao_account").get("email"));
        studyDto.setEmail("kakao_" + email.substring(1, email.length() - 1));

        String nickname = String.valueOf(jsonNode.get("kakao_account").get("profile").get("nickname"));
        studyDto.setNickname(nickname.substring(1, nickname.length() - 1));

        return studyDto;
    }

    public String getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        return restTemplate.postForObject(user_info_uri, request, String.class);
    }

    public String getAccessToken(String code) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", authorization_grant_type);
        params.add("client_id", client_id);
        params.add("redirect_uri", redirect_uri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(token_uri, request, String.class);

        return objectMapper.readValue(response.getBody(), KakaoAccessTokenDto.class).getAccess_token();
    }
}
