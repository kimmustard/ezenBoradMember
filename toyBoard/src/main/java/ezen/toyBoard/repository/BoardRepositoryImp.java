package ezen.toyBoard.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ezen.toyBoard.repository.mybatis.BoardMapper;

@Repository
public class BoardRepositoryImp implements BoardRepository{
	
	
	private final BoardMapper boardMapper;

	@Autowired
	public BoardRepositoryImp(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	
}
