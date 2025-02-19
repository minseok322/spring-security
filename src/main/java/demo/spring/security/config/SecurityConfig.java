package demo.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
//개발자가 구현한 로그인 화면을 띄우려면 반드시 SecurityConfig관련 설정을 할 것.
//스프링 컨테이너(BeanFactory, ApplicationContext)로부터 빈을 관리받기 위해 사용되는 어노테이션이다.
@Configuration
@EnableMethodSecurity //URL 매핑된 메소드 권한부여 활용할때
public class SecurityConfig {
        @Bean
        SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/","/info","/account/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated());
        http
                .formLogin(Customizer.withDefaults());
        http
                .httpBasic(Customizer.withDefaults());
        return http.build();
        }//end of defaultSecurityFilterChain

        @Bean
        public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
        }

}//end of SecurityConfig
