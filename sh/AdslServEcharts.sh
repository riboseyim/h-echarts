#!/usr/bin/sh
#AUTHOR:YANRUI
#CREATE:2015-04-01
#Echarts统计图表更新

os=`uname`

if [ $os = "HP-UX" ]
then
	. /slview/.profile
	pnum1=`ps -efx|grep com.zhongying.huanan.product.adsl.echarts.GenAdslServBarJS|grep java|grep -v grep|cut -b 10-14`
	if [ "$pnum1" != "" ]
	then		
	        echo com.zhongying.huanan.product.adsl.echarts.GenAdslServBarJS is running >> /slview/nms/service/GDAdslLan/running.txt
		exit 1
	fi
elif [ $os = "SunOS" ]
then
	. /slview/.profile
	pnum1=`/usr/ucb/ps -auxww |grep com.zhongying.huanan.product.adsl.echarts.GenAdslServBarJS|grep java|grep -v grep|cut -b10-14`
	if [ "$pnum1" != "" ]
	then		
		echo com.zhongying.huanan.product.adsl.echarts.GenAdslServBarJS is running >> /slview/nms/service/GDAdslLan/running.txt
		exit 1
	fi
elif [ $os = "Linux" ]
then
	. /slview/.bash_profile
	ps -auxww |grep com.zhongying.huanan.product.adsl.echarts.GenAdslServBarJS|grep -v grep|grep java|cut -b10-14 >pnum1
	while read id
	do
	    echo exit 1
	        echo com.zhongying.huanan.product.adsl.echarts.GenAdslServBarJS is running >> /slview/nms/service/GDAdslLan/running.txt
	        exit 1
	done < pnum1
	rm pnum1
elif [ $os = "AIX" ]
then
	. /slview/.profile
	pnum1=`/usr/bin/ps -ef|grep com.zhongying.huanan.product.adsl.echarts.GenAdslServBarJS|grep java|grep -v grep|cut -b 10-14`
	if [ "$pnum1" != "" ]
	then		
		echo com.zhongying.huanan.product.adsl.echarts.GenAdslServBarJS is running >> /slview/nms/service/GDAdslLan/running.txt
		exit 1
	fi
else
	echo "sorry, we support HP-UX,SunOS,AIX,Linux only"
	exit
fi


SERVICE_HOME=/slview/nms/app/adsl/

CLASSPATH=$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar

cd ${SERVICE_HOME}

LIBHOME="${SERVICE_HOME}/lib/*.jar"

for jarfile in ${LIBHOME}

do 

        CLASSPATH=$CLASSPATH:"${jarfile}"

done

echo CLASSPATH=$CLASSPATH

export CLASSPATH

echo now dir:`pwd`

#cd lib
LANG=zh_CN.gb2312
echo '-------------1'
export LANG

echo '-------------2'
 ${JAVA_HOME}/bin/java com.zhongying.huanan.product.adsl.echarts.GenAdslServBarJS 

 
echo '-------------3'
