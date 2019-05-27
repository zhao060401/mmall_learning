<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <style>
        .footer {
            text-align: center;
            width: 100%;
            position: fixed;
            bottom: 0;
        }
    </style>
</head>
<body>
<h2>springmvc上传文件</h2>

<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="springmvc上传文件"/>
</form>


富文本图片上传文件
<form name="form2" action="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="富文本图片上传文件"/>
</form>


<img src="/manage/product/getImage.do?fileName=0748ab6a-9df1-4f44-82cd-bb9f10f3baec.jpg"
     style="width: 10%;height:auto;">图片</img>
<div class="footer"><a href="http://www.miitbeian.gov.cn/" target="_blank">京ICP备19021477号-1</a></div>
</body>
</html>
