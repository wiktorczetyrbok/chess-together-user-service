package com.myApp.web;


import com.myApp.web.controller.ChessBoardController;
import com.myApp.web.game.logic.BoardMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackageClasses= {BoardMovement.class, ChessBoardController.class})

public class WebApplication {

	@Autowired


	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
