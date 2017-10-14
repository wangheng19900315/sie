package com.sie.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.*;
import com.sie.framework.type.OrderStatus;
import com.sie.framework.type.OrderType;
import com.sie.framework.type.SystemType;
import com.sie.service.*;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.ResultBean;
import com.sie.service.vo.*;
import com.sie.util.DateUtil;
import com.sie.util.FileUtil;
import com.sie.util.NumberUtil;
import com.sie.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/20.
 */
@Controller
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private StudentService studentService;


    @Autowired
    private OrderService orderService;


    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @RequestMapping("/index.html")
    public String index() {
        return "api";
    }

    private static final String SYSTEM_ACCESS_TOKEN = "un23n4no2bu4bs34";

}
