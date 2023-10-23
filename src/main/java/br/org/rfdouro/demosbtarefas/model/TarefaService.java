/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.rfdouro.demosbtarefas.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author rfdouro
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
