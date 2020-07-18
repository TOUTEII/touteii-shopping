package com.t.shopping.upload.test;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Import(FdfsClientConfig.class)
@SpringBootTest(classes=FastDFSTest.class)
@RunWith(SpringRunner.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastDFSTest {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Test
    public void testUpload() throws FileNotFoundException{
        File file=new File("D:\\T.jpg");
        StorePath storePath=this.storageClient.uploadFile(new FileInputStream(file),file.length(),"jpg",null);
        System.out.println(storePath.getFullPath());
        System.out.println(storePath.getPath());
    }

    @Test
    public void testUploadAndCreateThumb() throws FileNotFoundException{
        File file=new File("D:\\T.jpg");
        StorePath storePath=this.storageClient.uploadImageAndCrtThumbImage(new FileInputStream(file),file.length(),"png",null);
        System.out.println(storePath.getFullPath());
        System.out.println(storePath.getPath());
        String path=thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println(path);
    }

}