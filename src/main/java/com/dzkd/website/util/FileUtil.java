package com.dzkd.website.util;

import com.dzkd.website.pojo.R;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

    private static final Logger logger = LogManager.getLogger(FileUtil.class);

    public static R uploadFile(MultipartFile file, String filePath, String fileName) throws Exception {
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

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            throw new Exception("文件上传失败");
        }
    }
}
