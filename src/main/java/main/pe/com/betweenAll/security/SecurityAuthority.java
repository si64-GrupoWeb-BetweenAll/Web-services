package main.pe.com.betweenAll.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.pe.com.betweenAll.entities.Authority;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
    private Authority authority;
    @Override
    public String getAuthority() {
        return authority.getName().toString();
    }
}
