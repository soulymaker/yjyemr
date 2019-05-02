package web;

import BO.Result;
import Constants.Constant;
import DTO.QueryDTO;
import com.alibaba.fastjson.JSONObject;
import entity.TestRecord;
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
public class TestRecordController {
    @Autowired
    private AddService addService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @RequestMapping(value = {"/queryTestRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryTestRecord(@RequestBody TestRecord testRecord) {
        QueryDTO queryDTO = queryService.queryTestRecord(testRecord);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/addTestRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addTestRecord(@RequestBody TestRecord testRecord) {
        String result = addService.addTestRecord(testRecord);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/deleteTestRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteTestRecord(@RequestBody TestRecord testRecord) {
        String result = deleteService.deleteTestRecord(testRecord.getId().toString());
        if(result.equals(Constant.DELETE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/updateTestRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateTestRecord(@RequestBody TestRecord testRecord) {
        String result = updateService.updateTestRecord(testRecord);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }
}
