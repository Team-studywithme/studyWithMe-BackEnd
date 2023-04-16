package team.springwithme.service;

import team.springwithme.domain.entity.Account;

public interface AccountService {
    String getKakaoAccessToken(String code);

    String getUserInfo(String accessToken);

    Account findByEmail(String email);

    Account save(Account account);
}
