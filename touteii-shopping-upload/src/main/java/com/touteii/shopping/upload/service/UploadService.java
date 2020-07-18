package com.touteii.shopping.upload.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            file.transferTo(new File("C:\\tzw\\image\\" + originalFilename));


            //return url to show
            return "http://image.t-shopping.com/" + originalFilename;
        } catch (IOException e) {
            logger.info("sever error:"+originalFilename);
            e.printStackTrace();
        }

        return null;
    }
}
