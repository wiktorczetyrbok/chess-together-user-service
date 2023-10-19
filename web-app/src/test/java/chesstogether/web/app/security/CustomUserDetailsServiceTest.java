package chesstogether.web.app.security;

import chesstogether.web.app.model.RoleEntity;
import chesstogether.web.app.model.UserEntity;
import chesstogether.web.app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Tag("unit")
public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    private CustomUserDetailsService userDetailsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userDetailsService = new CustomUserDetailsService(userRepository);
    }

    @Test
    public void testLoadUserByUsername() {
        UserEntity mockUser = new UserEntity();
        mockUser.setUsername("testUser");
        mockUser.setPassword("testPassword");
        RoleEntity role = new RoleEntity();
        role.setName("ROLE_USER");
        mockUser.setRoles(Collections.singletonList(role));

        UserDetails expectedUserDetails = new org.springframework.security.core.userdetails.User(
                "testUser",
                "testPassword",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
        when(userRepository.findFirstByUsername("testUser")).thenReturn(mockUser);

        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

        assertEquals(expectedUserDetails, userDetails);
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        when(userRepository.findFirstByUsername("nonExistentUser")).thenReturn(null);
        try {
            userDetailsService.loadUserByUsername("nonExistentUser");
        } catch (UsernameNotFoundException e) {
            assertEquals("Invalid username or password", e.getMessage());
        }
    }
}
