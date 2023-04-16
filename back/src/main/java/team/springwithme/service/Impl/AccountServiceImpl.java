package team.springwithme.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import team.springwithme.domain.dto.KakaoAccessTokenDto;
import team.springwithme.domain.entity.Account;
import team.springwithme.repository.AccountRepository;
import team.springwithme.service.AccountService;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AccountRepository accountRepository;

    @Override
    public String getKakaoAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "<REST API KEY>");
        params.add("redirect_uri", "<Redirect_URI>" + "/oauth2/code/kakao");
        params.add("code", code);
        params.add("client_secret", "");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kauth.kakao.com/oauth/token";

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        try {
            return objectMapper.readValue(response.getBody(), KakaoAccessTokenDto.class).getAccess_token();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getUserInfo(String accessToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v2/user/me";

        return restTemplate.postForObject(url, request, String.class);
    }

    @Override
    public Account findByEmail(String email) {
        return (email != null) ? accountRepository.findByEmail(email) : null;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
