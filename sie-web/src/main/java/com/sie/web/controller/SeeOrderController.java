package com.sie.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.CrEntity;
import com.sie.framework.entity.OrderDetailEntity;
import com.sie.framework.entity.OrderEntity;
import com.sie.framework.type.OrderStatus;
import com.sie.framework.vo.OrderSearchVo;
import com.sie.service.CouponService;
import com.sie.service.CrService;
import com.sie.service.OrderDetailService;
import com.sie.service.OrderService;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.OrderDetailBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.service.excel.OrderExcelBean;
import com.sie.service.excel.OrderImport;
import com.sie.util.DateUtil;
import com.sie.util.ExportExcel;
import com.sie.util.ImportExcel;
import com.sie.util.NumberUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/see_order")
public class SeeOrderController {

    private static final Logger LOGGER = Logger.getLogger(SeeOrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private CrService crService;

    @Autowired
    private CouponService couponService;

    @RequestMapping("/list.html")
    public String list() {
        return "/see_order/list";
    }
}