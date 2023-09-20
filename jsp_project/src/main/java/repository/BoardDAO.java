package repository;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectList();

	BoardVO selectOne(int bno);

	int update(BoardVO bvo);

	int delete(int bno);

	int getTotalCount(PagingVO pgvo);

	List<BoardVO> getPageList(PagingVO pgvo);

	String getFileName(int bno);

}
