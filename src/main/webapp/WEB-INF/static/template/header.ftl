<#assign config_v="201601072310">
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="${seo_description}">
<meta property="qc:admins" content="74047741376755506375" />
    <link rel="icon" href="${TEMPLATE_BASE_PATH}/images/icon.bmp">
<title>${seo_title}</title>

<link href="${TEMPLATE_BASE_PATH}/css/bootstrap.min.css" rel="stylesheet">
<link href="${TEMPLATE_BASE_PATH}/css/blog.css" rel="stylesheet">
<link rel="stylesheet" href="${TEMPLATE_BASE_PATH}/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${TEMPLATE_BASE_PATH}/css/fontello.css"/>
<link rel="stylesheet" href="${TEMPLATE_BASE_PATH}/css/ylb-public.css"/>
<link rel="stylesheet" href="${TEMPLATE_BASE_PATH}/css/ylb-index.css"/>
    <style type="text/css">
        #scrollUp {
            bottom: 120px;
            right: 30px;
            padding: 10px 20px;
            background-color: #555;
            color: #fff;
        }
    </style>
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="${TEMPLATE_BASE_PATH}/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="${TEMPLATE_BASE_PATH}/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="${TEMPLATE_BASE_PATH}/js/html5shiv.min.js"></script>
      <script src="${TEMPLATE_BASE_PATH}/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <div class="blog-masthead">
    <div class="container">
        <nav class="blog-nav">
		<@folder_list_tag folderId= 0>
            <a class="blog-nav-item <#if 0==g_folderId>active</#if>" href="${BASE_PATH}/index.htm">首页</a>
			<#list tag_folder_list as tag_folder>
                <a class="blog-nav-item <#if tag_folder.folderId==g_folderId>active</#if>" href="<@folder_url_tag folderId=tag_folder.folderId/>">${tag_folder.name}</a>
			</#list>
            <#--<a class="blog-nav-item" href="${BASE_PATH}/admin/login.htm">登录</a>-->
		</@folder_list_tag>
            <a class="logoModal" href="/thirdpart/login.htm?source=QQ">
                <img alt="QQ登录" src="${TEMPLATE_BASE_PATH}/images/qq_bt_blue_76X24.png">
            </a>
        </nav>
    </div>
		</div>
</body>
