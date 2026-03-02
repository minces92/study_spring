package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ServletComponentScan // 서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);}

//	@Bean
//	public CommonsRequestLoggingFilter logFilter() {
//		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
//		filter.setIncludeHeaders(true);    // 헤더 정보 보기
//		filter.setIncludeQueryString(true); // 쿼리 파라미터 보기
//		filter.setIncludePayload(true);     // 바디 데이터 보기
//		filter.setMaxPayloadLength(10000);
//		return filter;
//	}
		@Bean
		public ViewResolver internalResourceViewResolver() {
			return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
}
}
