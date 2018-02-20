package com.jking.computersite.utils;

import com.jking.computersite.constant.UploadConstant;
import com.jking.computersite.enums.UploadEnums;
import com.jking.computersite.exception.MyException;
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
        String filePre = fileName.split("\\.")[0];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String date = simpleDateFormat.format(new Date());

        String dir_path = UploadConstant.PUBLIC + UploadConstant.ARTICLE + date + "/";
        String htmlPath = UploadConstant.ARTICLE + date + "/";

        while (true){
            File[] files = new File(dir_path).listFiles();
            StringBuilder sb = new StringBuilder(filePre);
            if (files != null) {
                for (File fileDir : files) {
                    String fileDirName = fileDir.getName();
                    if (filePre.equals(fileDirName)) {
                        sb.append("-"+(int) (Math.random() * 1000));
                        break;
                    }
                }
            }
            System.out.println(sb.toString());
            dir_path += sb.toString() + "/";
            htmlPath += sb.toString() + "/";
            break;
        }

        String path = dir_path + fileName;
        try {
            FileUtil.saveFile(file,path);
            save(file,dir_path);
        }catch (Exception e){
            e.printStackTrace();
        }

        return htmlPath;
    }

    public static String previewDoc(MultipartFile file){
        FileUtil.isDoc(file);
        String dir_path = UploadConstant.PUBLIC + UploadConstant.WORDPREVIEW;
        FileUtil.delAllFile(UploadConstant.PUBLIC + UploadConstant.WORDPREVIEW);
        save(file,dir_path);
        return UploadConstant.WORDPREVIEW + "article.html";
    }

    public static void delDoc(String path){
        FileUtil.delFolder(UploadConstant.PUBLIC + path);
    }

    public static String getDoc(String path){
        File file = new File(UploadConstant.PUBLIC + path);
        if (!file.exists()){
            throw new MyException(UploadEnums.FILE_NOT_FOUND);
        }
        return path + "article.html";
    }

    public static void save(MultipartFile file,String dir_path){
        try {
            String fileName = file.getOriginalFilename();
            String path = dir_path + fileName;
            String extension = fileName.split("\\.")[1];
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
                File imagesDir = new File(imagePathStr);
                if (!imagesDir.exists()){
                    imagesDir.mkdirs();
                }
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
                options.URIResolver(new BasicURIResolver("images/"));
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(dir_path + "article.html"), "utf-8");
                XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
                xhtmlConverter.convert(document, outputStreamWriter, options);
                outputStreamWriter.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}