package studioyoga.project.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TestUserDetails implements UserDetails {
    private final String username;
    private final String profilePicture;
    private final List<GrantedAuthority> authorities;

    public TestUserDetails(String username, String profilePicture, String... roles) {
        this.username = username;
        this.profilePicture = profilePicture;
        this.authorities = roles == null ? List.of() :
            Arrays.stream(roles)
                  .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                  .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override
    public String getPassword() { return ""; }
    @Override
    public String getUsername() { return username; }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

    public String getProfilePicture() { return profilePicture; }
}