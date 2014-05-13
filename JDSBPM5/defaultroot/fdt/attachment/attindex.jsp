<%@ page contentType="text/html;charset=gb2312" %>
<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<head>
    <title>Upload Control</title>
    <link rel="stylesheet" type="text/css" href="<%=path %>/common/attachment/css/dhtmlXVault.css" />

    <script language="JavaScript" type="text/javascript" src="<%=path %>/common/attachment/js/dhtmlXVault.js"></script>
</head>
<body >
    <div class="hdr"></div>
    <div id="vaultatt">
    </div>

<!--<XMP>
<div id="vaultDiv"></div>
<script>
	vault=new dhtmlXVaultObject();
            vault.setServerHandlers("UploadHandler.jsp",
                                    "GetInfoHandler.jsp",
                                    "GetIdHandler.jsp");
            vault.create("vaultDiv");
</script>
</XMP>


--></body>
 <script language="JavaScript" type="text/javascript">
        var vault = null;
        doOnLoad();
        function doOnLoad() {
            preLoadImages();
            vault = new dhtmlXVaultObject();
            vault.setPath("<%=path %>/common/attachment/");
            vault.processInstId = document.sysdemoupdate.processInstId.value;
            vault.activityInstId = document.sysdemoupdate.activityInstId.value;
            vault.setServerHandlers("fdtfileUpload.action", "<%=path %>/common/attachment/GetInfoHandler.jsp", "<%=basePath %>/common/attachment/GetIdHandler.jsp");
            vault.create("vaultatt");
            vault.fileList();
	}

	function preLoadImages(){
		var imSrcAr = new Array("btn_add.gif","btn_clean.gif","btn_upload.gif","ico_file.png","ico_image.png","ico_sound.png","ico_video.png","ico_zip.png","pb_back.gif","pb_demoUload.gif","pb_empty.gif");
		var imAr = new Array(0);
		for(var i=0;i<imSrcAr.length;i++){
			imAr[imAr.length] = new Image();
			imAr[imAr.length-1].src = "imgs/"+imSrcAr[i];
		}
	}
	 
    </script>

