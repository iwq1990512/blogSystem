
package com.cms.tag;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.plugin.TagPlugin;
import com.cms.service.FolderService;
import com.cms.util.HttpUtils;
import com.cms.util.PropertyUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * folder标签
 * 
 * @author lqq
 * 
 */
@Service
public class FolderUrlTag extends TagPlugin {
	@Autowired
	private FolderService folderService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String folderId = params.get("folderId").toString();
		String basePath = HttpUtils.getBasePath(request);
		if (Boolean.getBoolean(PropertyUtils.getValue("shishuo.static"))) {
			env.getOut().write(basePath + "/html/folder/" + folderId + ".html");
		} else {
			env.getOut().write(basePath + "/folder/" + folderId + ".htm");
		}
	}

}
