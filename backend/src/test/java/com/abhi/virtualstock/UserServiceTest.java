package com.abhi.virtualstock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.abhi.virtualstock.model.User;
import com.abhi.virtualstock.repository.UserRepository;
import com.abhi.virtualstock.service.StockService;
import com.abhi.virtualstock.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(
    locations = "classpath:application-test.properties"
)
public class UserServiceTest {

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private StockService stockService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetUserByEmail() {
        final String EMAIL = "test@test.com";
        final String FIRST_NAME = "First";

        User mockUser = new User();
        mockUser.setEmail(EMAIL);
        mockUser.setPassword("password");
        mockUser.setFirstName(FIRST_NAME);
        mockUser.setLastName("Last");
        userRepository.save(mockUser);

        User user = userService.findByEmail(EMAIL);
        assertNotNull(user);
        assertEquals(FIRST_NAME, user.getFirstName());
    }
}
