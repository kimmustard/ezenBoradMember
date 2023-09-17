package ezen.toyBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezen.toyBoard.domain.BoardVO;
import ezen.toyBoard.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;

	@Autowired
	public BoardServiceImpl(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	@Override
	public void register(BoardVO bvo) {
		log.info("register check 2");
		boardRepository.register(bvo);
	}


	



}