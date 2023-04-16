package team.springwithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.springwithme.domain.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}
