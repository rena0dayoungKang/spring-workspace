package com.kosta.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dao.BoardDao;
import com.kosta.board.dao.HeartDao;
import com.kosta.board.dto.Board;
import com.kosta.board.util.PageInfo;


@Service
public class BoardServiceImpl implements BoardService {
	
	
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private HeartDao heartDao;
	
	
//	public BoardServiceImpl(BoardDao boardDao, HeartDao heartDao) {
//		this.boardDao = boardDao;
//		this.heartDao = heartDao;
//	}

	@Override
	public void boardWrite(Board board, MultipartFile file, MultipartFile dfile) throws Exception {
		
	}

	@Override
	public Board boardDetail(Integer num) throws Exception {
		Board board = boardDao.selectBoard(num);
		if(board==null) throw new Exception("글 번호 오류");
		boardDao.updateViewCnt(num);
		return board;
	}

	@Override
	public void imageView(HttpServletRequest request, OutputStream out, String file) throws Exception {
		FileInputStream fis = null;
		String path = request.getServletContext().getRealPath("upload");
		try {
			fis = new FileInputStream(new File(path,file));
			byte[] buff = new byte[4096];
			int len;
			while((len=fis.read(buff))>0) {
				out.write(buff,0,len);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis!=null) fis.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Integer boardModify(HttpServletRequest request) throws Exception {
		return 0;
	}

	@Override
	public List<Board> boardList(PageInfo pageInfo) throws Exception {
		Integer boardCnt = boardDao.selectBoardCount();
		
		Integer allPage = (int)Math.ceil((double)boardCnt/10); 
		//startPage : 1~10=>1, 11~20=>11
		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1; //1,11,21,31...
		Integer endPage = startPage+10-1; //10,20,30...
		if(endPage>allPage) endPage = allPage;

		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage); 
		pageInfo.setEndPage(endPage);
		
		Integer row = (pageInfo.getCurPage()-1)*10+1;
		return boardDao.selectBoardList(row);
	}

	@Override
	public Integer checkHeart(String memberId, Integer boardNum) throws Exception {
		return heartDao.selectHeart(memberId, boardNum);
	}

	@Override
	public boolean toggleHeart(String id, Integer boardNum) throws Exception {
		Integer heartNum = heartDao.selectHeart(id, boardNum);
		if(heartNum==null) {
			heartDao.insertHeart(id, boardNum);
			return true;
		} else {
			heartDao.deleteHeart(id, boardNum);
			return false;
		}
	}

	@Override
	public void fileDown(HttpServletRequest request, HttpServletResponse response) 
			throws Exception, IOException {
		String file = request.getParameter("file");
		String path = request.getServletContext().getRealPath("upload");
		
		FileInputStream fis = new FileInputStream(new File(path,file));
		
		String mimeType = request.getServletContext().getMimeType(path+"\\"+file);
		if(mimeType==null) {
			mimeType = "application/octet-stream"; 
		}
		
		response.setContentType(mimeType);
		String encoding = new String(file.getBytes("utf-8"),"8859_1");
		response.setHeader("content-Disposition", "attachmemt; filename= "+encoding);

		OutputStream out = response.getOutputStream();
		byte[] buff = new byte[4096];
		int len;
		while((len=fis.read(buff))>0) {
			out.write(buff,0,len);
		}
		
		fis.close();
	}
}
