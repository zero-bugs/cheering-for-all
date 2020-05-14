package com.zero.bugs.demo.algorithm.controllers;

import com.alibaba.fastjson.JSON;
import com.zero.bugs.demo.algorithm.model.vo.ResponseViewObj;
import com.zero.bugs.demo.algorithm.repository.SortAlgorithmService;
import com.zero.bugs.demo.algorithm.repository.BinSearchTree;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/test/method")
public class AlgorithmServiceImpl {
    SortAlgorithmService sortService = new SortAlgorithmService();
    BinSearchTree service = new BinSearchTree();


    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public ResponseViewObj executeSortAlgorithm(@RequestParam("dataList") String dataList, @RequestParam("type") int type) {
        ResponseViewObj res = new ResponseViewObj();
        if (StringUtils.isEmpty(dataList) || type < 0){
            res.setMsg("please check input parameter,again.");
            return res;
        }

        List<Integer> dataStrArr = JSON.parseArray(dataList, Integer.class);
        int[] data = Arrays.stream(dataStrArr.toArray(new Integer[0])).mapToInt(Integer::valueOf).toArray();

        StringBuilder stringBuilder = new StringBuilder(0);
        stringBuilder.append("begin to execute.").append(System.lineSeparator());
        res.setMsg("begin to execute");
        res.setErrorCode(0);
        switch (type) {
            case 0:
                res.setData(sortService.bubbleSort(data));
                break;
            case 1:
                res.setData(sortService.selectSort(data));
                break;
            case 2:
                res.setData(sortService.insertSort(data));
                break;
            case 3:
                res.setData(sortService.shellSort(data));
                break;
            case 4:
                res.setData(sortService.quickSort(data,0,data.length - 1));
                break;
            case 5:
                res.setData(sortService.heapSort(data));
                break;
            case 6:
                res.setData(sortService.mergeSort(data,0,data.length-1));
                break;
            default:
                res.setData("cannot find type for [0-6]");
        }
        return res;
    }

    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    public ResponseViewObj executeTreeAlgorithm(@RequestParam("dataList") String dataList, @RequestParam("type") int type) {
        ResponseViewObj res = new ResponseViewObj();
        if (StringUtils.isEmpty(dataList) || type < 0){
            res.setMsg("please check input parameter,again.");
            return res;
        }

        List<Integer> dataStrArr = JSON.parseArray(dataList, Integer.class);
        int[] data = Arrays.stream(dataStrArr.toArray(new Integer[0])).mapToInt(Integer::valueOf).toArray();

        StringBuilder stringBuilder = new StringBuilder(0);
        stringBuilder.append("begin to execute.").append(System.lineSeparator());
        res.setMsg("begin to execute");
        res.setErrorCode(0);

        switch (type) {
            case 0:
                for(int val : data) {
                    service.insert(val);
                }
                break;
            case 1:
                for (int val:data){
                    service.delete(val);
                }
                break;
            case 2:
                service.inOrderTravel();
                res.setMsg(service.getMsg());
                break;
            case 3:
                service.midOrderTravel();
                res.setMsg(service.getMsg());
                break;
            case 4:
                service.backOrderTravel();
                res.setMsg(service.getMsg());
                break;
            default:
                res.setData("cannot find type for [0-6]");
        }
        return res;
    }

}

