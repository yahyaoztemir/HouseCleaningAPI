package com.yahya.justlife.requests;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public abstract class BaseReservationRequest {


    private Date date ;

    private int startHour ;

    private int startMinute ;

    private int duration ;

    private List<Long> cleanerProfessionalsIds;

}
