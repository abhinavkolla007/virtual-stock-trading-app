package com.nikolagrujic.tradingsimulator;

import com.nikolagrujic.tradingsimulator.model.User;
import com.nikolagrujic.tradingsimulator.repository.UserRepository;
import com.nikolagrujic.tradingsimulator.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean; // 1. IMPORT
import org.springframework.mail.javamail.JavaMailSender;   // 2. IMPORT
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceTest {

    // 3. ADD MOCK BEAN
    // This tells Spring Boot to put a Mockito mock of JavaMailSender
    // into the Application Context, satisfying the dependency 
    // for EmailVerificationService.
    @MockBean
    private JavaMailSender javaMailSender; 
    
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
        assertEquals(user.getFirstName(), FIRST_NAME);
    }
}