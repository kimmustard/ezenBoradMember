package ezen.toyBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ezen.toyBoard.service.BoardService;

@Controller
@RequestMapping("/brd")
public class BoardController {

	private final BoardService bsv;

	@Autowired
	public BoardController(BoardService bsv) {
		this.bsv = bsv;
	}
	
	@GetMapping("/register")
	public String registerForm() {
		return "board/register";
	}
}
