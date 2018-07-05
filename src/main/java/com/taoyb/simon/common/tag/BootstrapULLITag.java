/*
package com.taoyb.simon.common.tag;
import com.taoyb.simon.common.SpringContextHolder;
import com.taoyb.simon.web.model.TybMenus;
import com.taoyb.simon.web.service.TybMenusService;
import org.springframework.beans.BeansException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

*/
/**
 * Created by taoyb on 2016-12-11.
 *//*

public class BootstrapULLITag extends TagSupport {
    private String number;
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    @Override
    public int doEndTag() throws JspException {
        //HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            System.out.println(number);
            TybMenusService tybMenusService = SpringContextHolder.getBean("tybMenusServiceImpl");
            List<TybMenus> list = tybMenusService.findAllByParentId(1l);
            String str = "";
            for (int i = 0; i < list.size(); i++) {
                TybMenus tm = list.get(i);
                if (tm.getMenuStat() == TybMenus.STAT_FJ) {
                    str += "<li class=\"dropdown\"><a href=\"javascript:;\" ui-sref =\""+tm.getMenuUrl()+"\" style=\"background: url('"+tm.getImgUrl()+"') 11px 11px no-repeat;\">" +
                            "<span class=\"menu-item\">" + tm.getMenuName() + "</span></a><ul class=\"list-unstyled menu-item\">";
                    List<TybMenus> parList = tybMenusService.findAllByParentId(tm.getMenuId());
                    for (int j = 0; j < parList.size(); j++) {
                        str += "<li><a href=\"javascript:;\">" + parList.get(j).getMenuName() + "</a></li>";
                    }
                    str += "</ul></li>";
                } else {
                    str += "<li><a href=\"javascript:;\" ui-sref =\""+tm.getMenuUrl()+"\"  style=\"background: url('" + tm.getImgUrl() + "') 11px 11px no-repeat;\">" +
                            "<span class=\"menu-item\">" + tm.getMenuName() + "</span></a></li>";
                }
            }
            pageContext.getOut().print(str);
        } catch (BeansException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doEndTag();
    }
}
*/
