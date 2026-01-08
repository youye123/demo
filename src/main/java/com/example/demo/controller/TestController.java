package com.example.demo.controller;

import static java.nio.file.Files.createFile;

import com.example.demo.util.FileTransformationUtil;
import com.example.demo.util.PdfToSingleImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

  @GetMapping("/hello")
  public String test() {
    return "hello word!";
  }

  @PostMapping("/batch/upload")
  public void bankReceiptBatchUpload(@RequestParam(value = "orgId", required = false) String orgId,
      @RequestParam("uploadFiles") List<MultipartFile> uploadFiles) {

    int successAccount = 0;

    for (MultipartFile uploadfile : uploadFiles) {

      try {

        String transferFilePath = null;
        String srcFileName = uploadfile.getOriginalFilename();
        String fileType = srcFileName.substring(srcFileName.lastIndexOf(".") + 1,
            srcFileName.length()).toLowerCase();
        log.info("fileType :" + fileType);

        //批量上传接口，要调用大模型解析，如果是pdf，则转换为图片
        if ("pdf".equalsIgnoreCase(fileType)) {
          String tempFileUrl = new StringBuffer("").append(File.separator).append("temp").append(File.separator).append("FileToGraph").toString();
          String tempGraphName = new StringBuffer(srcFileName.substring(0, srcFileName.lastIndexOf(".")))
              .append("_").append(System.currentTimeMillis())
              .append(".png").toString();  // 添加时间戳避免重名

          FileTransformationUtil.createDirectory(tempFileUrl);
          String tempPdfFilePath = new StringBuffer(tempFileUrl).append(File.separator).append(tempGraphName).toString();

          // 正确处理临时文件
          File tempPdfFile = createFile(tempPdfFilePath);
          tempPdfFile.setExecutable(true, true);
          tempPdfFile.setReadable(true, true);
          tempPdfFile.setWritable(true, true);

          // 使用正确的工具方法转换文件
          PdfToSingleImage converter = new PdfToSingleImage();
          File convertedFile = converter.convertPdfToSingleImage(tempPdfFile, tempPdfFilePath, 150f);

          if (convertedFile != null && convertedFile.exists()) {
            //transferFilePath = uploadPdf(convertedFile);
          }

          // 清理临时文件
          if (tempPdfFile.exists()) {
            tempPdfFile.delete();
          }
        }

      } catch (Exception e) {
        log.error("批量上传接口异常：{}", e.getMessage());
      }
    }
    return;
  }

  private File createFile(String filePath) throws Exception {
    File file = new File(filePath);
    file.setWritable(true, true);
    file.setReadable(true, true);
    file.setExecutable(true, true);
    if (file.exists()) {
      throw new Exception("文件已存在");
    } else {
      try {
        file.createNewFile();
      } catch (IOException e) {
        log.error("非pdf转pdf文件时，文件创建失败！", e.getMessage(), e);
      }
    }
    return file;
  }
}