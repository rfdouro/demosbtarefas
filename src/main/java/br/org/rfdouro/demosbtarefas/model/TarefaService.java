package br.org.rfdouro.demosbtarefas.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author rfdouro
 * 
 * Simula um banco de tarefas usando lista em memória 
 * uma lista como atributo de classe
 */
@Service
public class TarefaService {

 public static List<Tarefa> tarefas = new ArrayList<>();
 public static Long nid = 0L;

 public Tarefa adiciona(Tarefa t) {
  t.setId(++nid);
  tarefas.add(t);
  return t;
 }

 public Tarefa atualiza(Tarefa t) {
  tarefas.forEach((_t) -> {
   if (_t.getId().equals(t.getId())) {
    _t.setDescricao(t.getDescricao());
    return;
   }
  });
  return t;
 }
 
 
 public void remove(Long id) {
  Tarefa ta = null;
  for (Tarefa _t : tarefas) {
   if (_t.getId().equals(id)) {
    ta = _t;
    break;
   }
  }
  tarefas.remove(ta);
 }

}
