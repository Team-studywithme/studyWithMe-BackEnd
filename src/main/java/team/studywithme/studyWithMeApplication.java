package team.studywithme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import team.studywithme.repository.elasticsearch.PostSearchRepository;

// @EnableJpaRepositories가 JPA 관련 Repository 클래스만 스캐닝할 수 있도록 함.
@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
		type = FilterType.ASSIGNABLE_TYPE,
		classes = PostSearchRepository.class
))
@SpringBootApplication
public class studyWithMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(studyWithMeApplication.class, args);
	}

}
