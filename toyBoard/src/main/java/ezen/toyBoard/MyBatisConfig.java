package ezen.toyBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ezen.toyBoard.repository.BoardRepository;
import ezen.toyBoard.repository.BoardRepositoryImp;
import ezen.toyBoard.repository.mybatis.BoardMapper;
import ezen.toyBoard.service.BoardService;
import ezen.toyBoard.service.BoardServiceImpl;

@Configuration
public class MyBatisConfig {

	private final BoardMapper boardMapper;

	@Autowired
	public MyBatisConfig(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Bean
	public BoardService boardService() {
		return new BoardServiceImpl(boardRepository());
	}
	
	@Bean
	public BoardRepository boardRepository() {
		return new BoardRepositoryImp(boardMapper);
	}
	
}
