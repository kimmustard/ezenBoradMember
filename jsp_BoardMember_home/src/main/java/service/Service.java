package service;

import java.util.List;

import domain.BoardVO;
import domain.CommentVO;
import domain.PagingVO;

public interface Service {

	int register(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO detail(int bno);

	int edit(BoardVO bvo);

	int remove(int bno);

	int getTotalCount(PagingVO pgvo);

	List<BoardVO> getPageList(PagingVO pgvo);

	List<CommentVO> getCmtList(int bcno);

	int addCmt(CommentVO cvo);

	
}
