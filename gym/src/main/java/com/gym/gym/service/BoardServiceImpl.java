// package com.gym.gym.service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.gym.gym.domain.Board;
// import com.gym.gym.domain.Option;
// import com.gym.gym.domain.Page;
// import com.gym.gym.mapper.BoardMapper;

// import groovy.util.logging.Slf4j;

// @Slf4j
// @Service
// public class BoardServiceImpl implements BoardService {

//     @Autowired
//     private BoardMapper boardMapper;

//     @Override
//     public List<Board> list(Option option, Page page) throws Exception {
//         int total = count(option);
//         page.setTotal(total);

//         List<Board> boardList = boardMapper.list(option, page);
//         return boardList;

//     }

//     @Override
//     public Board select(Long no) throws Exception {
//         Board board = boardMapper.select(no);
//         return board;
//     }

//     @Override
//     public int insert(Board board) throws Exception {
//         int result = boardMapper.insert(board);
//         return result;
//     }

//     @Override
//     public int update(Board board) throws Exception {
//         int result = boardMapper.update(board);
//         return result;

//     }

//     // @Override
//     // public int delete(Long no) throws Exception {
//     //     int result = boardMapper
//     // }

//     @Override
//     public int count(Option option) throws Exception {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'count'");
//     }

//     @Override
//     public Board select1(String id) throws Exception {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'select1'");
//     }

//     @Override
//     public List<Board> list() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'list'");
//     }

//     @Override
//     public Boolean isOwner(Long no) throws Exception {
     
//             Board board = boardMapper.select(no);

//             return board.getUserNo() == no; 
//         }

// }


