package demo.spring.security.form;

import demo.spring.security.account.Account;
import demo.spring.security.account.AccountContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SampleService {

    public void dashboard() {
        Account account = AccountContext.getAccount();
        log.info(account.getUsername());
    }
}
