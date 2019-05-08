package web;

import BO.Result;
import Constants.Constant;
import DTO.LoginDTO;
import DTO.QueryDTO;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AddService;
import service.DeleteService;
import service.QueryService;
import service.UpdateService;
import utils.encrypt.Md5;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private AddService addService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestBody LoginDTO l) {
        User user = new User();
        user.setUserId(l.getUserId());
        user.setPassword(l.getPassword());
        LoginDTO result = queryService.queryUser(user);
        if (result == null) {
            return Result.returnData("登录失败", "登录失败");
        } else {
            Map<String, String> map = Maps.newHashMap();
            map.put("userId", result.getUserId());
            map.put("userType", result.getUserType());
            Map<String,Object> userMap=Maps.newHashMap();
            userMap.put("user",map);
            return Result.returnData(null, "success", userMap);
        }
    }

    @RequestMapping(value = {"/queryUser"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryUser(@RequestBody User user) {
        QueryDTO queryDTO = queryService.queryUser2(user);
        if(queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/queryAllUser"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryAllUser() {
        User user = new User();
        QueryDTO queryDTO = queryService.queryUser2(user);
        if(queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addUser(@RequestBody User user) {
        String result = addService.addUser(user);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/deleteUser"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteUser(@RequestBody User user) {
        String result = deleteService.deleteUser(user.getId().toString());
        if(result.equals(Constant.DELETE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/updateUser"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateUser(@RequestBody User user) {
        String result = updateService.updateUser(user);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

}
