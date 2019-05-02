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
public class DoctorController {

    @Autowired
    private AddService addService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;


    @RequestMapping(value = {"/web/queryDepartment"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryDepartment(@RequestBody Department department) {
        QueryDTO queryDTO = queryService.queryDepartment(department);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/web/queryDisease"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryDisease(@RequestBody Disease disease) {
        QueryDTO queryDTO = queryService.queryDisease(disease);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/web/queryHospital"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryHospital(@RequestBody Hospital hospital) {
        QueryDTO queryDTO = queryService.queryHospital(hospital);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    // 根据 doctorName 查询 自己诊断的病人
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

    @RequestMapping(value = {"/web/queryStaff"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryStaff(@RequestBody Staff staff) {
        QueryDTO queryDTO = queryService.queryStaff(staff);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/web/queryTest"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryTest(@RequestBody Test test) {
        QueryDTO queryDTO = queryService.queryTest(test);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }


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

    @RequestMapping(value = {"/web/queryTreatment"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryTreatment(@RequestBody Treatment treatment) {
        QueryDTO queryDTO = queryService.queryTreatment(treatment);
        if(!queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

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

    // 必传uid  doctorName
    @RequestMapping(value = {"/web/addRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addRecord(@RequestBody Record record) {
        String result = addService.addRecord(record);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    // 必传uid
    @RequestMapping(value = {"/web/addTestRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addTestRecord(@RequestBody TestRecord testRecord) {
        String result = addService.addTestRecord(testRecord);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    // 必传uid
    @RequestMapping(value = {"/web/addTreatmentRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addTreatmentRecord(@RequestBody TreatmentRecord treatmentRecord) {
        String result = addService.addTreatmentRecord(treatmentRecord);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    //仅更新密码
    @RequestMapping(value = {"/web/updateUser"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateUser(@RequestBody User user) {
        String result = updateService.updateUser(user);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }


    @RequestMapping(value = {"/web/updateRecord"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateRecord(@RequestBody Record record) {
        String result = updateService.updateRecord(record);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }
}
