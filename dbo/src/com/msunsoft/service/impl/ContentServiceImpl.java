package com.msunsoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msunsoft.mapper.ContentMapper;
import com.msunsoft.model.Content;
import com.msunsoft.service.ContentService;
import com.msunsoft.utils.ContentVo;
@Service
public class ContentServiceImpl implements ContentService{
	@Autowired
	private ContentMapper contentMapper;
	@Override
	public Content findContentById(ContentVo contentVo) {
		// TODO Auto-generated method stub
		return contentMapper.findContentById(contentVo);
	}
	@Override
	public void addContent(ContentVo contentVo) {
		// TODO Auto-generated method stub
		contentMapper.addContent(contentVo);
	}
	@Override
	public void deleteContent(ContentVo contentVo) {
		// TODO Auto-generated method stub
		contentMapper.deleteContent(contentVo);
	}
	@Override
	public void updateContent(ContentVo contentVo) {
		// TODO Auto-generated method stub
		contentMapper.updateContent(contentVo);
	}
	@Override
	public List<Content> findContentAll(ContentVo contentVo) {
		// TODO Auto-generated method stub
		return contentMapper.findContentAll(contentVo);
	}
	
}
