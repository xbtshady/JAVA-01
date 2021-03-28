/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example.consumer.controller;


import example.consumer.po.Order;
import example.consumer.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final ITestService testService;

    @Autowired(required = false)
    public TestController(ITestService testService) {
        this.testService = testService;
    }


    @GetMapping(value = "/transfer")
    public void transfer(){
        //在内存中创建一个订单 用户1转7人民币到给用户2的美元账户上
        Order order = Order.builder()
                .user_income(2)
                .user_pay(1)
                .count(7)
                .build();
        //测试进行转账
        testService.transfer(order);
    }

}
