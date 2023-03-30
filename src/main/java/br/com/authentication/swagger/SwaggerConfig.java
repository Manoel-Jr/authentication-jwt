package br.com.authentication.swagger;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String HTTP_HEADER = "header";
	private static final String SCOPE = "global";

	@Bean
	public Docket docketApi() {
		return new Docket(DocumentationType.OAS_30).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().securityContexts(Collections.singletonList(securityContext()))
				.securitySchemes(Collections.singletonList(tokenAuthentication())).apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfo("Management Users - MJ Soluções LTDA.", "Manoel Junior - Developer", "1.0",
				"Terms of service", new Contact("DEVELOPER IT", "https://github.com/Manoel-Jr", "mjdev2023@gmail.com"),
				"License of API", "API license URL", Collections.emptyList());
	}

	private ApiKey tokenAuthentication() {
		return new ApiKey("Authorization", AUTHORIZATION_HEADER, HTTP_HEADER);
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope(SCOPE, "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
	}
}
