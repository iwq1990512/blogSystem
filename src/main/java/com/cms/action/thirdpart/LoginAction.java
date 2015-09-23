package com.cms.action.thirdpart;

import com.cms.action.BaseAction;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yuheng on 2015/9/23.
 */
@Controller
@RequestMapping("/thirdpart")
public class LoginAction extends BaseAction {
    private final Logger logger = Logger.getLogger(LoginAction.class);

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String login(@RequestParam(value = "source")String source, HttpServletRequest request) {
        return "/manage/login";
    }
}
