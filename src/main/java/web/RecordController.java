package web;

import BO.Result;
import Constants.Constant;
import DTO.QueryDTO;
import com.alibaba.fastjson.JSONObject;
import entity.Record;
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
public class RecordController {
    @Autowired
    private AddService addService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @RequestMapping(value = {"/queryRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryRecord(@RequestBody Record record) {
        QueryDTO queryDTO = queryService.queryRecord(record);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }
    @RequestMapping(value = {"/addRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addRecord(@RequestBody Record record) {
        String result = addService.addRecord(record);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/deleteRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteRecord(@RequestBody Record record) {
        String result = deleteService.deleteRecord(record.getId().toString());
        if(result.equals(Constant.DELETE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/updateRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateRecord(@RequestBody Record record) {
        String result = updateService.updateRecord(record);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }
}
