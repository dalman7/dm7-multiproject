package kr.co.dm7.blackpink.common;

import kr.co.dm7.blackpink.common.security.config.SecurityRepositoryConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({SecurityRepositoryConfig.class})
public @interface EnableDm7CommonSecurity {
}
