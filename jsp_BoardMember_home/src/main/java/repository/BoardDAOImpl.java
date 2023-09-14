package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.CommentVO;
import domain.PagingVO;
import orm.DatabaseBuilder;


public class BoardDAOImpl implements BoardDAO {

	
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	//DB 연결 
	private SqlSession sql;
	private final String NS = "BoardMapper.";	//namespace.id
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("insert check 3");
		int isOk= sql.insert(NS+"add",bvo);
		//insert , update, delete시 commit() 필요
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<BoardVO> selectList() {
		log.info("list check 3");
		return sql.selectList(NS+"list");
	}
	
	@Override
	public BoardVO selectOne(int bno) {
		log.info("detail check 3");
		int isOk = sql.update(NS+"hit", bno);
		if(isOk >0) {
			sql.commit();
		}
		
		return sql.selectOne(NS+"detail", bno);
	}

	

	@Override
	public int update(BoardVO bvo) {
		log.info("edit check 3");
		int isOk = sql.update(NS+"up", bvo);
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
		return 0;
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return sql.selectOne(NS+"cnt", pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		return sql.selectList(NS+"page", pgvo);
	}

	
	
	// 댓글시스템
	
	
	@Override
	public List<CommentVO> cmtSelectList(int bcno) {
		log.info("CmtList check3");
		return sql.selectList(NS+"cmtList", bcno);
	}

	
	@Override
	public int insertCmt(CommentVO cvo) {
		log.info("comment check 3");
		int isOk = sql.insert(NS+"addCmt", cvo);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}


	
	
}
