	<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<div class="sidebar-module sidebar-module-inset">
					<h4>本站介绍</h4>
					<p>
						本博客系统是一个轻量级的Java CMS。
						Spring MVC为表现层，Spring为业务层，MyBatis为数据层。项目大量使用注解，代码注释清晰，文档齐全，是学习和二次开发的首选。 
					</p>
				</div>
				<div class="sidebar-module sidebar-module-inset">
					<h4><a href="<@folder_url_tag folderId=1/>">博客目录</a></h4>
					<ol class="list-unstyled">
						<@folder_list_tag folderId= 1>
		                		<#list tag_folder_list as tag_folder>
						<li><a href="<@folder_url_tag folderId=tag_folder.folderId/>">${tag_folder.name}</a><li>
						</#list>
	               				</@folder_list_tag>
					</ol>
				</div>
				<div class="sidebar-module sidebar-module-inset">
					<h4>功能</h4>
					<ol class="list-unstyled">
						<li><a href="${BASE_PATH}/admin/login.htm">登录</a><li>
					</ol>
				</div>
				<div class="sidebar-module">
<#--					 <a target="_balnk" rel="nofollow" href="http://s.click.taobao.com/t?e=m%3D2%26s%3DIBpia%2BLo0tEcQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAjrHDnuydt9DjEirhkXBrn3taZwnSL6Nwn5Xa4s7Qs0aQZoJJOKTMC8aPrINdm0lpOdn1BbglxZYxUhy8exlzcq9AmARIwX9K%2BnbtOD3UdznPV1H2z0iQv9NkKVMHClW0QbMqOpFMIvnvjQXzzpXdTHGJe8N%2FwNpGw%3D%3D">
	 					<img style="" src="${TEMPLATE_BASE_PATH}/images/aliyun.jpg?v=${config_v}">
	 				</a>-->
				</div>
			</div>