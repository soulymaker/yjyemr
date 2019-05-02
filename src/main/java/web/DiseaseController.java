package web;

import BO.Result;
import Constants.Constant;
import DTO.QueryDTO;
import com.alibaba.fastjson.JSONObject;
import entity.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AddService;
import service.DeleteService;
import service.QueryService;
import service.UpdateService;

@RestController
@RequestMapping("/api")
public class DiseaseController {
    @Autowired
    private AddService addService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @RequestMapping(value = {"/queryDisease"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryDisease(@RequestBody Disease disease) {
        QueryDTO queryDTO = queryService.queryDisease(disease);
        if(queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/queryAllDisease"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryAllDisease() {
        Disease disease = new Disease();
        QueryDTO queryDTO = queryService.queryDisease(disease);
        if(queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/addDisease"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addDisease(@RequestBody Disease disease) {
        String result = addService.addDisease(disease);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/deleteDisease"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteDisease(@RequestBody Disease disease) {
        String result = deleteService.deleteDisease(disease.getId().toString());
        if(result.equals(Constant.DELETE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/updateDisease"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateDisease(@RequestBody Disease disease) {
        String result = updateService.updateDisease(disease);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }
}
