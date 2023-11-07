package com.matzalal.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matzalal.web.service.QnaService;

@RestController("apiQnaController")
@RequestMapping("/api/qnas")
public class QnaController {
		
		@Autowired
		private QnaService service;
		

		
}