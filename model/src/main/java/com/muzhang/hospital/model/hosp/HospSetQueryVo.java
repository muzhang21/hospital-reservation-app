package com.muzhang.hospital.model.hosp;

import lombok.Data;
//for search by name and code
@Data
public class HospSetQueryVo {
    private String hosname;
    private String hoscode;
}
