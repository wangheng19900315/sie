package com.sie.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sie.framework.entity.OrderDetailEntity;
import com.sie.framework.entity.OrderEntity;
import com.sie.framework.type.OrderStatus;
import com.sie.framework.vo.OrderSearchVo;
import com.sie.service.OrderDetailService;
import com.sie.service.OrderService;
import com.sie.service.bean.*;
import com.sie.service.excel.OrderExcelBean;
import com.sie.service.excel.OrderImport;
import com.sie.util.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = Logger.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("/list.html")
    public String list(){
        return "/order/list";
    }


    @RequestMapping("/detail.html")
    public String detail(Model model, Integer id){
        model.addAttribute("id", id);
        return "/order/detail";
    }

    @RequestMapping("/update.html")
    public String update(Model model, Integer id){
        model.addAttribute("id", id);
        return "/order/update";
    }




    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<OrderBean> listJons(OrderSearchVo vo, Integer page, Integer rows){

        PageInfo<OrderBean> pageInfo = null;
        try{
            pageInfo = this.orderService.getOrderList(page,rows, vo);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }


    @RequestMapping("/detail.json")
    @ResponseBody
    public OrderBean detail(Integer orderId){
        OrderBean orderBean = null;
        try{
            orderBean  = this.orderService.getDetail(orderId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderBean;
    }


    @RequestMapping(value = "/add.json",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean add(String order){
        ObjectMapper mapper = new ObjectMapper();
        ResultBean resultBean = new ResultBean();

        try{
            OrderBean orderBean = mapper.readValue(order,OrderBean.class);
            resultBean = this.orderService.addOrder(orderBean);
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


    @RequestMapping("/detailList.json")
    @ResponseBody
    public PageInfo<OrderDetailBean> listJons(Integer page, Integer rows,Integer orderId ){

        PageInfo<OrderDetailBean> pageInfo = null;
        try{
            pageInfo = this.orderDetailService.getOrderDetailList(page,rows, orderId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping("/updateCourseIds.json")
    @ResponseBody
    public ResultBean updateCourseIds(OrderDetailEntity detailEntity){

        ResultBean resultBean = new ResultBean();
        try{
           this.orderDetailService.updateCourseIds(detailEntity);
            resultBean.setSuccess(true);
            resultBean.setMessage("修改成功");
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    @RequestMapping("/updateOrderInfo.json")
    @ResponseBody
    public ResultBean updateOrderInfo(OrderEntity orderEntity){

        ResultBean resultBean = new ResultBean();
        try{
            this.orderService.updateOrderInfo(orderEntity);
            resultBean.setSuccess(true);
            resultBean.setMessage("修改成功");
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


    @RequestMapping("/addCoursesAndDorm.json")
    @ResponseBody
    public OrderBean updateOrderInfo(Integer orderId){
        //获取订单下边可以加个的课程和住宿
        OrderBean orderBean = null;
        try{
            orderBean  = this.orderService.getDetail(orderId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderBean;
    }

    @RequestMapping(value = "/delete.json")
    @ResponseBody
    public ResultBean delete(Integer id){
        ResultBean resultBean = new ResultBean();
        //判断是否可以删除
        OrderEntity orderEntity = orderService.get(id);
        if(orderEntity != null){
            //判断是否可以删除
            OrderStatus status = OrderStatus.valueOf(orderEntity.getStatus());
            if(status == OrderStatus.CANCEL){
                try{
                    this.orderService.delete(id);
                    if(NumberUtil.isSignless(id)){
                        resultBean.setMessage("删除成功");
                        resultBean.setSuccess(true);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return resultBean;
    }





    @RequestMapping(value = "export.json")
    public String exportFile(OrderSearchVo vo, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "订单信息"+ DateUtil.format(new Date(), "yyyyMMddHHmmss")+".xlsx";
            PageInfo<OrderBean> pageInfo = orderService.getOrderList(0,Integer.MAX_VALUE, vo);

            List<OrderExcelBean> orderExcelBeanList = new ArrayList<>();
            if(pageInfo.getRows() != null && pageInfo.getRows().size() > 0){
                for(OrderBean orderBean:pageInfo.getRows()){
                    OrderExcelBean orderExcelBean = new OrderExcelBean();
                    BeanUtils.copyProperties(orderBean, orderExcelBean);
                    if(orderBean.getCreateTime() != null){
                        orderExcelBean.setCreateTimeString(DateUtil.format(orderBean.getCreateTime(), "yyyy-MM-dd"));
                    }
                    if(orderBean.getPayTime() != null){
                        orderExcelBean.setPayTimeString(DateUtil.format(orderBean.getPayTime(), "yyyy-MM-dd"));
                    }
                    orderExcelBeanList.add(orderExcelBean);
                }
            }
            new ExportExcel("订单信息", OrderExcelBean.class).setDataList(orderExcelBeanList).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/order/list.html";
    }

    @RequestMapping(value = "import.json")
    @ResponseBody
    public ResultBean importFile(@RequestParam("excelFile") MultipartFile file, RedirectAttributes redirectAttributes) {
        ResultBean resultBean = new ResultBean();
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 0, 0);
            List<OrderImport> list = ei.getDataList(OrderImport.class);
            if(list == null || list.size() == 0){
                resultBean.setMessage("excel数据为空，请检查文件");
                return resultBean;
            }
            int start,end;//订单开始和结束的位置
            for(int i=0; i<list.size(); ){
                String studentID = list.get(i).getStudentID();
                start = i;
                end = i+1;
                //订单合并单元格
                while (end < list.size() && studentID.equals(list.get(end).getStudentID())){
                    end++;
                }
                String flag = this.orderService.importBean(list,start,end);
                if(StringUtil.isNotBlank(flag)){
                    failureMsg.append("<p>第"+(i+2)+"行:"+flag+"</p>") ;
                    failureNum ++;
                }else{
                    successNum++;
                }
                i = end;
            }
            resultBean.setMessage("导入完毕，成功导入"+successNum+"条,导入失败"+failureNum+"条;"+failureMsg.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBean;
    }

}