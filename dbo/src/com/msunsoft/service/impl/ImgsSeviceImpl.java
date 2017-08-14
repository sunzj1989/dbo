package com.msunsoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msunsoft.mapper.ImagesMapper;
import com.msunsoft.model.Images;
import com.msunsoft.service.ImgsService;
import com.msunsoft.utils.PhotoEntity;
@Service
public class ImgsSeviceImpl implements ImgsService{
	@Autowired
	private ImagesMapper imgsMapper;
	@Override
	public List<PhotoEntity> findImgsBySubId(PhotoEntity photoEntity) {
		// TODO Auto-generated method stub
		return imgsMapper.findImgsBySubId(photoEntity);
	}

	@Override
	public void deleteImg(PhotoEntity photoEntity) {
		// TODO Auto-generated method stub
		imgsMapper.deleteImg(photoEntity);
	}

	@Override
	public void addImg(PhotoEntity photoEntity) {
		// TODO Auto-generated method stub
		imgsMapper.addImg(photoEntity);
	}

	@Override
	public Images findImgsById(PhotoEntity photoEntity) {
		// TODO Auto-generated method stub
		return imgsMapper.findImgsById(photoEntity);
	}

	@Override
	public List<Images> findImgsAll(PhotoEntity photoEntity) {
		// TODO Auto-generated method stub
		return imgsMapper.findImgsAll(photoEntity);
	}

}
