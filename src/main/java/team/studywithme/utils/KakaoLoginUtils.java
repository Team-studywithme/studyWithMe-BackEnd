package team.studywithme.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
import org.springframework.web.client.RestTemplate;
import team.studywithme.api.controller.dto.KakaoAccessTokenDto;
import team.studywithme.api.controller.dto.KakaoUserInfoDto;
import team.studywithme.config.jwt.JwtProperties;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoLoginUtils {

    private final ObjectMapper objectMapper;
    private final KakaoAuthorizationInfo kakaoAuthorizationInfo;

    public KakaoUserInfoDto getKakaoUserInfo(String code) {
        String accessToken = getAccessToken(code);
        String userInfo = getUserInfo(accessToken);
        try{
            return strToUserDtoObj(userInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private KakaoUserInfoDto strToUserDtoObj(String userInfo) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(userInfo);
        String idJsonValue = String.valueOf(jsonNode.get("kakao_account").get("id"));
        String userEmailJsonValue = String.valueOf(jsonNode.get("kakao_account").get("email"));
        String nicknameJsonValue = String.valueOf(jsonNode.get("kakao_account").get("profile").get("nickname"));
        String id = userEmailJsonValue.substring(1,idJsonValue.length() -1);
        String userEmail = userEmailJsonValue.substring(1, nicknameJsonValue.length() - 1);
        String nickname = nicknameJsonValue.substring(1, nicknameJsonValue.length() - 1);

        return new KakaoUserInfoDto(id,userEmail, nickname);
    }

    private String getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        return restTemplate.postForObject(kakaoAuthorizationInfo.getUserInfoUri(), request, String.class);
    }

    private String getAccessToken(String code){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", kakaoAuthorizationInfo.getAuthorizationGrantType());
        params.add("client_id", kakaoAuthorizationInfo.getClientId());
        params.add("redirect_uri", kakaoAuthorizationInfo.getRedirectUri());
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(kakaoAuthorizationInfo.getTokenUri(), request, String.class);

        try {
            return objectMapper.readValue(response.getBody(), KakaoAccessTokenDto.class).getAccess_token();
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
