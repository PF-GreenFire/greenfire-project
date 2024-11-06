package sisosolsol.greenfire.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
        basePackages = "sisosolsol.greenfire",
        annotationClass = Mapper.class
)
public class MybatisConfig {
}
