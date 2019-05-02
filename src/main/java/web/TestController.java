package web;

import BO.Result;
import Constants.Constant;
import DTO.QueryDTO;
import com.alibaba.fastjson.JSONObject;
import entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AddService;
import service.DeleteService;
import service.QueryService;
import service.UpdateService;

@RestController
@RequestMapping("/api")
public class TestController {
    @Autowired
    private AddService addService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @RequestMapping(value = {"/queryTest"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryTest(@RequestBody Test test) {
        QueryDTO queryDTO = queryService.queryTest(test);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/queryAllTest"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryAllTest() {
        Test test = new Test();
        QueryDTO queryDTO = queryService.queryTest(test);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/addTest"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addTest(@RequestBody Test test) {
        String result = addService.addTest(test);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/deleteTest"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteTest(@RequestBody Test test) {
        String result = deleteService.deleteTest(test.getId().toString());
        if(result.equals(Constant.DELETE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/updateTest"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateTest(@RequestBody Test test) {
        String result = updateService.updateTest(test);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }


}
