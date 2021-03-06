### 按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
[代码](https://github.com/xbtshady/JAVA-01/tree/main/Week_07/insertSql)
测试方法：
* 使用jdbc获取连接分批循环插入
* 使用数据连接池多线程同时插入
* 加上rewriteBatchedStatements=true进行测试

总结：
* 数据最好是分批多次插入
* rewriteBatchedStatements参数会影响到批量插入，=true才会批量执行SQL

### 动态切换数据源版本 

[代码](https://github.com/xbtshady/JAVA-01/tree/main/Week_07/ds)

使用dynamic-datasource-spring-boot-starter并重写AbstractRoutingDataSource
实现在业务代码中动态选择数据源

```java
@RestController
public class TestController {
    @Resource
    OrderMapper orderMapper;

    @RequestMapping(value = "/slave")
    public String slave() {
        DataSourceContext.setDataSource("slave");
        return orderMapper.getUserId() + "";
    }

    @RequestMapping(value = "/master")
    public String master() {
        DataSourceContext.setDataSource("master");
        return orderMapper.getUserId() + "";
    }
}
```

