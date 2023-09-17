package ezen.toyBoard.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ezen.toyBoard.domain.BoardVO;
import ezen.toyBoard.repository.mybatis.BoardMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoardRepositoryImp implements BoardRepository{
	
	
	private final BoardMapper boardMapper;

	@Autowired
	public BoardRepositoryImp(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public void register(BoardVO bvo) {
		log.info("register check 3");
		boardMapper.insert(bvo);
	}
	
	
}
