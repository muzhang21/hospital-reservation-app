package com.muzhang.resr.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muzhang.common.result.Result;
import com.muzhang.common.utils.MD5;
import com.muzhang.hospital.model.hosp.HospSetQueryVo;
import com.muzhang.hospital.model.hosp.HospitalSet;
import com.muzhang.resr.hosp.service.HospitalSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.lang.annotation.Retention;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    // 1.query info of hosp_resr_manage
    @GetMapping("findAll")
    public Result findAllHospitalSet(){
        List<HospitalSet> hospitalSetList = hospitalSetService.list();
        return Result.ok(hospitalSetList);
    }

    //2. delete hospital settings (Using swagger to test methods)
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if(flag){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //3. search by name and code and pagination
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospSetQueryVo hospSetQueryVo){
        Page<HospitalSet> page = new Page<>(current,limit);
        //condition query
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hosname = hospSetQueryVo.getHosname();
        String hoscode = hospSetQueryVo.getHoscode();
        wrapper.eq("hoscode",hospSetQueryVo.getHoscode());
        if(!StringUtils.hasLength(hosname)){
            wrapper.like("hosname",hospSetQueryVo.getHosname());
        }
        if(!StringUtils.hasLength(hoscode)){
            wrapper.eq(hoscode,hospSetQueryVo.getHoscode());
        }
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page,wrapper);

        return Result.ok(pageHospitalSet);
    }

    //Add hospital setting
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){
        //set status: 1: available, 0 unavailable
        hospitalSet.setStatus(1);
        //set sign key
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        //use service
        boolean save = hospitalSetService.save(hospitalSet);
        if(save){
            return Result.ok();
        }
        else {
            return Result.fail();
        }

    }

    //5.get hospital setting by hospital id
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    //6.update hospital setting
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if(flag){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //batch deletion
    @DeleteMapping("batchDelete")
    public Result batchDeleteHospitalSet(@RequestBody List<Long> idList){
        hospitalSetService.removeByIds(idList);
        return Result.ok();
    }

    //status lock and unlock
    @PostMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status){
        HospitalSet hospitalSetById = hospitalSetService.getById(id);
        //setting status
        hospitalSetById.setStatus(status);

        hospitalSetService.updateById(hospitalSetById);
        return Result.ok();
    }

    //send SignKey
    @PutMapping("sendKey/{id}")
    public Result lockHospitalSet(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //TODO use text sending
        return Result.ok();

    }




}
