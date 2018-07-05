/*
package com.simon.web.controller;


import com.simon.web.base.BaseController;
import com.simon.web.model.TybUser;
import com.simon.web.utils.AuthPassport;
import com.simon.web.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

*/
/**
 * 用户
 * Created by taoyb on 2017-05-26.
 *//*

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    */
/**
     * @param currentPage 当前页
     * @return 用户列表查询
     *//*

    @RequestMapping(value = "/findAllUsers", method = RequestMethod.GET)
    @AuthPassport(authority= Simon.AUTH_USER)
    public @ResponseBody
    JsonResult findAllUsers(Integer currentPage) {
        return new JsonResult(tybUserService.findAllUsers(currentPage));
    }

    */
/**
     * 新增修改页
     * @param user
     * @return
     *//*

    @RequestMapping(value = "/detailUser", method = RequestMethod.GET)
    @AuthPassport(authority= Simon.AUTH_USER)
    public @ResponseBody JsonResult detailUser(TybUser user) {
        if (user!=null && user.getUserId()!=null){
            user = tybUserService.findById(user.getUserId());
        }else {
            user = new TybUser();
        }
        return new JsonResult( user);
    }

    */
/**
     * 更新用户
     * @param user
     * @return
     *//*

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @AuthPassport(authority= Simon.AUTH_USER)
    public @ResponseBody JsonResult updateUser(TybUser user) {
        if (user!=null && user.getUserId()!=null){
            user.setUpdateTime(new Date());
            tybUserService.updateEntity(user);
        }else {
            tybUserService.addEntity(user);
        }
        return new JsonResult( user);
    }

    */
/**
     * 删除用户
     * @param userId
     * @return
     *//*

    @RequestMapping("/deleteUser")
    @AuthPassport(authority= Simon.AUTH_USER)
    public @ResponseBody JsonResult deleteUser(Long userId){
        tybUserService.delEntity(userId);
        return new JsonResult(1,null);
    }
}
*/
