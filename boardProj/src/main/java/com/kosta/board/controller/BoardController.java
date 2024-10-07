package com.kosta.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.Member;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private HttpSession session;
	
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
		Member member = (Member)session.getAttribute("member");
		try {
			Board board = boardService.boardDetail(num);  //boardService.boardDetail(num) ����Ÿ���� board��
			mav.addObject("board", board);
			if(member != null) {
				Integer hnum = boardService.checkHeart(member.getId(), num);
				mav.addObject("heart", hnum);
			}
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
	public String boardWrite(@ModelAttribute Board board, 
			@RequestPart(value="file", required=false) MultipartFile file, 
			@RequestPart(value="dfile", required=false) MultipartFile dfile, Model model)
			//�ڹٿ��� �����ϴ� ���� �Ӽ��� MultipartFile�̿�
	{	
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
	
	@RequestMapping(value="/boardModify", method=RequestMethod.GET)
	public ModelAndView boardModify(@RequestParam("num") Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			Board board = boardService.boardDetail(num);
			mav.addObject("board", board);
			mav.setViewName("modifyform");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		} 
		return mav;
	}
	
	@RequestMapping(value="/boardModify", method=RequestMethod.POST)
	public String boardModify(Board board, MultipartFile file, MultipartFile dfile, Model model) {
		try {
			Integer num = boardService.boardModify(board, file, dfile);
			return "redirect:boardDetail?num="+num;
		} catch (Exception e) {
			model.addAttribute("err", e.getMessage());
			return "err";
		}	
	}
	
	//heart toggle ���ƿ� ��� 
	@RequestMapping(value="/heart", method=RequestMethod.POST)
	@ResponseBody  //ajax�� ���� �����͸� �������� ���̹Ƿ� responsebody
	public String heart(@RequestParam("num") Integer num) {
		try {
			boolean heart = boardService.toggleHeart(((Member)session.getAttribute("member")).getId(), num);
			return String.valueOf(heart);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	//Detail ���� fileDown
	@RequestMapping(value="/fileDown")
	public void fileDown(@RequestParam("file") String filename, HttpServletResponse response) {
		String path = "C:/kdy/upload/";
		File file = new File(path, filename);
		response.setContentType("application/download");
		response.setContentLength((int)file.length());
		response.setHeader("Content-disposition", "attachment;filename=\""+filename+"\"");
		try {
			OutputStream out = response.getOutputStream();
			FileInputStream fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
