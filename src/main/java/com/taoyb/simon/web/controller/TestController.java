package com.taoyb.simon.web.controller;


import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybTest;
import com.taoyb.simon.web.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by taoyb on 2016-12-05.
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

   @RequestMapping(value = "/formAll")
    public String formAll(Model model, TybTest test) {
        Pager<TybTest> pager = tybTestService.findAllTests(test,1,15);
        model.addAttribute("pager", pager);
        //model.addAttribute("m",request.getParameter("m"));
        return "test/formAll";
    }
     /*@RequestMapping(value = "/formToAdd")
    public String formToAdd(Model model,TybTest test) {
        if (test!=null && test.getTestId() != null){
            TybTest te = tybTestService.findById(test.getTestId());
            model.addAttribute("test",te);
        }
        model.addAttribute("m",request.getParameter("m"));
        return "test/form_add";
    }
    @RequestMapping(value = "/formAdd")
    public @ResponseBody
    AjaxDone formAdd(TybTest test) {
        return tybTestService.saveTest(test,request.getParameter("m"));
    }
    @RequestMapping(value = "/formDel")
    public @ResponseBody AjaxDone formDel(Long tid) {
        return tybTestService.delTest(tid);
    }*/
}
