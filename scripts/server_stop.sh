#!/bin/bash
sudo su
PID=`ps -eaf | grep skilltracker-nonadmin | grep -v grep | awk '{print $2}'`
kill -9 $PID
PID=`ps -eaf | grep skilltracker-admin | grep -v grep | awk '{print $2}'`
kill -9 $PID
PID=`ps -eaf | grep skilltracker-receiver | grep -v grep | awk '{print $2}'`
kill -9 $PID
