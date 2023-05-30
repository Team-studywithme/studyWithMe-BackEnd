package team.studywithme.structure;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import team.studywithme.utils.kakao.MockKakaoLoginUtilsImpl;

@SpringBootTest
@ActiveProfiles("test")
@Import(MockKakaoLoginUtilsImpl.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class BaseDataTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;
}
