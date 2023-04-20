package team.studywithme.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import team.studywithme.api.controller.dto.KakaoUserInfoDto;
import team.studywithme.utils.session.SessionUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoLoginUtils {

    private final ObjectMapper objectMapper;
    private final KakaoAuthorizationInfo kakaoAuthorizationInfo;

    public KakaoUserInfoDto getKakaoUserInfo(String code) {
        String accessToken = getAccessToken(code);
        String userInfo = getUserInfo(accessToken);
        expireAccessToken(accessToken);

        try {
            return strToUserDtoObj(userInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private KakaoUserInfoDto strToUserDtoObj(String userInfo) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(userInfo);
        String id = String.valueOf(jsonNode.get("id"));
        String userEmailJsonValue = String.valueOf(jsonNode.get("kakao_account").get("email"));
        String userEmail = userEmailJsonValue.substring(1, userEmailJsonValue.length() - 1);

        log.info("데이터 불러오기 완료 : {}",id);

        return new KakaoUserInfoDto(id, userEmail);
    }

    private void expireAccessToken(String accessToken){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        restTemplate.postForObject(kakaoAuthorizationInfo.getUserLogoutToken(), request, String.class);
    }

    private String getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            return restTemplate.postForObject(kakaoAuthorizationInfo.getUserInfoUri(), request, String.class);
        }catch(HttpClientErrorException e){
            log.info("many request to kakao server");
            return null;
        }
    }

    private String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", kakaoAuthorizationInfo.getAuthorizationGrantType());
        params.add("client_id", kakaoAuthorizationInfo.getClientId());
        params.add("redirect_uri", kakaoAuthorizationInfo.getRedirectUri());
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(kakaoAuthorizationInfo.getTokenUri(), request, String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            String token = String.valueOf(jsonNode.get("access_token"));
            return token;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (HttpClientErrorException e){
            log.info("many request to kakao server");
            return null;
        }
    }
}
