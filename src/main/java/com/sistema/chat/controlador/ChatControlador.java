package com.sistema.chat.controlador;

import java.util.Date;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.sistema.chat.modelo.Mensaje;

@Controller
public class ChatControlador {
	
	private String[] colores = {"red", "green", "purple", "orange", "yellow"};
	
	@MessageMapping("/mensaje") //recibe
	@SendTo("/chat/mensaje") // con esta notacion notificamos a todos los usuarios el nuevo mensaje y a todos los clentes suscritos
	public Mensaje recibirMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto(" - Nuevo usuario conectado: ");
		}
		return mensaje; 
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String comprobarSiElUsuarioEstaEscribiendo(String username) {
		return username.concat("está escribiendo ...");
	}
}
