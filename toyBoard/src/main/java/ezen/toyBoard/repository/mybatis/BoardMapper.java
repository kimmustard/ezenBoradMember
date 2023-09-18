package ezen.toyBoard.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import ezen.toyBoard.domain.BoardVO;

@Mapper
public interface BoardMapper {
	
	void insert(BoardVO bvo);
	
}
