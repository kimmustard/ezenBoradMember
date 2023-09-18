package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;
import repository.CommentDAO;
import repository.CommentDAOImpl;


public class CommentServiceImpl implements CommentService {
	
	private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	
	private CommentDAO cdao;
	private BoardDAO bdao;
	
	public CommentServiceImpl() {
		cdao = new CommentDAOImpl();
		bdao = new BoardDAOImpl();
	}

	@Override
	public int post(CommentVO cvo) {
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		return cdao.getList(bno);
	}

	@Override
	public int modify(CommentVO cvo) {
		return cdao.update(cvo);
	}

	@Override
	public int remove(int cno) {
		return cdao.delete(cno);
	}
	
}

