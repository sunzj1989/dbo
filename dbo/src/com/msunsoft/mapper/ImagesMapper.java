package com.msunsoft.mapper;

import java.util.List;

import com.msunsoft.model.Images;
import com.msunsoft.utils.PhotoEntity;

/**
 * @author 图片管理
 *
 */
public interface ImagesMapper {
	/*
	 * 根据小病种id查询所有图片
	 */
	List<PhotoEntity> findImgsBySubId(PhotoEntity photoEntity);
	/*
	 * 根据小病种id删除图片
	 */
	void deleteImg(PhotoEntity photoEntity);
	/*
	 * 添加图片
	 */
	void addImg(PhotoEntity photoEntity);
	/*
	 * 根据id查询图片
	 */
	Images findImgsById(PhotoEntity photoEntity);
	List<Images>  findImgsAll(PhotoEntity photoEntity);
}
