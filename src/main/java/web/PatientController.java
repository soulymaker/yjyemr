package web;

import BO.Result;
import Constants.Constant;
import DTO.QueryDTO;
import com.alibaba.fastjson.JSONObject;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AddService;
import service.DeleteService;
import service.QueryService;
import service.UpdateService;

@RestController
public class PatientController{

    @Autowired
    private AddService addService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    //仅查询 自己  by uid
    @RequestMapping(value = {"/web/queryPatient"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryPatient(@RequestBody Patient patient) {
        QueryDTO queryDTO = queryService.queryPatient(patient);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    // 仅查询自己记录  by uid
    @RequestMapping(value = {"/web/queryRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryRecord(@RequestBody Record record) {
        QueryDTO queryDTO = queryService.queryRecord(record);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    //by uid
    @RequestMapping(value = {"/web/queryTestRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryTestRecord(@RequestBody TestRecord testRecord) {
        QueryDTO queryDTO = queryService.queryTestRecord(testRecord);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    // by uid
    @RequestMapping(value = {"/web/queryTreatmentRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryTreatmentRecord(@RequestBody TreatmentRecord treatmentRecord) {
        QueryDTO queryDTO = queryService.queryTreatmentRecord(treatmentRecord);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    // by uid 修改对应记录
    @RequestMapping(value = {"/web/updatePatient"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updatePatient(@RequestBody Patient patient) {
        String result = updateService.updatePatient(patient);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    //仅更新密码  by uid
    @RequestMapping(value = {"/web/updateUser"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateUser(@RequestBody User user) {
        String result = updateService.updateUser(user);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

}
