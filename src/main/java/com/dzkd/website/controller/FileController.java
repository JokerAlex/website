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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class FileController {

    private static final Logger logger = LogManager.getLogger(FileController.class);


    private PictureMapper pictureMapper;
    private FileInfoMapper fileInfoMapper;

    @Autowired
    public FileController(PictureMapper pictureMapper, FileInfoMapper fileInfoMapper) {
        this.pictureMapper = pictureMapper;
        this.fileInfoMapper = fileInfoMapper;
    }


    /**
     * 文件上传
     * 招生信息文件type:0,articleCate:0
     * 新闻图片type:1,articleCate:0
     *
     * @param file
     * @param type
     * @param articleId
     * @param articleCate
     * @return
     */
    @RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
    public R upload(@RequestParam("file") MultipartFile file, int type, int articleId, int articleCate) {

        String fileName = file.getOriginalFilename();

        logger.info("fileName------>" + fileName);
        logger.info("type-->" + type + "\tarticleId-->" + articleId + "\tarticleCate-->" + articleCate);

        try {
            FileUtil.uploadFile(file, fileName, type, articleCate, articleId);
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

    @RequestMapping(value = "/admin/delFile", method = RequestMethod.DELETE)
    public R delFiles(@RequestBody Map<String, Integer> map) {
        int id = map.get("id");
        int type = map.get("type");
        logger.info("id-->" + id + "type-->" + type);

        try {
            List<Object> fileList = new ArrayList<>();
            if (type == 0) {
                FileInfo fileInfo = fileInfoMapper.selectByPrimaryKey(id);
                if (fileInfo == null) {
                    return R.isFail(new Exception("Not Found"));
                }
                fileList.add(fileInfo);
                FileUtil.delFile(fileList,type);
                int delFile = fileInfoMapper.deleteByPrimaryKey(id);
                logger.info("delFiles->delFile" + delFile);
            } else if (type == 1) {
                Picture picture = pictureMapper.selectByPrimaryKey(id);
                if (picture == null) {
                    return R.isFail(new Exception("Not Found"));
                }
                fileList.add(picture);
                FileUtil.delFile(fileList,type);
                int delPicture = pictureMapper.deleteByPrimaryKey(id);
                logger.info("delFiles->delPicture" + delPicture);
            } else {
                return R.isFail(new Exception("参数错误"));
            }

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(e);
        }
    }

    @RequestMapping(value = "/admin/showFiles", method = RequestMethod.GET)
    public R showFiles(int type, int articleCate, int articleId) {
        if (type == 0) {
            List<FileInfo> fileInfoList = fileInfoMapper.selectByArticle(articleCate, articleId);
            return R.isOk().data(fileInfoList);
        } else if (type == 1) {
            List<Picture> pictureList = pictureMapper.selectByArticle(articleCate, articleId);
            return R.isOk().data(pictureList);
        } else {
            return R.isFail(new Exception("参数错误"));
        }
    }

    @RequestMapping("/download/{fileId}")
    public R download(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = "fileId") Integer fileId) {
        if (fileId == null) {
            return R.isFail(new Exception("参数错误"));
        }

        FileInfo fileInfo = fileInfoMapper.selectByPrimaryKey(fileId);

        if (fileInfo == null) {
            return R.isFail(new Exception("请求的资源不存在"));
        }

        String fileName;
        try {
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                fileName = URLEncoder.encode(fileInfo.getFileName(), "UTF-8");
            } else {
                fileName = new String(fileInfo.getFileName().getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            }
        } catch (UnsupportedEncodingException e) {
            logger.catching(e);
            return R.isFail(new Exception("编码错误"));
        }

        //response.setContentType("multipart/form-data");
        //也可以明确的设置一下UTF-8，测试中不设置也可以。
        response.setContentType("multipart/form-data;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; fileName=" +
                fileName);

        try {
            FileUtil.downloadFile(fileInfo, response.getOutputStream());
            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(e);
        }
    }
}
