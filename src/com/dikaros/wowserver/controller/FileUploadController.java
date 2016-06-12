package com.dikaros.wowserver.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dikaros.wowserver.bean.Result;
import com.dikaros.wowserver.util.ResultUtil;

@Controller
public class FileUploadController {
	@RequestMapping("/uploadavator")
	public void fileupload(@RequestParam("file") CommonsMultipartFile file,String userId,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String fileName = file.getOriginalFilename();
		String path = request.getRealPath("/image/avator/"+userId);
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		InputStream is = file.getInputStream();
		BufferedImage bf = ImageIO.read(is);
		boolean c = creatPicture(bf, path, "avator", "png");
		response.getWriter().write(c?ResultUtil.getResultById(300).toJson():ResultUtil.getResultById(301).toJson());
	}
	
	
	/**
	 * 创建图片
	 * @param bf
	 * @param path
	 * @param name
	 * @param format
	 * @return
	 */
	public boolean creatPicture(BufferedImage bf, String path, String name,
			String format) {
		boolean result = false;
		try {
			// 新建文件
			File file = new File(path + "/" + name + "." + format);
			// 通过图片io流将图片写入file文件
			ImageIO.write(bf, format, file);
//			System.out.println("图片创建成功，保存在" + path + "/" + name + "目录下");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("创建失败");
			result = false;
		}
		return result;

	}
}
