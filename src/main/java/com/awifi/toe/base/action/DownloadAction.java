package com.awifi.toe.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.awifi.toe.base.util.IOUtil;

/**
 * @Description:
 * @Title: DownloadAction.java
 * @Package com.awifi.toe.base.action
 * @author kangyanxiang
 * @date 2015年7月21日 上午10:52:41
 * @version V1.0
 */

@Controller
@Scope("prototype")
@RequestMapping("/download")
public class DownloadAction extends BaseAction {

    /**
     * 下载模板
     * @param fileName 文件名
     * @param request  request
     * @param response response
     * @throws Exception 异常
     * @author kangyanxiang
     * @date 2015年7月21日 上午9:55:12
     */
    @RequestMapping(value = "/template")
    public void template(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getServletContext().getRealPath("file/template/");
        IOUtil.download(fileName, path, response);
    }

}
