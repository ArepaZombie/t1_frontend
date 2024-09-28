package com.example.torresmora_t1_frontend.controller;

import com.example.torresmora_t1_frontend.viewmodel.VehiculoModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/t1")
public class VehiculoController {

  @GetMapping("/inicio")
  public String inicio(){

    return "inicio";
  }

  @PostMapping("/buscar")
  public String buscar(@RequestParam("placa") String placa, Model model){

    //Nos aseguramos que ingresen algún dato y que la longitud sea exactamente 7
    if (placa.length()!=7 || !placa.matches("^[\\w-]*$") ){
      //Pienso que es mas sencillo solo usaar un atributo string para el caso de valicación
      model.addAttribute("mensaje",
        "Debe ingresar una placa correcta");
      return "inicio";
    }


    VehiculoModel vehiculo = new VehiculoModel(
      "00","",
      "B4T-MAN","Wayne","Batimovil","5","99999999","Negro"
    );

    model.addAttribute("vehiculo",vehiculo);
    return "resultado";

  }


}
