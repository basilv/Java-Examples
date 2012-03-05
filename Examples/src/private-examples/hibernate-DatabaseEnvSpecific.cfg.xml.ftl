<#if environment.id != "devl">
<#-- Don't generate the file outside of development. 
To avoid hardcoding non-dev database passwords,
we require that this file be manually supplied in the other environments.
-->
<@skipGeneration/>
</#if>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
		"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
		"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

<!--  Holds database settings that will vary across environments. -->
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.url">jdbc:oracle:thin:@iedm2a31:1521:oranmsd1</property>
        <property name="hibernate.connection.password">nms77dev</property>
    </session-factory>
</hibernate-configuration>
