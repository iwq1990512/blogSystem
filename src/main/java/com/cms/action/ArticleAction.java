

package com.cms.action;

import com.cms.entity.vo.ArticleVo;
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
@RequestMapping("/article")
public class ArticleAction extends BaseAction {

	@RequestMapping(value = "/{articleId}.htm", method = RequestMethod.GET)
	public String article(@PathVariable long articleId,
			@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		try {
			ArticleVo article = fileService.getArticleById(articleId);
			Folder folder = folderService.getFolderById(article.getFolderId());
			modelMap.addAttribute("p", p);
			modelMap.addAttribute("folder", folder);
			modelMap.addAttribute("article", article);
			modelMap.addAttribute("g_folderId", folderService.firstFolderId(folder.getFolderId()));
			return themeService.getArticleTemplate(article.getFolderId(),
					articleId);
		} catch (Exception e) {
			modelMap.addAttribute("g_folderId", 0);
			return themeService.get404();
		}
	}
}
