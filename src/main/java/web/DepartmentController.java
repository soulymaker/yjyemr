package web;

import BO.Result;
import Constants.Constant;
import DTO.QueryDTO;
import com.alibaba.fastjson.JSONObject;
import entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AddService;
import service.DeleteService;
import service.QueryService;
import service.UpdateService;

@Controller
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private AddService addService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @RequestMapping(value = {"/queryDepartment"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryDepartment(@RequestBody Department department) {
        QueryDTO queryDTO = queryService.queryDepartment(department);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/web/addDepartment"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addDepartemnt(@RequestBody Department department) {
        String result = addService.addDepartment(department);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/deleteDepartment"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteDepartemnt(@RequestBody Department department) {
        String result = deleteService.deleteDepartment(department.getId().toString());
        if(result.equals(Constant.DELETE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/updateDepartment"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateDepartemnt(@RequestBody Department department) {
        String result = updateService.updateDepartment(department);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }
}
