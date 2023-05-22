package team.studywithme.utils.kakao;

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

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoLoginUtils {

    private final ObjectMapper objectMapper;
    private final KakaoAuthorizationInfo kakaoAuthorizationInfo;

    public KakaoUserInfoDto getKakaoUserInfo(String code) {
        String accessToken = getAccessToken(code);
        String userInfo = getUserInfo(accessToken);
        expireToken(accessToken);
        try {
            return strToUserDtoObj(userInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private KakaoUserInfoDto strToUserDtoObj(String userInfo) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(userInfo);
        String id = String.valueOf(jsonNode.get("id"));;
        String userEmailJsonValue = String.valueOf(jsonNode.get("kakao_account").get("email"));
        String userEmail = userEmailJsonValue.substring(1, userEmailJsonValue.length() - 1);

        log.info("데이터 불러오기 완료 : {}",id);

        return new KakaoUserInfoDto(id, userEmail);
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

    private void expireToken(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            restTemplate.postForObject(kakaoAuthorizationInfo.getToken_remove_uri(), request, String.class);
        }catch(HttpClientErrorException e){
            log.info("many request to kakao server");
        }
    }

    private String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", kakaoAuthorizationInfo.getAuthorizationGrantType());
        log.info("kakao auth : {}",kakaoAuthorizationInfo.getAuthorizationGrantType());
        params.add("client_id", kakaoAuthorizationInfo.getClientId());
        log.info("kaka auth : {}",kakaoAuthorizationInfo.getClientId());
        params.add("redirect_uri", kakaoAuthorizationInfo.getRedirectUri());
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(kakaoAuthorizationInfo.getTokenUri(), request, String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return String.valueOf(jsonNode.get("access_token"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (HttpClientErrorException e){
            log.info("many request to kakao server");
            throw new IllegalArgumentException("클라이언트의 인증코드가 틀리거나 이미 사용한 인증코드입니다.");
        }
    }
}
