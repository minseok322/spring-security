package demo.spring.security.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Account> account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(account.getFirst().getUsername())
                .password(account.getFirst().getPassword())
                .roles(account.getFirst().getRole())
                .build();
    }

    public Account createNew(Account account) {
        account.encodePassword();
        return this.accountRepository.save(account);
    }

}
