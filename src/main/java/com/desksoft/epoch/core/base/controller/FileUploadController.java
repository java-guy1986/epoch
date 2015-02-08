package com.desksoft.epoch.core.base.controller;

import java.io.File;  
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.apache.commons.io.FileUtils;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestParam;  
import org.springframework.web.multipart.MultipartFile;  
  
/** 
 * SpringMVC中的文件上传 
 * 1)由于SpringMVC使用的是commons-fileupload实现,所以先要将其组件引入项目中 
 * 2)在SpringMVC配置文件中配置MultipartResolver处理器(可在此加入对上传文件的属性限制) 
 * 3)在Controller的方法中添加MultipartFile参数(该参数用于接收表单中file组件的内容) 
 * 4)编写前台表单(注意enctype="multipart/form-data"以及<input type="file" name="****"/>) 
 * PS:由于这里使用了ajaxfileupload.js实现无刷新上传,故本例中未使用表单 
 * --------------------------------------------------------------------------------------------- 
 * 这里用到了如下的jar 
 * commons-io-2.4.jar 
 * commons-fileupload-1.3.jar 
 * commons-logging-1.1.2.jar 
 * spring-aop-3.2.4.RELEASE.jar 
 * spring-beans-3.2.4.RELEASE.jar 
 * spring-context-3.2.4.RELEASE.jar 
 * spring-core-3.2.4.RELEASE.jar 
 * spring-expression-3.2.4.RELEASE.jar 
 * spring-jdbc-3.2.4.RELEASE.jar 
 * spring-oxm-3.2.4.RELEASE.jar 
 * spring-tx-3.2.4.RELEASE.jar 
 * spring-web-3.2.4.RELEASE.jar 
 * spring-webmvc-3.2.4.RELEASE.jar 
 * --------------------------------------------------------------------------------------------- 
 * @create Sep 14, 2013 5:06:09 PM 
 * @author 玄玉<http://blog.csdn.net/jadyer> 
 * http://localhost/epoch/fileUpload/index.do
 */  
@Controller  
@RequestMapping("/fileUpload")  
public class FileUploadController extends BaseController{  
	
	
	@RequestMapping(value="/index")
	public String index(){
		return "fileupload/index";
	}
	
    /** 
     * 这里这里用的是MultipartFile[] myfiles参数,所以前台就要用<input type="file" name="myfiles"/> 
     * 上传文件完毕后返回给前台[0`filepath],0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述) 
     */  
    @RequestMapping(value="/addUser")  
    public String addUser(String uname, @RequestParam MultipartFile[] myfiles) throws IOException{  
        //可以在上传文件的同时接收其它参数  
        System.out.println("收到用户[" + uname + "]的文件上传请求");  
        //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中  
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        HttpServletRequest request = this.getServletRequest();
        HttpServletResponse response = this.getServletResponse();
        String realPath = this.getHttpSession(request).getServletContext().getRealPath("/upload");  
        //设置响应给前台内容的数据格式  
        response.setContentType("text/plain; charset=UTF-8");  
        //设置响应给前台内容的PrintWriter对象  
        PrintWriter out = response.getWriter();  
        //上传文件的原名(即上传前的文件名字)  
        String originalFilename = null;  
        //如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解  
        //如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解  
        //上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件  
        for(MultipartFile myfile : myfiles){  
            if(myfile.isEmpty()){  
                out.print("1`请选择文件后上传");  
                out.flush();  
                return null;  
            }else{  
                originalFilename = myfile.getOriginalFilename();  
                System.out.println("文件原名: " + originalFilename);  
                System.out.println("文件名称: " + myfile.getName());  
                System.out.println("文件长度: " + myfile.getSize());  
                System.out.println("文件类型: " + myfile.getContentType());  
                System.out.println("========================================");  
                try {  
                    //这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉  
                    //此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传  
                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));  
                } catch (IOException e) {  
                    System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");  
                    e.printStackTrace();  
                    out.print("1`文件上传失败，请重试！！");  
                    out.flush();  
                    return null;  
                }  
            }  
        }  
        //此时在Windows下输出的是[D:\Develop\apache-tomcat-6.0.36\webapps\AjaxFileUpload\\upload\愤怒的小鸟.jpg]  
        //System.out.println(realPath + "\\" + originalFilename);  
        //此时在Windows下输出的是[/AjaxFileUpload/upload/愤怒的小鸟.jpg]  
        //System.out.println(request.getContextPath() + "/upload/" + originalFilename);  
        //不推荐返回[realPath + "\\" + originalFilename]的值  
        //因为在Windows下<img src="file:///D:/aa.jpg">能被firefox显示,而<img src="D:/aa.jpg">firefox是不认的  
        out.print("0`" + request.getContextPath() + "/upload/" + originalFilename);  
        out.flush();  
        return null;  
    }  
}  