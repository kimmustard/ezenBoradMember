package ezen.toyBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezen.toyBoard.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;

	@Autowired
	public BoardServiceImpl(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}


	



}