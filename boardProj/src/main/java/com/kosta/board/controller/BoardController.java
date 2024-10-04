package com.kosta.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.Board;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/boardList")
	public ModelAndView boardList(@RequestParam(value="page", required=false, defaultValue="1") Integer page) {
		//page 값이 없으면 defaultValue="1" 로 지정해줌 
		ModelAndView mav = new ModelAndView();
		try {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(page);
			List<Board> boardList = boardService.boardList(pageInfo);
			mav.addObject("boardList", boardList);
			mav.addObject("pageInfo", pageInfo);
			mav.setViewName("boardlist");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}
	
	@RequestMapping("/boardDetail") //아무것도 안쓰면 기본GET방식
	public ModelAndView boardDetail(@RequestParam("num") Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			Board board = boardService.boardDetail(num);  //boardService.boardDetail(num) 리턴타입이 board라서
			mav.addObject("board", board);
			mav.setViewName("boarddetail");
			
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}
	
	@RequestMapping(value="/boardWrite", method=RequestMethod.GET)
	public String boardWrite() {
		return "writeform";
	}
	
	@RequestMapping(value="/boardWrite", method=RequestMethod.POST)
	public String boardWrite(Board board, @RequestPart(value="file", required=false) MultipartFile file, 
			@RequestPart(value="dfile", required=false) MultipartFile dfile, Model model) 
	{	
		ModelAndView mav = new ModelAndView();
		try {
			boardService.boardWrite(board, file, dfile);
			//저장하고 나서 detail로 넘겨줄건데, detail 조회를 위한 글번호 num을 알아야 함 -> board.xml insert 수정해서 사용
			return "redirect:boardDetail?num="+board.getNum(); //getNum으로 사용할 수 있음 
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "err";
		}
		
	}
}
