
package com.cms.action;

import com.cms.entity.vo.FolderVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cms.entity.Folder;

/**
 * @author yuheng
 * 
 */
@Controller
@RequestMapping("/folder")
public class FolderAction extends BaseAction {

	/**
	 * 目录ID
	 * 
	 * @param ename
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/{folderId}.htm", method = RequestMethod.GET)
	public String folder(@PathVariable long folderId,
			@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		try {
			Folder folder = folderService.getFolderById(folderId);
			modelMap.put("folder", folder);
			FolderVo fatherFolder = folderService.getFolderById(folderService.firstFolderId(folderId));
			modelMap.put("g_folderId", fatherFolder.getFolderId());
			modelMap.put("p", p);
			return themeService.getFolderTemplate(folder.getFolderId());
		} catch (Exception e) {
			modelMap.addAttribute("g_folderId", 0);
			logger.fatal(e.getMessage());
			return themeService.get404();
		}
	}
}
