package com.taoyb.simon.web.controller;


import com.taoyb.simon.common.mail.MailUtil;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybRecord;
import com.taoyb.simon.web.utils.AjaxDone;
import com.taoyb.simon.web.utils.BaseController;
import com.taoyb.simon.web.utils.Tree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by taoyb on 2016-12-05.
 */
@Controller
@RequestMapping("/record")
public class TybRecordController extends BaseController {
    @RequestMapping(value = "/recordAll")
    public String recordAll(Model model, TybRecord record) {
        Pager<TybRecord> pager = tybRecordService.findAllRecords(record, getPageNum(), getPageSize());
        model.addAttribute("pager", pager);
        model.addAttribute("m", request.getParameter("m"));
        return "function/recordAll";
    }

    @RequestMapping(value = "/recordToSaveOrUp")
    public String recordToAdd(Model model, TybRecord record) {
        if (record != null && record.getRecordId() != null) {
            TybRecord rec = tybRecordService.findById(record.getRecordId());
            model.addAttribute("record", rec);
        }
        model.addAttribute("m", request.getParameter("m"));
        return "function/recordSaveOrUp";
    }

    @RequestMapping(value = "/recordAdd")
    public @ResponseBody
    AjaxDone recordAdd(TybRecord record) {
        return tybRecordService.saveRecord(record, request.getParameter("m"));
    }

    @RequestMapping(value = "/recordDel")
    public @ResponseBody
    AjaxDone recordDel(Long rid) {
        return tybRecordService.delRecord(rid);
    }

    //详情
    @RequestMapping(value = "/recordToInfo")
    public String recordToInfo(Model model, TybRecord record) {
        TybRecord rec = tybRecordService.findById(record.getRecordId());
        model.addAttribute("record", rec);
        model.addAttribute("m", request.getParameter("m"));
        return "function/recordInfo";
    }

    /**
     * @作者 TYB
     * @日期 2018-01-25 下午 5:52
     * @描述 跳转发送邮件页面
     */
    @RequestMapping(value = "/toSendMail", method = RequestMethod.GET)
    public String toSendMail(Model model) {
        return "left/mail_form";
    }

    /**
     * @作者 TYB
     * @日期 2018-01-25 下午 5:55
     * @描述 发送邮件
     */
    @RequestMapping(value = "/sendMail")
    public @ResponseBody
    AjaxDone sendMail(Model model) {
        try {
            String to = request.getParameter("to");
            String subject = request.getParameter("subject");
            String html = request.getParameter("html");
            MailUtil.sendMail(to, subject, html);
            return AjaxDone.successCloseRel("jbsxBox");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error();
        }
    }

    /**
     * @作者 TYB
     * @日期 2018-07-28 下午 9:34
     * @描述 添加父节点
     */
    @RequestMapping(value = "/saveLeftNote")
    public @ResponseBody
    Tree saveLeftNote(TybRecord record) {
        return tybRecordService.toTransforTreeByRecord(tybRecordService.addEntity(record));
    }
    /**
     * @作者 TYB
     * @日期 2018-07-28 下午 10:00
     * @描述 修改节点名称
     */
    @RequestMapping(value = "/updateLeftNoteName")
    public @ResponseBody
    TybRecord updateLeftNoteName(TybRecord record) {
        TybRecord re = tybRecordService.findById(record.getRecordId());
        re.setRecordName(record.getRecordName());
        tybRecordService.updateEntity(re);
        return re;
    }

    @RequestMapping(value = "/deleteLeftNote")
    public @ResponseBody void deleteLeftNote(TybRecord record) {
        tybRecordService.delEntity(record.getRecordId());
    }

    @RequestMapping(value = "/addContent")
    public String addContent(Model model, TybRecord record) {
            TybRecord rec = tybRecordService.findById(record.getRecordId());
            model.addAttribute("record", rec);
        model.addAttribute("m", request.getParameter("m"));
        return "blog/addContent";
    }

    @RequestMapping(value = "/record_left",method = RequestMethod.POST)
    public @ResponseBody
    List<Tree> recordLeft(HttpServletRequest request) {
        String pid = request.getParameter("id");
        List<Tree> list= tybRecordService.findTreeRecordAll(pid,false);
        return list;
    }
}
