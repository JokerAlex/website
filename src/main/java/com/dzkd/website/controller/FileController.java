package com.dzkd.website.controller;

import com.dzkd.website.dao.FileInfoMapper;
import com.dzkd.website.dao.PictureMapper;
import com.dzkd.website.pojo.FileInfo;
import com.dzkd.website.pojo.Picture;
import com.dzkd.website.pojo.R;
import com.dzkd.website.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
public class FileController {

    private static final Logger logger = LogManager.getLogger(FileController.class);

    private static String FILE_PATH = "/Users/alex/Downloads/uploadFiles/files/";

    private PictureMapper pictureMapper;
    private FileInfoMapper fileInfoMapper;

    @Autowired
    public FileController(PictureMapper pictureMapper, FileInfoMapper fileInfoMapper) {
        this.pictureMapper = pictureMapper;
        this.fileInfoMapper = fileInfoMapper;
    }



    /**
     * 文件上传
     * @param file
     * @param type
     * @param articleId
     * @param articleCate
     * @return
     */
    @RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
    public R upload(@RequestParam("file") MultipartFile file, int type, int articleId, int articleCate) {
        String fileName = file.getOriginalFilename();
        String filePath;

        if (type == 0) {
            filePath = FILE_PATH + articleCate + "/" + articleId + "/";
        } else if (type ==1) {
            filePath = "/Users/alex/Downloads/uploadFiles/pictures/" + articleCate + "/" + articleId + "/";
        } else {
            return R.isFail(new Exception("参数错误"));
        }

        logger.info("filePath------>" + filePath + fileName);
        logger.info("type-->" + type + "\tarticleId-->" + articleId + "\tarticleCate-->" + articleCate);

        try {
            FileUtil.uploadFile(file, filePath, fileName);
            if (type == 0) {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileArticleCate(articleCate);
                fileInfo.setFileArticleId(articleId);
                fileInfo.setFileName(fileName);
                int insertFile = fileInfoMapper.insertSelective(fileInfo);
                logger.info("FileController->insertFile:" + insertFile);
            } else {
                Picture picture = new Picture();
                picture.setAcessId(articleId);
                picture.setPictureCategory(articleCate);
                picture.setPictureName(fileName);
                int insertPicture = pictureMapper.insertSelective(picture);
                logger.info("FileController->insertPicture:" + insertPicture);
            }

            return R.isOk();
        } catch (Exception e) {
            return R.isFail(e);
        }
    }

    @RequestMapping("/download/{fileId}")
    public R download(HttpServletResponse response, @PathVariable(name = "fileId") Integer fileId) {
        if (fileId == null) {
            return R.isFail(new Exception("参数错误"));
        }

        FileInfo fileInfo = fileInfoMapper.selectByPrimaryKey(fileId);

        if (fileInfo == null) {
            return R.isFail(new Exception("请求的资源不存在"));
        }

        String filePath = FILE_PATH + fileInfo.getFileArticleCate() + "/" + fileInfo.getFileArticleId() + "/";
        logger.info("download->fileInfo:" + filePath + fileInfo.getFileName());

        File file = new File(filePath, fileInfo.getFileName());

        //response.setContentType("multipart/form-data");
        //也可以明确的设置一下UTF-8，测试中不设置也可以。
        response.setContentType("multipart/form-data;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; fileName="+
                fileInfo.getFileName());


        /*//response.setContentType("application/force-download");// 设置强制下载不打开
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileInfo.getFileName());// 设置文件名*/

        try {
            FileUtil.downloadFile(file, response.getOutputStream());
            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(e);
        }
    }
}
