package com.example.torresmora_t1_frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/t1")
public class MatriculaController {

  @GetMapping("/inicio")
  public String inicio(){
    return "inicio";
  }

  @PostMapping("/buscar")
  public String buscar(){
    return "resultado";
  }


}
