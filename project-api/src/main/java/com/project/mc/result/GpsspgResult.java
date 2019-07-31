package com.project.mc.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sang on 2019/3/22.
 */
@Data
public class GpsspgResult implements Serializable{

    private static final long serialVersionUID = 1L;

    private String msg;

    private String latitude;

    private String longitude;

    private String status;

    private List<Result> result;

    private Integer count;

    private String match;

    @Data
    public static class Result implements Serializable{
        private static final long serialVersionUID = 1L;
        private String address;
        private String roads;
        private String lng;
        private String lat;

    }

}
