package team.springwithme.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import team.springwithme.domain.dto.StudyDto;
import team.springwithme.domain.entity.Avatar;
import team.springwithme.repository.AvatarRepository;
import team.springwithme.service.AccountService;
import team.springwithme.service.AvatarService;

@Service
public class AvatarServiceImpl implements AvatarService {

    private final ObjectMapper objectMapper;
    private final AccountService accountService;
    private final AvatarRepository avatarRepository;

    public AvatarServiceImpl(ObjectMapper objectMapper, AccountService accountService, AvatarRepository avatarRepository) {
        this.objectMapper = objectMapper;
        this.accountService = accountService;
        this.avatarRepository = avatarRepository;
    }

    @Override
    public StudyDto verificationKakao(String code) {
        StudyDto studyDto = new StudyDto();

        String accessToken = accountService.getKakaoAccessToken(code);
        String userInfo = accountService.getUserInfo(accessToken);

        try {
            JsonNode jsonNode = objectMapper.readTree(userInfo);

            String email = String.valueOf(jsonNode.get("kakao_account").get("email"));
            studyDto.setEmail("kakao_" + email.substring(1, email.length() - 1));

            String nickname = String.valueOf(jsonNode.get("kakao_account").get("profile").get("nickname"));
            studyDto.setNickname(nickname.substring(1, nickname.length() - 1));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return studyDto;
    }

    @Override
    public Avatar save(Avatar avatar) {
        return avatarRepository.save(avatar);
    }
}
