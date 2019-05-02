package web;

import BO.Result;
import Constants.Constant;
import DTO.QueryDTO;
import com.alibaba.fastjson.JSONObject;
import entity.TreatmentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AddService;
import service.DeleteService;
import service.QueryService;
import service.UpdateService;

@RestController
@RequestMapping("/api")
public class TreatmentRecordController {
    @Autowired
    private AddService addService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @RequestMapping(value = {"/queryTreatmentRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryTreatmentRecord(@RequestBody TreatmentRecord treatmentRecord) {
        QueryDTO queryDTO = queryService.queryTreatmentRecord(treatmentRecord);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/queryAllTreatmentRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryAllTreatmentRecord() {
        TreatmentRecord treatmentRecord = new TreatmentRecord();
        QueryDTO queryDTO = queryService.queryTreatmentRecord(treatmentRecord);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/addTreatmentRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addTreatmentRecord(@RequestBody TreatmentRecord treatmentRecord) {
        String result = addService.addTreatmentRecord(treatmentRecord);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/deleteTreatmentRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteTreatmentRecord(@RequestBody TreatmentRecord treatmentRecord) {
        String result = deleteService.deleteTreatmentRecord(treatmentRecord.getId().toString());
        if(result.equals(Constant.DELETE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/updateTreatmentRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateTreatmentRecord(@RequestBody TreatmentRecord treatmentRecord) {
        String result = updateService.updateTreatmentRecord(treatmentRecord);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }
}
