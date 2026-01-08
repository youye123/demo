package com.example.demo.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.web.multipart.MultipartFile;

public class PdfToSingleImage {

    public static File convertPdfToSingleImage(String pdfPath, String outputImagePath, float dpi) {
        return convertPdfToSingleImage(new File(pdfPath), outputImagePath, dpi);
    }

    public static File convertPdfToSingleImage(File file, String outputImagePath, float dpi) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFRenderer renderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();

            // 存储每一页的 BufferedImage
            List<BufferedImage> images = new ArrayList<>();

            int maxWidth = 0;
            int totalHeight = 0;

            // 第一步：渲染所有页面，并计算总高度和最大宽度
            for (int i = 0; i < pageCount; i++) {
                BufferedImage img = renderer.renderImageWithDPI(i, dpi, org.apache.pdfbox.rendering.ImageType.RGB);
                images.add(img);
                maxWidth = Math.max(maxWidth, img.getWidth());
                totalHeight += img.getHeight();
            }

            // 第二步：创建一个大画布（RGB，无透明）
            BufferedImage combinedImage = new BufferedImage(maxWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = combinedImage.createGraphics();

            // 可选：设置背景为白色（防止透明区域变黑）
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, maxWidth, totalHeight);

            // 第三步：依次绘制每一页
            int y = 0;
            for (BufferedImage img : images) {
                // 如果某页宽度小于 maxWidth，居中或左对齐（这里左对齐）
                g2d.drawImage(img, 0, y, null);
                y += img.getHeight();
            }
            g2d.dispose();

            // 第四步：保存图像
            String format = outputImagePath.toLowerCase().endsWith(".png") ? "png" : "jpg";
            File outputFile = new File(outputImagePath);
            ImageIO.write(combinedImage, format, outputFile);

            System.out.println("✅ 成功生成单张图片: " + outputImagePath);
            return outputFile;  // 返回生成的文件对象

        } catch (IOException e) {
            System.err.println("❌ 转换失败: " + e.getMessage());
            e.printStackTrace();
            return null;  // 出现异常时返回null
        }
    }


    public static File multipartFileToFile(MultipartFile multipartFile, File file) throws IOException {

        // 将 MultipartFile 写入临时文件
        multipartFile.transferTo(file);

        // 删除时自动清理（可选）
        file.deleteOnExit();

        return file;
    }

    private String getExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        int dotIndex = filename.lastIndexOf(".");
        return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
    }

    public static void main(String[] args) {
        String pdfFile = "C:\\Users\\Administrator\\Downloads\\正常业务1.pdf";           // 输入 PDF 文件路径
        String outputImage = "C:\\Users\\Administrator\\Downloads\\正常业务1.png";   // 输出目录
        float dpi = 150f;                      // 渲染清晰度（建议 100~300）

        convertPdfToSingleImage(pdfFile, outputImage, dpi);
    }
}