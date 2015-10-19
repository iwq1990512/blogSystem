

package com.cms.tag;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.Map;

import com.cms.exception.FolderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.Folder;
import com.cms.plugin.TagPlugin;
import com.cms.service.FolderService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Service
public class FolderTag extends TagPlugin {

	@Autowired
	private FolderService folderService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		Integer folderId = Integer.parseInt(params.get("folderId").toString());

		try {
			Folder folder = folderService.getFolderById(folderId);
			env.setVariable("tag_folder", DEFAULT_WRAPPER.wrap(folder));
		} catch (FolderNotFoundException e) {
			env.setVariable("tag_folder", DEFAULT_WRAPPER.wrap(new Folder()));
		}
		body.render(env.getOut());
	}

}
