package service;

import java.util.List;

import domain.BoardVO;

public interface Service {

	int register(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO detail(int bno);

	int edit(BoardVO bvo);

	int remove(int bno);

	
}
