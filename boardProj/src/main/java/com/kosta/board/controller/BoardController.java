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
		//page ���� ������ defaultValue="1" �� �������� 
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
	
	@RequestMapping("/boardDetail") //�ƹ��͵� �Ⱦ��� �⺻GET���
	public ModelAndView boardDetail(@RequestParam("num") Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			Board board = boardService.boardDetail(num);  //boardService.boardDetail(num) ����Ÿ���� board��
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
			//�����ϰ� ���� detail�� �Ѱ��ٰǵ�, detail ��ȸ�� ���� �۹�ȣ num�� �˾ƾ� �� -> board.xml insert �����ؼ� ���
			return "redirect:boardDetail?num="+board.getNum(); //getNum���� ����� �� ���� 
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "err";
		}
		
	}
}
