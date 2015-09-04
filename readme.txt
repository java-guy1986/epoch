一:打包发布
1、打包不执行测试
mvn clean package -Dmaven.test.skip=true
2、编译（不运行）测试单元
mvn test-compile
3、其它操作，参考maven官方
http://maven.apache.org/guides/getting-started/index.html
**********************************************************************************
二：已优化功能
1、mybatis
A、 分页插件的实现  代码参考:com.desksoft.epoch.common.plugins.PagerHelper.java
B 、类型(java Boolean到mysql INTEGER)转换器的实现，代码参考:
	com.desksoft.epoch.common.plugins.handler.BooleanTypeHandler

2、spring aop
A、实现service层日志拦截器的实现，代码参考:com.desksoft.epoch.common.log.SysLogAspect

3、自定义filter
A、具体参考:com.desksoft.epoch.common.filter.ApplicationContextFilter
************************************************************************************
四、统一异常处理
A、定义了DaoException、ServiceException，但service、dao层的方法签名都会抛出Exception，
这样做的目的是上层调用下层时不用编写繁琐的try{}catch()语句，而将异常统一交给Spring处理。
具体参考springmvc-servlet.xml中对异常处理的定义：
	<!-- 统一异常处理 -->
	<bean id="exceptionResolver"
		class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
		<!-- 定义默认的异常处理页面 -->
		<property name="defaultErrorView" value="forward:/exception.html" />
		<!-- 定义异常处理页面用来获取异常信息的变量名，默认名为exception -->  
		<property name="exceptionAttribute" value="ex"></property>
		<property name="exceptionMappings">
			<props>
				<prop key="com.desksoft.epoch.core.base.dao.DaoException">forward:/views/ex/daoEx.html</prop>
				<prop key="com.desksoft.epoch.core.base.service.ServiceException">forward:/views/ex/serviceEx.html</prop>
			</props>
		</property>
	</bean>
*******************************************************************************************************************
五、单元测试
请参考src/test/java中编写的单元测试。

