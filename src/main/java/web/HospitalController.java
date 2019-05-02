package web;

import BO.Result;
import Constants.Constant;
import DTO.QueryDTO;
import com.alibaba.fastjson.JSONObject;
import entity.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AddService;
import service.DeleteService;
import service.QueryService;
import service.UpdateService;

@RestController
@RequestMapping("/api")
public class HospitalController {
    @Autowired
    private AddService addService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @RequestMapping(value = {"/queryHospital"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryHospital(@RequestBody Hospital hospital) {
        QueryDTO queryDTO = queryService.queryHospital(hospital);
        if(queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/queryAllHospital"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryAllHospital() {
        Hospital hospital = new Hospital();
        QueryDTO queryDTO = queryService.queryHospital(hospital);
        if(queryDTO.getFlag().equals(Constant.QUERY_SUCCESS)) {
            return Result.returnData(queryDTO.getResult());
        } else {
            return Result.returnData(queryDTO.getFlag());
        }
    }

    @RequestMapping(value = {"/addHospital"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addHospital(@RequestBody Hospital hospital) {
        String result = addService.addHospital(hospital);
        if(result.equals(Constant.ADD_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/deleteHospital"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteHospital(@RequestBody Hospital hospital) {
        String result = deleteService.deleteHospital(hospital.getId().toString());
        if(result.equals(Constant.DELETE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }

    @RequestMapping(value = {"/updateHospital"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateHospital(@RequestBody Hospital hospital) {
        String result = updateService.updateHospital(hospital);
        if(result.equals(Constant.UPDATE_SUCCESS))
            return Result.returnData(null,result);
        else
            return Result.returnData(result,null);
    }
}
