package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;


public class BoardServiceImpl implements Service {

	//로그 객체 선언
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	//dao 객체 생성
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public int register(BoardVO bvo) {
		log.info("insert check 2");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		log.info("list check 2");
		return bdao.selectList();
	}

	@Override
	public BoardVO detail(int bno) {
		log.info("detail check 2");
		return bdao.selectOne(bno);
	}

	@Override
	public int edit(BoardVO bvo) {
		log.info("edit check 2");
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info("edit check 2");
		return bdao.delete(bno);
		
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		log.info("totalCount check 2");
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		log.info("PageList check 2");
		return bdao.getPageList(pgvo);
	}



}
