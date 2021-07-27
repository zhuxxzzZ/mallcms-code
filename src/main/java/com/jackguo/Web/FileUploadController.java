package com.jackguo.Web;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.jackguo.common.CodeMsg;
import com.jackguo.common.Constant;
import com.jackguo.common.Result;
import io.swagger.annotations.Api;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Api("文件上传接口")
@RestController
@RequestMapping("/file")
public class FileUploadController {



    @RequestMapping("/uploadImage.do")
    public Object uploadImage(@RequestParam("image")MultipartFile image,HttpServletRequest Request){

        if (image.isEmpty()) {
        System.out.println("文件为空空");
    }
//        原图片名称
        String originalFilename = image.getOriginalFilename();
//        获取图片后缀
        String s = FileUtil.extName(originalFilename);
//        产生新的图片名称
        String newFileName = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
        newFileName=newFileName+"."+s;
//        获取文件夹物理路径
        String realPath = Request.getServletContext().getRealPath(Constant.UPLOAD_FOLDER);
//        String realPath = "D://Test/"; // 上传后的路径
//        String realPath ="E:\\IdeaWorking\\carcms\\src\\main\\resources\\static\\upload";
        System.out.println("111"+realPath);
//        文件保存的物理路径
        String fileRealPath=realPath+ File.separator+newFileName;
        System.out.println("333"+fileRealPath);
//       文件保存的url路径

        try {
            image.transferTo(new File(fileRealPath));
//            return  url;

            return new Result(newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Result(CodeMsg.CAR_UPLOAD_IMG_ERROR);
    }

}
