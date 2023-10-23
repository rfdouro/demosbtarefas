package br.org.rfdouro.demosbtarefas.control;

import br.org.rfdouro.demosbtarefas.model.Tarefa;
import br.org.rfdouro.demosbtarefas.model.TarefaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author romulo
 */
@RestController
@CrossOrigin
@RequestMapping("/tarefa")
public class TarefaController {

 /*
 insere automaticamente um repositório de dados para tarefas
  */
 @Autowired
 TarefaService repository;

 /*
 método que retorna a listagem de tarefas ordenada por descrição
 atende no endpoint /tarefa com verbo GET
  */
 @GetMapping({"", "/"})
 public List<Tarefa> getTarefas() {
  TarefaService.tarefas.sort((o1, o2) -> {
   return o1.getDescricao().compareTo(o2.getDescricao());
  });
  return TarefaService.tarefas;
 }

/*
 método que retorna a listagem de tarefas ordenada por descrição
 fazendo uma pesquisa por parte da descrição que está salva no banco de dados
 atende no endpoint /tarefa/pesquisa com verbo GET
 e necessita de um parâmetro chamado 'descricao'
  */
 @GetMapping("/pesquisa")
 public List<Tarefa> getTarefas(String descricao) {
  TarefaService.tarefas.sort((o1, o2) -> {
   return o1.getDescricao().compareTo(o2.getDescricao());
  });
  return TarefaService.tarefas;
 }

 /*
 método que recebe uma tarefa enviada na requisição e a insere no banco de dados
 retorta após inserir já com o ID
 atende no endpoint /tarefa com verbo POST
 a anotação @RequestBody é importante pois indica que os dados da requisição 
 serão enviados no corpo da requisição (em JSON)
  */
 @PostMapping({"", "/"})
 public Tarefa insere(@RequestBody Tarefa tarefa) {
  return repository.adiciona(tarefa);
 }

 /*
 método que recebe uma tarefa enviada na requisição (com id preenchido)
 e a atualiza no banco de dados
 retorta a tarefa atualizada
 caso não tenha id na requisição retorna null
 atende no endpoint /tarefa com verbo PUT
 a anotação @RequestBody é importante pois indica que os dados da requisição 
 serão enviados no corpo da requisição (em JSON)
  */
 @PutMapping({"", "/"})
 public Tarefa atualiza(@RequestBody Tarefa tarefa) {
  if (tarefa.getId() != null) {
   return repository.atualiza(tarefa);
  }
  return null;
 }

 /*
 método que recebe um id de tarefa enviada na requisição 
 caso tenha enviado o id, é excluída no banco de dados
 retorta uma mensagem
 o id é passado no path (caminho da url) por isso
 se usa o @PathVariable indicativo
  */
 @DeleteMapping("/{id}")
 public String exclui(@PathVariable("id") Long id) {
  if (id != null) {
   repository.remove(id);
   return "Excluído";
  }
  return null;
 }

}
