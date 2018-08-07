package com.dzkd.website.util;

import com.dzkd.website.pojo.FileInfo;
import com.dzkd.website.pojo.Picture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class FileUtil {

    private static String FILE_PATH = "/Users/alex/Downloads/uploadFiles/files/";
    private static String PICTURE_PATH = "/Users/alex/Downloads/uploadFiles/pictures/";
    private static int FILE = 0;
    private static int PICTURE = 1;

    private static final Logger logger = LogManager.getLogger(FileUtil.class);


    /**
     * 招生信息文件type:0,articleCate:0
     * 新闻图片type:1,articleCate:0
     *
     * @param file
     * @param fileName
     * @param fileType
     * @throws Exception
     */
    public static void uploadFile(MultipartFile file, String fileName, int fileType, int articleCate, int articleId) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("文件为空");
        }

        try {
            byte[] fileByte = file.getBytes();

            File targetFile;
            FileOutputStream out;

            if (fileType == FILE) {
                targetFile = new File(FILE_PATH + articleCate + "/" + articleId + "/");
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                out = new FileOutputStream(FILE_PATH + articleCate + "/" + articleId + "/" + fileName);
            } else if (fileType == PICTURE) {
                targetFile = new File(PICTURE_PATH + articleCate + "/" + articleId + "/");
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                out = new FileOutputStream(PICTURE_PATH + articleCate + "/" + articleId + "/" + fileName);
            } else {
                throw new Exception("文件类型不正确");
            }


            out.write(fileByte);
            out.flush();
            out.close();

        } catch (Exception e) {
            logger.catching(e);
            throw new Exception("文件上传失败");
        }
    }

    /**
     * 下载文件
     * @param fileInfo
     * @param outputStream
     * @throws Exception
     */
    public static void downloadFile(FileInfo fileInfo, OutputStream outputStream) throws Exception {
        File file = new File(FILE_PATH
                + fileInfo.getFileArticleCate() + "/"
                + fileInfo.getFileArticleId() + "/", fileInfo.getFileName());
        logger.info("fileInfo-->" + file.getPath() + file.getName());

        if (!file.exists()) {
            throw new Exception("请求的资源不存在");
        }

        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int i = bis.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bis.read(buffer);
            }

        } catch (Exception e) {
            logger.catching(e);
            throw new Exception("文件下载失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     * @param fileInfoList
     * @param type
     * @throws Exception
     */
    public static void delFile(List fileInfoList, int type) throws Exception {
        for (Object object : fileInfoList) {
            File file;
            if (type == FILE) {
                FileInfo fileInfo = (FileInfo) object;
                file = new File(FILE_PATH
                        + fileInfo.getFileArticleCate() + "/"
                        + fileInfo.getFileArticleId() + "/", fileInfo.getFileName());
            } else if (type == PICTURE) {
                Picture picture = (Picture) object;
                file = new File(PICTURE_PATH
                        + picture.getPictureCategory() + "/"
                        + picture.getAcessId() + "/", picture.getPictureName());
            } else {
                throw new Exception("文件类型错误");
            }

            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                System.gc();    //加上确保文件能删除，不然可能删不掉
                boolean flag = file.delete();
                logger.info("flag-->" + file.getName() + "-->" + flag);
            }

            File fileDir = new File(file.getParent());
            if (fileDir.isDirectory() && Objects.requireNonNull(fileDir.list()).length == 0) {
                System.gc();    //加上确保文件能删除，不然可能删不掉
                boolean flag = fileDir.delete();
                logger.info("flag-->" + fileDir.getPath() + "-->" + flag);
            }
        }

    }
}
