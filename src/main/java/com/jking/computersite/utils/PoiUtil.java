package com.jking.computersite.utils;

import com.jking.computersite.constant.UploadConstant;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PoiUtil {
    public static String saveDoc(MultipartFile file){
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        String filePre = fileName.split("\\.")[0];
        String extension = fileName.split("\\.")[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String date = simpleDateFormat.format(new Date());

        String dir_path = UploadConstant.PUBLIC + UploadConstant.ARTICLE + date + "/";
        String htmlPath = UploadConstant.ARTICLE + date + "/";

        boolean off = true;
        while (off){
            off = false;
            File[] files = new File(dir_path).listFiles();
            StringBuilder sb = new StringBuilder(filePre);
            if (files != null) {
                for (File fileDir : files) {
                    String fileDirName = fileDir.getName();
                    System.out.println(fileDirName);
                    if (filePre.equals(fileDirName)) {
                        sb.append((int) (Math.random() * 1000));
                        off = true;
                        break;
                    }
                }
                dir_path += sb.toString() + "/";
                htmlPath += sb.toString() + "/";
            }
        }

        String path = dir_path + fileName;
        System.out.println(dir_path);
        try {
            File dir = new File(dir_path);
            if (!dir.exists()){
                dir.mkdirs();
                File imageDir = new File(dir_path + "images");
                imageDir.mkdirs();
            }
            FileUtil.saveFile(file,path);
            if (extension.equals("doc")){
                HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(path));
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
                // 保存图片，并返回图片的相对路径
                String imagePathStr = dir_path + "images/";
                wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
                    try (FileOutputStream out = new FileOutputStream(imagePathStr + name)) {
                        out.write(content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "images/" + name;
                });
                wordToHtmlConverter.processDocument(wordDocument);
                Document htmlDocument = wordToHtmlConverter.getDocument();
                DOMSource domSource = new DOMSource(htmlDocument);
                String targetFileName = dir_path + "article.html";
                StreamResult streamResult = new StreamResult(new File(targetFileName));

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer serializer = tf.newTransformer();
                serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
                serializer.setOutputProperty(OutputKeys.INDENT, "yes");
                serializer.setOutputProperty(OutputKeys.METHOD, "html");
                serializer.transform(domSource, streamResult);
            }else {
                XWPFDocument document = new XWPFDocument(new FileInputStream(path));
                XHTMLOptions options = XHTMLOptions.create();
                // 存放图片的文件夹
                options.setExtractor(new FileImageExtractor(new File(dir_path + "images/")));
                // html中图片的路径
                options.URIResolver(new BasicURIResolver("image"));
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(dir_path + "article.html"), "utf-8");
                XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
                xhtmlConverter.convert(document, outputStreamWriter, options);
                outputStreamWriter.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return htmlPath + "article.html";
    }

}