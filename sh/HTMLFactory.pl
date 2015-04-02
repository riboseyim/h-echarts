#!/usr/local/bin/perl
use strict;
use DBI;
use GetSHDir;
use Data::Dumper;

################################################################
#作者：严睿
#
#创建时间：2015-4-2
#version 1.0:2015-4-2 start_adsl_echarts
#
#未来特性规划：
#1.是否正常启动、执行状态记录、消息通知
#
################################################################

my $APPDIR="/slview/test/yanrui";
my $APPNAME="HostCoreInfo.pl";

my $SetupDir="/slview/nms";
my $Detail;
my $dbh;
	

#实时调用参数
my $para1;
my $para2;

##实时调用检验参数
if(@ARGV > 0){	
	$para1 = $ARGV[0];	
	$para2 = $ARGV[1];	
}

if($SetupDir < 0){
    $Detail ="Can not open properties file  for get SetupDir\n";
    print "$Detail";
    $SetupDir="/slview/nms/";
}

### 使用lib函数连接数据库
unshift (@INC,"$SetupDir/lib/");
require ("CommonPub.pl");
require ("Debug.pl");
unshift (@INC,"$APPDIR/lib/");
require ("NoticeInterface.pl");

my ($ref,$sth,$suc,$statement);
require ("OraPub.pl");
$dbh = &dbCon("OraPub.pl");
$dbh->{AutoCommit}=0;

my $osname;
my $hostip;
my $appname;
my $monitype;
my $monitem;
my $monivalue;
my $snaptime;
my $memo;

my $tmptime;
my $beginTime = getTime();
print "==============网管网监控快照开始  $beginTime ==============\n";

check_appinfo();

if($para1 eq 'TEST'){
	my $to='yanrui@zhong-ying.com';
	&MailNotice("",$to,"Test","test mail content ","$appname","test");
	exit(0);
}
if ($para1 eq 'start') {
	&start_adsl_echarts();
}else{
	print "Undefined para .......";
	exit(0);
}

exit(0);




#################
##
#################
sub start_adsl_echarts(){
	

	#&DefaultMailNotice($RptName,$subject,$content,$APPNAME);
	`/slview/nms/app/adsl/AdslServEcharts.sh start > test.log`;
	$tmptime = getTime();
	print "==============执行 ADSL Auto Deploy Report Update--结束 $tmptime \n";
}


sub check_appinfo(){
	my $hostname=`hostname`;
	chomp($hostname);
	$appname=$hostname.'(HTMLFactory-15.4.1)';
	&check_hostinfo();
}


#############################
#记录消息触发动作日志
#
#begin 2014-12-28 0:30
########################
sub log_notify($){
	my ($content)=@_;

	my $tmptime = getTime();
	my $todaynum=getTodayNum();

	my $fileName="$APPDIR/tmp/$hostip-monitor-notify-$todaynum.log";#
	print "print notify log to >>>> $fileName \n";
	open(WRITE,">>$fileName");	
	print WRITE "$tmptime [$appname] 触发 消息动作 \n $content \n  ================== \n";
	close(WRITE);

}



sub check_hostinfo()
{
	$osname = `uname`;
	my $ipaddr;
	if($osname =~ /Sunos/i)
	{
		my $ipinfo = `/sbin/ifconfig -a`;
		while ($ipinfo =~ /inet\s+((\d|\.)+)\s/g) 
		{
		     $ipaddr = $1;
		     if($ipaddr ne '127.0.0.1')
		     {
		     	last;
		     }
   		}
	}
	
	if($osname =~ /linux/i)
	{
		my $ipinfo = `/sbin/ifconfig`;
		print "$ipinfo  \n";
		while($ipinfo =~ /addr:((\d|\.)+)\s/)
		{
			 $ipaddr = $1;
		     if($ipaddr ne '127.0.0.1')
		     {
		     	last;
		     }
		}
	}
	if($osname =~ /HP-UX/i)
	{
	  my $ipinfo = `/usr/sbin/ifconfig lan0`;
	  if($ipinfo =~ /inet\s+((\d|\.)+)\s/)
	  {
	  	$ipaddr = $1;
	  }
	}


	$hostip=$ipaddr;
}

sub lastHour{
	my ($sec, $min, $hour, $mday, $mon, $year, $wday, $yday, $isdst) = localtime(time-1*3600);
    my $lastHour = sprintf("%04d-%02d-%02d %02d:%02d:%02d", $year+1900, $mon+1, $mday, $hour, $min);
	print "==========lastHour:$lastHour==============\n";
	return $lastHour;
}


sub getTime(){
 (my $sec,my $min,my $hour,my $day,my $mon,my $year,my $weekday,my $yeardate,my $savinglightday)
= (localtime(time));
 $sec = ($sec < 10)? "0$sec":$sec;
 $min = ($min < 10)? "0$min":$min;
 $hour = ($hour < 10)? "0$hour":$hour;
 $day = ($day < 10)? "0$day":$day;
 $mon = ($mon < 9)? "0".($mon+1):($mon+1);
 $year += 1900;
 
 my $now = "$year-$mon-$day $hour:$min:$sec";
 return $now;
}

sub getToday{
	my ($sec, $min, $hour, $mday, $mon, $year, $wday, $yday, $isdst) = localtime(time);
    my $today = sprintf("%04d-%02d-%02d", $year+1900, $mon+1, $mday, $hour, $min);
	return $today;
}

sub getMonthNum{
	my ($sec, $min, $hour, $mday, $mon, $year, $wday, $yday, $isdst) = localtime(time);
    my $today = sprintf("%04d%02d", $year+1900, $mon+1, $mday, $hour, $min);
	return $today;
}

sub getTodayNum{
	my ($sec, $min, $hour, $mday, $mon, $year, $wday, $yday, $isdst) = localtime(time);
    my $today = sprintf("%04d%02d%02d", $year+1900, $mon+1, $mday, $hour, $min);
	return $today;
}

sub getHourNum{
	my ($sec, $min, $hour, $mday, $mon, $year, $wday, $yday, $isdst) = localtime(time);
    my $today = sprintf("%04d%02d%02d%02d", $year+1900, $mon+1, $mday, $hour, $min);
	return $today;
}

sub getTimeNum{
	my ($sec, $min, $hour, $mday, $mon, $year, $wday, $yday, $isdst) = localtime(time);
    my $today = sprintf("%04d%02d%02d%02d%02d%02d", $year+1900, $mon+1, $mday, $hour, $min);
	return $today;
}