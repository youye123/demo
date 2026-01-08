package com.example.demo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTransformationUtil {

  private FileTransformationUtil() {
    throw new IllegalStateException("Utility class");
  }

  private static final Logger logger = LoggerFactory.getLogger(FileTransformationUtil.class);

  // 获得指定文件的byte数组
  public static byte[] getBytes(String filePath) throws Exception {
    File file = new File(filePath);
    if (!file.exists()) {
      logger.error("非pdf转pdf文件时，指定路径的文件未找到！");
      throw new FileNotFoundException("文件未找到: " + filePath);
    }

    try (ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(file.toPath()))) {
      int buf_size = 1024;
      byte[] buffer = new byte[buf_size];
      int len = 0;
      while (-1 != (len = bis.read(buffer, 0, buf_size))) {
        bos.write(buffer, 0, len);
      }
      return bos.toByteArray();
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      throw e;
    }
  }

  /**
   * 关闭输入输出文件流
   *
   * @param bis
   * @param bos
   * @throws IOException
   */
  private static void closeStream(BufferedInputStream bis, ByteArrayOutputStream bos) throws IOException {
    try {
      if (bis != null) {
        bis.close();
      }
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    try {
      if (bos != null) {
        bos.flush();
        bos.close();
      }
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }

  /**
   * 创建文件目录
   *
   * @param filePath
   */
  public static void createDirectory(String filePath) {
    File dir = new File(filePath);
    dir.setWritable(true, true);
    dir.setReadable(true, true);
    dir.setExecutable(true, true);
    if (dir.exists() && dir.isDirectory()) {
      // 判断文件目录是否存在
      return;
    } else {
      if (dir.mkdirs()) {
        return;
      } else {
        logger.error("非pdf转pdf文件时，目录创建失败！");
      }
    }
  }

  public static File createFile(String filePath) throws Exception {
    File file = new File(filePath);
    file.setWritable(true, true);
    file.setReadable(true, true);
    file.setExecutable(true, true);
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        logger.error("非pdf转pdf文件时，文件创建失败！");
      }
    }
    return file;
  }

  // 根据byte数组，生成文件
  public static File getFile(byte[] bfile, String filePath, String fileName) throws Exception {
    createDirectory(filePath);
    File file = createFile(filePath + File.separator + fileName);
    file.setExecutable(true, true);
    file.setReadable(true, true);
    file.setWritable(true, true);
    try (FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);) {
      bos.write(bfile);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return file;
  }

  private static void closeStream(BufferedOutputStream bos, FileOutputStream fos) throws IOException {
    if (bos != null) {
      try {
        bos.flush();
        bos.close();
      } catch (IOException e1) {
        logger.error(e1.getMessage());
      }
    }
    if (fos != null) {
      try {
        fos.flush();
        fos.close();
      } catch (IOException e1) {
        logger.error(e1.getMessage());
      }
    }
  }

  /**
   * 删除临时文件
   *
   * @param sourFile
   * @param destFile
   */
  public static void deleteTempFile(File sourFile, File destFile) {
    if (sourFile != null && sourFile.exists()) {
      if (!sourFile.delete()) {
        logger.error("非pdf转pdf文件时，服务器上源文件删除失败！");
      }
    }
    if (destFile != null && destFile.exists()) {
      if (!destFile.delete()) {
        logger.error("非pdf转pdf文件时，服务器上目标文件删除失败！");
      }
    }
  }

  /**
   * 删除临时文件
   *
   * @param file
   */
  public static void deleteTempFile(File file) {
    if (file != null && file.exists()) {
      if (!file.delete()) {
        logger.error("非pdf转pdf文件时，服务器上临时文件删除失败！");
      }
    }
  }

  /**
   * 递归删除文件
   *
   * @param path
   */
  public static void deleteFileByPath(String path) {
    File file = new File(path);
    if (file.isDirectory()) {
      File[] files = file.listFiles();
      for (File f : files) {
        deleteFileByPath(f.getPath());
      }
    }
    file.delete();
  }
}
