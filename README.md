activiti-hashed-passwords
=========================

Plugin for activiti for storing user passwords as hash instead of plain text.

With this plugin you can use the activiti identity service and not worry about password security. Passwords are hashed with PBKDF2 algorithm and combined with a salt, which is unique for every password. The hashing class is taken from crackstation (https://crackstation.net/hashing-security.htm).

You can use this library in your custom application or integrate it into activiti-explorer or activiti-rest.

installation for activiti-explorer
----------------------------------

1. [Download the jar file] (https://github.com/lovromazgon/activiti-hashed-passwords/raw/master/lib/activiti-hashed-passwords-1.0.jar) in the lib folder
2. Copy the jar file to the exploded activiti-explorer location ${activiti-explorer.war}/WEB-INF/lib
3. Register the HashedUserManagerFactory in the processEngineConfiguration bean. Do this by adding the following lines to activiti-standalone-context.xml (located in ${activiti-explorer.war}/WEB-INF):

```
<bean id="processEngineConfiguration" class="org.activiti.spring.SpringProcessEngineConfiguration">
...
  <property name="customSessionFactories">
    <list>
      <bean class="com.mazgon.activiti.HashedUserManagerFactory" />
    </list>
  </property>
...
</bean>
```
