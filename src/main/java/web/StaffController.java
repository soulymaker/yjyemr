package web;

import BO.Result;
import Constants.Constant;
import DTO.QueryDTO;
import com.alibaba.fastjson.JSONObject;
import entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AddService;
import service.DeleteService;
import service.QueryService;
import service.UpdateService;

@RestController
@RequestMapping("/api")
public class StaffController {
    @Autowired
    private AddService addService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @RequestMapping(value = {"/queryStaff"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryStaff(@RequestBody Staff staff) {
        QueryDTO queryDTO = queryService.queryStaff(staff);
        if(queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/queryAllStaff"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryAllStaff() {
        Staff staff = new Staff();
        QueryDTO queryDTO = queryService.queryStaff(staff);
        if(queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/addStaff"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addStaff(@RequestBody Staff staff) {
        String result = addService.addStaff(staff);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/deleteStaff"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteStaff(@RequestBody Staff staff) {
        String result = deleteService.deleteStaff(staff.getId().toString());
        if(result.equals(Constant.DELETE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/updateStaff"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateStaff(@RequestBody Staff staff) {
        String result = updateService.updateStaff(staff);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }
}
