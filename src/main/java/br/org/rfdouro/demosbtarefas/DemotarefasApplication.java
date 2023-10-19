package br.org.rfdouro.demosbtarefas;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class DemotarefasApplication {
 
 @Autowired
 ServletContext servletContext;

 public static void main(String[] args) {
  SpringApplication.run(DemotarefasApplication.class, args);
 }

 /*
 objeto gerenciado pelo Spring que adiciona informações à documentação
  */
 @Bean
 public OpenAPI customOpenAPI() {
  List<Server> ls = new ArrayList<>();

  String c = servletContext.getContextPath();

  ls.add(new Server().url(c + "/").description("Servidor padrão"));
  return new OpenAPI()
          .servers(ls)
          .info(new Info()
                  .title("Servidor")
                  .version("1.0.0")
                  .contact(new Contact().email("rdouro@gmail.com").name("Rômulo F. Douro"))
                  .description("API de tarefas simples")
          );
 }

}
