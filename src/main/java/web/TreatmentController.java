package web;

import BO.Result;
import Constants.Constant;
import DTO.QueryDTO;
import com.alibaba.fastjson.JSONObject;
import entity.Treatment;
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
public class TreatmentController {
    @Autowired
    private AddService addService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @RequestMapping(value = {"/queryTreatment"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryTreatment(@RequestBody Treatment treatment) {
        QueryDTO queryDTO = queryService.queryTreatment(treatment);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/addTreatment"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addTreatment(@RequestBody Treatment treatment) {
        String result = addService.addTreatment(treatment);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/deleteTreatment"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteTreatment(@RequestBody Treatment treatment) {
        String result = deleteService.deleteTreatment(treatment.getId().toString());
        if(result.equals(Constant.DELETE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/updateTreatment"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateTreatment(@RequestBody Treatment treatment) {
        String result = updateService.updateTreatment(treatment);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }
}
