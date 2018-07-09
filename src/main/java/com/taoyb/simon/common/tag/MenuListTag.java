package com.taoyb.simon.common.tag;
import com.taoyb.simon.common.SpringContextHolder;
import com.taoyb.simon.web.model.TybMenus;
import com.taoyb.simon.web.service.TybMenusService;
import org.springframework.beans.BeansException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by taoyb on 2016-12-11.
 */
public class MenuListTag extends TagSupport {
    private String number;
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    @Override
    public int doStartTag() throws JspException {
        try {
            System.out.println(number);
            TybMenusService tybMenusService = SpringContextHolder.getBean("tybMenusServiceImpl");
            List<TybMenus> list = tybMenusService.findAllByParentId(Long.parseLong(number),"web");
            String str = "";
            for (int i = 0; i < list.size(); i++) {
                TybMenus tm = list.get(i);
                /*str += "<div class=\"toggleCollapse\"><h2>"+tm.getMenuName()+"</h2><div>收缩</div></div>\n<div class=\"accordion\" fillSpace=\"sidebar\">\n";*/
                List<TybMenus> parList = tybMenusService.findAllByParentId(tm.getMenuId(),"web");
                for (int j = 0; j < parList.size(); j++) {
                    TybMenus tmj = parList.get(j);
                    str+="<div class=\"accordionHeader\">\n<h2><span>Folder</span>"+tmj.getMenuName()+"</h2>\n</div><div class=\"accordionContent\">\n<ul class=\"tree treeFolder\">";
                    List<TybMenus> parListk = tybMenusService.findAllByParentId(tmj.getMenuId(),"web");
                    for (int k = 0; k < parListk.size(); k++) {
                        TybMenus tmk = parListk.get(k);
                        List<TybMenus> parListm = tybMenusService.findAllByParentId(tmk.getMenuId(),"web");
                        if (parListm.size()>0){
                            str += "<li><a>"+tmk.getMenuName()+"</a><ul>";
                            for (int l = 0; l < parListm.size(); l++) {
                                str += "<li><a href=\""+(parListm.get(l).getMenuUrl().indexOf("?")>0?parListm.get(l).getMenuUrl()+"&m=main"+l+k+j:parListm.get(l).getMenuUrl()+"?m=main"+l+k+j)+"\" target=\"navTab\" rel=\"main"+l+k+j+"\">"+parListm.get(l).getMenuName()+"</a></li>";
                            }
                            str += "</ul></li>";
                        }else {
                            str += "<li><a href=\""+(tmk.getMenuUrl().indexOf("?")>0?tmk.getMenuUrl()+"&m=par"+k+j:tmk.getMenuUrl()+"?m=par"+k+j)+"\" target=\"navTab\" external=\"true\" rel=\"par"+k+j+"\">"+tmk.getMenuName()+"</a></li>";
                        }
                    }
                    str+="</ul>\n</div>";
                }
                /*str+="</div>";*/
            }
            /*for (int i = 0; i < list.size(); i++) {
                TybMenus tm = list.get(i);
                if (tm.getMenuStat() == TybMenus.STAT_FJ) {
                    str += "<li><a href=\""+tm.getMenuUrl()+"\" target=\"navTab\">"+tm.getMenuName()+"</a><ul>";
                    List<TybMenus> parList = tybMenusService.findAllByParentId(tm.getMenuId(),"web");
                    for (int j = 0; j < parList.size(); j++) {
                        str += "<li><a href=\""+parList.get(j).getMenuUrl()+"\" target=\"navTab\" rel=\"main"+j+"\">"+parList.get(j).getMenuName()+"</a></li>";
                    }
                    str += "</ul></li>";
                } else {
                    str += "<li><a href=\""+tm.getMenuUrl()+"\" target=\"navTab\" external=\"true\" rel=\"main" + i + "\">"+tm.getMenuName()+"</a></li>";
                }
            }*/
            pageContext.getOut().print(str);
        } catch (BeansException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
}
