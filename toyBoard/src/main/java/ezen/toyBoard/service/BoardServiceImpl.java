package ezen.toyBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezen.toyBoard.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	private final BoardMapper bm;

	@Autowired
	public BoardServiceImpl(BoardMapper bm) {
		this.bm = bm;
	}
	
	
	
}