package com.dzkd.website.util;

import com.dzkd.website.pojo.R;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {

    private static final Logger logger = LogManager.getLogger(FileUtil.class);

    public static void uploadFile(MultipartFile file, String filePath, String fileName) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("文件为空");
        }
        try {
            byte[] fileByte = file.getBytes();
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(filePath+fileName);
            out.write(fileByte);
            out.flush();
            out.close();

        } catch (Exception e) {
            logger.catching(e);
            throw new Exception("文件上传失败");
        }
    }

    public static void downloadFile(File file, OutputStream outputStream) throws Exception {
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
}
