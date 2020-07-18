package com.touteii.shopping.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/gif", "image/jpeg", "image/png");

    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    public String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        //check file
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)) {
            logger.info("file type is error:{}", originalFilename);
            return null;
        }
        try {
            //check file body
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                logger.info("file body is enable:{}", originalFilename);
                return null;
            }


            //save file sever
          //  file.transferTo(new File("C:\\tzw\\image\\" + originalFilename));
           String ext= StringUtils.substringAfterLast(originalFilename,".");
           StorePath storePath= this.fastFileStorageClient.uploadFile(file.getInputStream(),file.getSize(),ext,null);

           return "http://image.t-shopping.com/" + storePath.getFullPath();

            //return url to show
           // return "http://image.t-shopping.com/" + originalFilename;
        } catch (IOException e) {
            logger.info("sever error:"+originalFilename);
            e.printStackTrace();
        }

        return null;
    }
}
