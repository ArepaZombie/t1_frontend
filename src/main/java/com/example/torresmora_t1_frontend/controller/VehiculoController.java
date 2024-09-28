package com.example.torresmora_t1_frontend.controller;

import com.example.torresmora_t1_frontend.dto.RequestVehiculo;
import com.example.torresmora_t1_frontend.dto.ResponseVehiculo;
import com.example.torresmora_t1_frontend.viewmodel.VehiculoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/t1")
public class VehiculoController {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping("/inicio")
  public String inicio(){return "inicio";}

  @PostMapping("/buscar")
  public String buscar(@RequestParam("placa") String placa, Model model){

    //Nos aseguramos que ingresen algún dato y que la longitud sea exactamente 7
    if (placa.length()!=7 || !placa.matches("^[\\w-]*$") ){
      //Pienso que es mas sencillo solo usaar un atributo string para el caso de valicación
      model.addAttribute("mensaje",
        "Debe ingresar una placa correcta");
      return "inicio";
    }

    try{
      String url = "http://localhost:8081/t1/busqueda";
      RequestVehiculo request = new RequestVehiculo(placa);
      ResponseVehiculo response = restTemplate.postForObject(
        url, request, ResponseVehiculo.class);

      if (response.codigo().equals("00")){
        VehiculoModel vehiculo = new VehiculoModel(
          "00","",
          placa,response.marca(),
          response.modelo(),
          response.asientos(),
          response.precio(),
          response.color()
        );
        model.addAttribute("vehiculo",vehiculo);
        return "resultado";
      }else {
        model.addAttribute("mensaje",
          response.mensaje());
        return "inicio";
      }

    } catch (Exception e) {
      model.addAttribute("mensaje",
        "Hubo un error del servidor");
      System.out.println("ERROR: "+e.getMessage());
      return "inicio";
    }

  }

}
