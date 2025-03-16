package br.com.agrego;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPI31
public class OpenAPIConfig {

//	@Primary
//	@Bean
//	public SpringDocConfiguration springDocConfiguration() {
////		return new SpringDocConfiguration()
////				.withTitle("My API")
////				.withDescription("This is my API")
////				.withVersion("1.0.0");
//		SpringDocConfiguration springDocConfiguration = new SpringDocConfiguration();
//		return new SpringDocConfiguration();
//	}
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("AGREGO API")
						.description("Doc da minha api")
						.version("v0.0.1")
//						.license(new License()
//								.name("Apache 2.0")
//								.url("http://springdoc.org")
//								)
					)
//				.externalDocs(new ExternalDocumentation()
//								.description("SpringShop Wiki Documentation")
//								.url("https://springshop.wiki.github.org/docs")
//							)
				;
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("APP").pathsToMatch("/api/**").build();
	}

//	@Bean
//	public GroupedOpenApi adminApi() {
//		return GroupedOpenApi.builder()
//				.group("springshop-admin")
//				.pathsToMatch("/admin/**")
//				.addOpenApiMethodFilter(method -> method.isAnnotationPresent(Admin.class))
//				.build();
//	}

}
