package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;


public class BoardDAOImpl implements BoardDAO {

	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	//DB 연결
	private SqlSession sql;
	private final String NS = "BoardMapper.";
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("register check 3");
		int isOk = sql.insert(NS+"add", bvo);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<BoardVO> selectList() {
		log.info("List check 3");
		return sql.selectList(NS+"list");
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info("detail check 3");
		sql.update(NS+"hit", bno);
		sql.commit();
		return sql.selectOne(NS+"dt", bno);
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("edit check 3");
		int isOk = sql.update(NS+"up",bvo);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int delete(int bno) {
		log.info("remove check 3");
		int isOk = sql.delete(NS+"del", bno);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		log.info("pageList check 3");
		return sql.selectOne(NS+"cnt", pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		return sql.selectList(NS+"page", pgvo);
	}

	@Override
	public String getFileName(int bno) {
		return sql.selectOne(NS+"fileName", bno);
	}
	
	
	
	
}
